package com.egp.modeles;
import com.egp.constants.*;
import com.egp.observer.Observable;

import java.util.ArrayList;


public class Zone extends Observable {
    public Etat etat;
    public Type type;
    public final int x;
    public final int y;
    public static Zone zone_hover = null;
    private ArrayList<Player> players = new ArrayList<>();

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

    public void innonde() {
        if (this.etat == Etat.Normale)
            this.etat = Etat.Innondee;
        else if (this.etat == Etat.Innondee)
            this.etat = Etat.Submergee;

        notifyObservers();
    }

    public void asseche() {
        this.etat = Etat.Normale;
        notifyObservers();
    }

    public void addPlayer(Player player) {
        this.players.add(player);
        notifyObservers();
    }

    public void deletePlayer(Player player) {
        this.players.remove(player);
        notifyObservers();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public boolean hasCurrent() {
        for (Player player :
                players) {
            if (player.isCurrent())
                return true;
        }

        return false;
    }

    public void setType(Type type) {
        this.type = type;
        notifyObservers();
    }
}

