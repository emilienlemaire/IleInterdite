package com.egp.modeles;

import com.egp.constants.Etat;

public class Player {
    private Zone position;
    private final int ID;
    private int actions = 3;
    private int cles = 0;

    public Player(Zone position, int ID){
        this.position = position;
        this.ID = ID;
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

    public int getCles(){ return this.cles; }

    public void setCles(int nbcles){
        this.cles = nbcles;
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
        return "Joueur no : " + this.ID + "\nNombre d'actions restantes : " + this.actions + "\nNombre de clés : " + this.cles;
    }

}
