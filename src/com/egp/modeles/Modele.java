package com.egp.modeles;

import com.egp.constants.Sounds;
import com.egp.constants.enums.Etat;
import com.egp.constants.enums.PlayerType;
import com.egp.constants.enums.Type;
import com.egp.controllers.Controller;
import com.egp.modeles.Cartes.Card;
import com.egp.modeles.Cartes.CardDeck;
import com.egp.modeles.Events.Event;
import com.egp.modeles.Events.Key;
import com.egp.modeles.Players.Explorateur;
import com.egp.modeles.Players.Player;
import com.egp.modeles.Players.Plongeur;
import com.egp.observer.Observable;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;
import java.util.Random;

public class Modele extends Observable {
    private final int nbCols;
    private final int nbRows;
    private final int nbPlayer;

    private Controller controller;

    private final ArrayList<Zone> cases;
    private final ArrayList<Player> players;
    private final ArrayList<Player> deadPlayers;

    private Zone heliport;
    private Player currentPlayer;

    private int nbTour = 0;

    public CardDeck zonePaquet;
    public CardDeck eventPaquet;

    public Modele(int nbCols, int nbRows, ArrayList<PlayerType> playersType, double dropKey) {
        this.nbCols = nbCols;
        this.nbRows = nbRows;
        this.nbPlayer = playersType.size();

        this.cases = new ArrayList<>();
        this.players = new ArrayList<>();
        this.deadPlayers = new ArrayList<>();

        this.init(playersType, dropKey);
    }

    /**
     * Méthode initialisant tout les atributs du modèle
     * @param playersType permet de créer les objets Player du bon type de joueur
     * @param dropKey proba de tirer une clé dans le paquet de cartes
     */
    public void init(ArrayList<PlayerType> playersType, double dropKey) {
        for (int i = 0; i < nbRows; i++) {
            for (int j = 0; j < nbCols; j++) {
                this.cases.add(new Zone(Etat.Normale, Type.Normale, j, i));
            }
        }

        Type[] values = Type.values();
        int[] indices = new Random().ints(0, nbCols * nbRows).distinct().limit(values.length - 1).toArray();
        ArrayList<Event> keys = new ArrayList<>();

        for (int i = 0; i < indices.length; i++) {
            this.cases.get(indices[i]).type = values[i + 1];
            if (values[i + 1] == Type.Heliport)
                this.heliport = this.cases.get(indices[i]);

            if (values[i+1] != Type.Heliport && values[i+1] != Type.Normale)
                keys.add(new Key(values[i+1]));
        }


        //Initialisation des paquets de cartes.
        this.zonePaquet = new CardDeck(this.cases);
        this.eventPaquet = new CardDeck(keys, new Event[]{new Event("Rien"), new Event("Montée")}, dropKey);

        int[] spawn_idx = new Random().ints(0, nbCols * nbRows).limit(nbPlayer).toArray();

        for(int i = 0; i<playersType.size(); i++){
            Player player = null;
            switch (playersType.get(i)){
                case Normal:
                    player = new Player(this.cases.get(spawn_idx[i]), i + 1, playersType.get(i));
                    break;
                case Explorateur:
                    player = new Explorateur(this.cases.get(spawn_idx[i]), i + 1, playersType.get(i));
                    break;
                case Plongeur:
                    player = new Plongeur(this.cases.get(spawn_idx[i]), i + 1, playersType.get(i));
                    break;
            }

            this.players.add(player);
            this.cases.get(spawn_idx[i]).addPlayer(player);
        }


        setCurrentPlayer();
    }

    /**
     * Méthodes qui testent si une action est faisaible sur la Zone c
     */

    public boolean atteignable(Zone c){
        if (this.currentPlayer.getActions() > 0)
            return this.currentPlayer.atteignable(c);
        else
            return false;
    }

    public boolean recuperable(Zone c){

        if (c.type == Type.Heliport || c.type == Type.Normale)
            return false;

        return this.currentPlayer.recuperable(c);
    }

    public boolean assechable(Zone c){
        if (c.etat == Etat.Normale || c.etat == Etat.Submergee)
            return false;

        if (this.currentPlayer.getActions() == 0)
            return false;

        if (this.currentPlayer.getPosition().x == c.x &&
                this.currentPlayer.getPosition().y == c.y)
            return true;

        return this.currentPlayer.atteignable(c);
    }

    /**
     * Méthodes qui effectuent une action sur la Zone c
     */

    public void deplace(Zone c){
        this.currentPlayer.getPosition().deletePlayer(this.currentPlayer);
        this.currentPlayer.setPosition(c);
        this.currentPlayer.getPosition().addPlayer(this.currentPlayer);

        this.afterAction();
    }

    public void asseche(Zone c){
        c.asseche();

        this.afterAction();
    }

