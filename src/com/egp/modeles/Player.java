package com.egp.modeles;

import com.egp.constants.Etat;

public class Player {
    private Zone position;
    private final int ID;
    private int actions;

    public Player(Zone position, int ID){
        this.position = position;
        this.ID = ID;
        this.actions = 3;
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

    public boolean atteignable(Zone c){

        if (c.etat == Etat.Submergee)
            return false;

        if (this.position.x == c.x)
            return this.position.y == c.y - 1 || this.position.y == c.y + 1;

        if (this.position.y == c.y)
            return this.position.x == c.x - 1 || this.position.x == c.x + 1;

        return false;
    }

}
