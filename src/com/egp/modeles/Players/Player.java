package com.egp.modeles.Players;

import com.egp.constants.enums.Etat;
import com.egp.constants.enums.PlayerType;
import com.egp.constants.enums.Type;
import com.egp.modeles.Artefact;
import com.egp.modeles.Events.Key;
import com.egp.modeles.Zone;
import com.egp.observer.Observable;

import java.util.ArrayList;

public class Player extends Observable {
    private final int ID;
    private final PlayerType type;

    private Zone position;
    private boolean isCurrent = false;
    private int actions = 3;

    private final ArrayList<Key> keys;
    private final ArrayList<Artefact> artefacts;

    public Player(Zone position, int ID, PlayerType type){
        this.position = position;
        this.ID = ID;
        this.type = type;

        this.keys = new ArrayList<>();
        this.artefacts = new ArrayList<>();
    }

    public int getActions() {
        return actions;
    }

    public void setActions(int actions){
        this.actions = actions;
    }

    public Zone getPosition(){
        return this.position;
    }

    public void setPosition(Zone p){
        this.position = p;
    }

    public int getID(){ return this.ID; }

    public PlayerType getType() { return this.type; }

    public ArrayList<Key> getKeys(){ return this.keys; }

    public void addKey(Key cle){
        this.keys.add(cle);
    }

    public ArrayList<Artefact> getArtefacts(){ return this.artefacts; }

    public void addArtefacts(Artefact artefact){
        this.getArtefacts().add(artefact);
        notifyObservers();
    }

    public void removeKey(Type type){
        this.getKeys().removeIf(k -> k.getElement() == type);
        notifyObservers();
    }

    public boolean atteignable(Zone c){
        if (c.etat == Etat.Submergee)
            return false;

        if (this.getPosition().x == c.x)
            return this.getPosition().y == c.y - 1 || this.getPosition().y == c.y + 1;

        if (this.getPosition().y == c.y)
            return this.getPosition().x == c.x - 1 || this.getPosition().x == c.x + 1;

        return false;
    }

    public boolean recuperable(Zone c){
        if(c.etat == Etat.Submergee || this.getPosition().x != c.x || this.getPosition().y != c.y)
            return false;

        for (Key k : this.getKeys()){
            if (c.type == k.getElement())
                return true;
        }
        return false;
    }

    public boolean isDead(){
        return this.getPosition().etat == Etat.Submergee;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
        notifyObservers();
    }

    public String toString(){
        StringBuilder msg = new StringBuilder("Joueur no : " + this.ID + "\nNombre d'actions restantes : " +
                this.actions + "\nNombre d'artefacts : " + this.artefacts.size() +
                "\nType de clé disponible : ");

        for(Key k : this.keys)
            msg.append("\n").append(k.getElement());

        return msg.toString();
    }

}
