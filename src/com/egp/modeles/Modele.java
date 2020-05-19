package com.egp.modeles;
import com.egp.constants.*;
import com.egp.observer.Observable;

import java.util.ArrayList;
import java.util.Random;

public class Modele extends Observable {
    private ArrayList<Zone> cases;
    private int nbCols;
    private int nbRows;
    private int[] indices;
    private int nbPlayer;
    private ArrayList<Player> players;
    private Zone heliport;
    private int nbTour = 0;
    private Player currentPlayer;

    public Modele(int nbCols, int nbRows, int nbPlayer) {
        this.nbCols = nbCols;
        this.nbRows = nbRows;
        this.nbPlayer = nbPlayer;

        this.cases = new ArrayList<>();
        this.players = new ArrayList<>();

        this.init();
    }

    public void init() {
        for (int i = 0; i < nbRows; i++) {
            for (int j = 0; j < nbCols; j++) {
                this.cases.add(new Zone(Etat.Normale, Type.Normale, j, i));
            }
        }

        Type[] values = Type.values();

        this.indices = new Random().ints(0, nbCols * nbRows).distinct().limit(values.length-1).toArray();

        for (int i = 0; i < this.indices.length; i++) {
            this.cases.get(this.indices[i]).type = values[i + 1];
            if (values[i + 1] == Type.Heliport)
                this.heliport = this.cases.get(this.indices[i]);
        }

        int spawn_idx = new Random().nextInt(nbCols * nbRows);
        for(int i = 0; i<this.nbPlayer; i++)
            this.players.add(new Player(this.cases.get(spawn_idx), i+1));

        setCurrentPlayer();
    }

    private void setCurrentPlayer(){
        this.currentPlayer = this.players.get(nbTour % nbPlayer);
    }

    public boolean atteignable(Zone c){
        return this.currentPlayer.atteignable(c);
    }

    public void deplace(Zone c){
        this.currentPlayer.setPosition(c);
        this.currentPlayer.setActions(this.currentPlayer.getActions() - 1);
    }

    public boolean assechable(Zone c){
        if(c.etat == Etat.Normale || c.etat == Etat.Submergee)
            return false;

        if(this.currentPlayer.getPosition().x == c.x &&
        this.currentPlayer.getPosition().y == c.y)
            return true;

        return atteignable(c);
    }

    public void asseche(Zone c){
        c.etat = Etat.Normale;
    }

    public void incrementeTour(){
        this.nbTour++;
        setCurrentPlayer();
    }

    public void inondeCases() {
        int i = 0;
        Random r = new Random();
        while (i < 3) {
            int idx = r.nextInt(this.nbCols * this.nbRows);
            if (this.cases.get(idx).etat == Etat.Normale) {
                this.cases.get(idx).etat = Etat.Innondee;
                i++;
            } else if (this.cases.get(idx).etat == Etat.Innondee) {
                this.cases.get(idx).etat = Etat.Submergee;
                i++;
            }

        }
        notifyObservers();
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

    public String toString() {
        String msg = "";
        for (Zone c : this.cases) {
            msg += c.toString() + "\n";
        }
        return msg;
    }
}
