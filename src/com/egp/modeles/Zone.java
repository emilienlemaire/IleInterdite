package com.egp.modeles;
import com.egp.constants.*;


public class Zone {
    public Etat etat;
    public Type type;
    public final int x;
    public final int y;
    public static Zone zone_hover = null;

    public Zone(Etat etat, Type type, int x, int y){
        this.etat = etat;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public static Zone getHover(){
        return zone_hover;
    }

    public static void setHover(Zone hover){
        zone_hover = hover;
    }

    public String toString(){
        String msg = "Case d'état " + this.etat.toString() + ", de type "
                + this.type.toString() + " et de coordonées (" + x + ", " + y + ")";
        return msg;
    }
}

