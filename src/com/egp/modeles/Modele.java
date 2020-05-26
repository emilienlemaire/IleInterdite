package com.egp.modeles;

import com.egp.constants.Etat;
import com.egp.constants.Sounds;
import com.egp.constants.Type;
import com.egp.observer.Observable;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Modele extends Observable {
    private final ArrayList<Zone> cases;

    private final int nbCols;
    private final int nbRows;
    private final int nbPlayer;

    private final ArrayList<Player> players;
    private final ArrayList<Player> deadPlayers;
    private Zone heliport;
    private int nbTour = 0;
    private Player currentPlayer;

    private ArrayList<Key> keys;

    public Modele(int nbCols, int nbRows, int nbPlayer) {
        this.nbCols = nbCols;
        this.nbRows = nbRows;
        this.nbPlayer = nbPlayer;

        this.cases = new ArrayList<>();
        this.players = new ArrayList<>();
        this.deadPlayers = new ArrayList<>();

        this.init();
    }

    public void init() {
        for (int i = 0; i < nbRows; i++) {
            for (int j = 0; j < nbCols; j++) {
                this.cases.add(new Zone(Etat.Normale, Type.Normale, j, i));
            }
        }

        this.keys = new ArrayList<>();
        Type[] values = Type.values();
        int[] indices = new Random().ints(0, nbCols * nbRows).distinct().limit(values.length - 1).toArray();

        for (int i = 0; i < indices.length; i++) {
            this.cases.get(indices[i]).type = values[i + 1];
            if (values[i + 1] == Type.Heliport)
                this.heliport = this.cases.get(indices[i]);

            if (values[i+1] != Type.Heliport && values[i+1] != Type.Normale)
                this.keys.add(new Key(values[i+1]));
        }

        Collections.shuffle(this.keys);

        int[] spawn_idx = new Random().ints(0, nbCols * nbRows).limit(nbPlayer).toArray();

        for(int i = 0; i<this.nbPlayer; i++) {
            Player player = new Player(this.cases.get(spawn_idx[i]), i + 1);
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
        if (c.type == Type.Heliport || c.type == Type.Normale || c.etat == Etat.Submergee ||
                this.currentPlayer.getActions() == 0 ||
                this.currentPlayer.getPosition().x == c.x && this.currentPlayer.getPosition().y == c.y)
            return false;


        for (Key k : this.currentPlayer.getKeys()){
            if (c.type == k.getElement())
                return true;
        }
        return false;
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

        afterAction();
    }

    public void asseche(Zone c){
        c.asseche();

        afterAction();
    }

    public void recupere(Zone c){
        this.currentPlayer.removeKey(c.type);
        this.currentPlayer.addArtefacts(new Artefact(c.type));

        c.setType(Type.Normale);
        System.out.println(this.currentPlayer);

        MediaPlayer artifactPlayer = new MediaPlayer(Sounds.artifact);
        artifactPlayer.setAutoPlay(true);

        afterAction();
    }

    /**
     * Méthode à exécuter après chaque action
     */
    public void afterAction(){
        this.currentPlayer.setActions(this.currentPlayer.getActions() - 1);
        if (checkWin())
            System.out.println("C'est win");
        notifyObservers();
    }


    private boolean innondeCase(Zone c){
        if (c.etat != Etat.Submergee){
            c.innonde();
            checkDead();
            return true;
        }
        return false;
    }

    public void inondeCases() {
        int i = 0;
        Random r = new Random();
        while (i < 3) {
            int idx = r.nextInt(this.nbCols * this.nbRows);
            if(innondeCase(this.cases.get(idx)))
                i++;
        }

        MediaPlayer floodingPlayer = new MediaPlayer(Sounds.flooding);
        floodingPlayer.setVolume(0.2);
        floodingPlayer.setAutoPlay(true);
    }

    public boolean dropCles(){
        float r = new Random().nextFloat();

        if (r > 0.66) {
            if (this.keys.size() > 0) {
                Key k = this.keys.get(0);
                this.currentPlayer.addKey(k);
                System.out.println(this.currentPlayer);
                this.keys.remove(k);
                return true;
            }
            return false;
        }

        if (r > 0.33) {
            innondeCase(this.currentPlayer.getPosition());
            return false;
        }

        return false;
    }


    public void incrementeTour(){
        this.nbTour++;
        setCurrentPlayer();
        notifyObservers();
    }

    private void setCurrentPlayer(){
        if (this.players.size() == 0) {
            this.currentPlayer = null;
        } else {
            if (this.currentPlayer != null)
                this.currentPlayer.setCurrent(false);
            this.currentPlayer = this.players.get(this.nbTour % this.players.size());
            this.currentPlayer.setActions(3);
            this.currentPlayer.setCurrent(true);
        }
    }

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

    public boolean checkWin(){
        int nbArtefacts = 0;
        for (Player player : this.heliport.getPlayers()){
            for(Artefact artefact : player.getArtefacts())
                nbArtefacts++;
        }

        return nbArtefacts == 4;

    }

    public boolean checkLoose(){

        if (this.players.size() == 0)
            return true;

        for(Player player : this.deadPlayers){
            if (player.getArtefacts().size() > 0 || player.getKeys().size() > 0)
                return true;
        }

        for(Zone c : this.cases){
            if (c.type != Type.Normale && c.etat == Etat.Submergee)
                return true;
        }

        return false;

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

    public ArrayList<Player> getPlayers() { return this.players; }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public String toString() {
        String msg = "";
        for (Zone c : this.cases) {
            msg += c.toString() + "\n";
        }
        return msg;
    }

}
