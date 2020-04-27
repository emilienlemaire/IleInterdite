package com.egp.modeles;
import com.egp.constants.*;


public class Zone {
    public Etat etat;
    public Type type;
    public final int x;
    public final int y;

    public Zone(Etat etat, Type type, int x, int y){
        this.etat = etat;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public String toString(){
        String msg = "Case d'état " + this.etat.toString() + ", de type "
                + this.type.toString() + " et de coordonées (" + x + ", " + y + ")";
        return msg;
    }
}