    public void recupere(Zone c){
        this.currentPlayer.removeKey(c.type);
        this.currentPlayer.addArtefacts(new Artefact(c.type));

        c.setType(Type.Normale);

        MediaPlayer artifactPlayer = new MediaPlayer(Sounds.artifact);
        artifactPlayer.setAutoPlay(true);

        this.afterAction();
    }

    /**
     * Méthode à exécuter après chaque action
     */
    public void afterAction(){
        this.currentPlayer.setActions(this.currentPlayer.getActions() - 1);
        if (checkWin())
            this.controller.gameWon();
        this.notifyObservers();
    }


    private void innondeCase(Zone c){
        if (c.etat != Etat.Submergee){
            c.innonde();
            this.checkDead();
        }
    }

    public void inondeCases() {
        for(int i = 0; i<3; i++){
            Card c = this.zonePaquet.tirer();
            this.innondeCase((Zone) c.getObject());

            if (((Zone) c.getObject()).etat == Etat.Submergee)
                this.zonePaquet.retire(c);
        }

        MediaPlayer floodingPlayer = new MediaPlayer(Sounds.flooding);
        floodingPlayer.setVolume(0.2);
        floodingPlayer.setAutoPlay(true);
    }

    /**
     * Méthode qui tire et exécute un évènement après la fin d'un tour. Le tirage d'un évènement est géré par la classe CardDeck
     * @return l'évènement tiré
     */
    public Event eventDrop(){
        Card c = this.eventPaquet.tirer();
        Event e = (Event) c.getObject();

        switch (e.getName()){
            case "Rien":
                break;
            case "Montée":
                innondeCase(this.currentPlayer.getPosition());
                this.zonePaquet.melangeTrash();
                this.zonePaquet.placeTrash();
                break;
            default:
                this.currentPlayer.addKey((Key) e);
                this.eventPaquet.retire(c);
        }

        return e;
    }

    /**
     * Méthode réunissant les méthodes à appeler à la fin d'un tour
     */

    public void incrementeTour(){
        this.nbTour++;
        this.setCurrentPlayer();
        this.notifyObservers();
    }

    /**
     * Méthode décidant quel joueur doit joueur pour ce tour
     */
    private void setCurrentPlayer(){
        if (this.currentPlayer != null)
            this.currentPlayer.setCurrent(false);
        this.currentPlayer = this.players.get(this.nbTour % this.players.size());
        this.currentPlayer.setActions(3);
        this.currentPlayer.setCurrent(true);
    }

    /**
     * Méthode qui vérifie si un ou plusieurs joueurs sont morts
     * @return true si un ou plusieurs joueurs sont morts, false sinon
     */
    public boolean checkDead(){
        ArrayList<Player> toRemove = new ArrayList<>();
        for(Player player : this.players){
            if (player.isDead()){
                toRemove.add(player);
            }
        }

        if (toRemove.size() > 0) {
            for (Player player : toRemove) {
                player.getPosition().deletePlayer(player);
                this.players.remove(player);
                this.deadPlayers.add(player);
            }
            MediaPlayer mediaPlayer = new MediaPlayer(Sounds.die);
            mediaPlayer.setAutoPlay(true);
            return true;
        }
        return false;
    }

    /**
     * Méthode qui vérifie si la partie est gagnée
     * @return true si la partie est gagnée, false sinon
     */
    public boolean checkWin(){
        int nbArtefacts = 0;
        for (Player player : this.heliport.getPlayers()){
            for(Artefact ignored : player.getArtefacts())
                nbArtefacts++;
        }

        return nbArtefacts == 4 && this.heliport.getPlayers().size() == this.players.size();

    }
    /**
     * Méthode qui vérifie si la partie est perdue
     * @return true si la partie est perdue, false sinon
     */
    public boolean checkLoose(){
        if (this.players.size() == 0) {
            return true;
        }

        for(Player player : this.deadPlayers){
            if (player.getArtefacts().size() > 0 || player.getKeys().size() > 0)
                return true;
        }

        ArrayList<Key> plongeurKeys = new ArrayList<>();

        for (Player player : players) {
            if (player.getType() == PlayerType.Plongeur) {
                plongeurKeys.addAll(player.getKeys());
                break;
            }
        }

        for(Zone c : this.cases){
            if (c.type != Type.Normale && c.etat == Etat.Submergee) {
                boolean found = false;
                for (Key key : plongeurKeys) {
                    if (key.getElement() == c.type) {
                        found = true;
                    }
                }
                if (!found)
                    return true;
            }
        }

        return false;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public int getNbRows() {
        return nbRows;
    }

    public int getNbCols() {
        return nbCols;
    }

    public ArrayList<Zone> getCases() {
        return this.cases;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public String toString() {
        StringBuilder msg = new StringBuilder();
        for (Zone c : this.cases) {
            msg.append(c.toString()).append("\n");
        }
        return msg.toString();
    }

}
