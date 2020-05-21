package com.egp.modeles;

import com.egp.constants.Etat;
import com.egp.observer.Observable;

import java.util.ArrayList;

public class Player extends Observable {
    private Zone position;
    private boolean isCurrent = false;
    private final int ID;
    private int actions = 3;

    private ArrayList<Key> keys;
    private ArrayList<Artefact> artefacts;

    public Player(Zone position, int ID){
        this.position = position;
        this.ID = ID;

        this.keys = new ArrayList<>();
        this.artefacts = new ArrayList<>();
    }

    public int getActions() {
        return actions;
    }

    public void setActions(int actions){
        this.actions = actions;
        notifyObservers();
    }

    public Zone getPosition(){
        return this.position;
    }

    public void setPosition(Zone p){
        this.position = p;
        notifyObservers();
    }

    public int getID(){ return this.ID; }

    public ArrayList<Key> getKeys(){ return this.keys; }

    public void addKey(Key cle){
        this.keys.add(cle);
        notifyObservers();
    }

    public ArrayList<Artefact> getArtefacts(){ return this.artefacts; }

    public void addArtefacts(Artefact artefact){
        this.artefacts.add(artefact);
        notifyObservers();
    }

    public void removeKey(Key cle){
        this.keys.remove(cle);
        notifyObservers();
    }

    public boolean atteignable(Zone c){

        if (c.etat == Etat.Submergee)
            return false;

        if (this.position.x == c.x)
            return this.position.y == c.y - 1 || this.position.y == c.y + 1;

        if (this.position.y == c.y)
            return this.position.x == c.x - 1 || this.position.x == c.x + 1;

        return false;
    }

    public String toString(){
        String msg = "Joueur no : " + this.ID + "\nNombre d'actions restantes : " +
                this.actions + "\nNombre d'artefacts : " + this.artefacts.size() +
                "\nType de clé disponible : ";

        for(Key k : this.keys)
            msg += "\n"+k.getElement();

        return msg;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
        notifyObservers();
    }
}
