package com.egp.modeles;
import com.egp.constants.enums.Etat;
import com.egp.constants.enums.Type;
import com.egp.modeles.Players.Player;
import com.egp.observer.Observable;

import java.util.ArrayList;


public class Zone extends Observable {
    public Etat etat;
    public Type type;
    public final int x;
    public final int y;
    public static Zone zone_hover = null;
    private final ArrayList<Player> players = new ArrayList<>();

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

    public void innonde() {
        if (this.etat == Etat.Normale)
            this.etat = Etat.Innondee;
        else if (this.etat == Etat.Innondee)
            this.etat = Etat.Submergee;

        this.notifyObservers();
    }

    public void asseche() {
        this.etat = Etat.Normale;
        this.notifyObservers();
    }

    public void addPlayer(Player player) {
        this.players.add(player);
        this.notifyObservers();
    }

    public void deletePlayer(Player player) {
        this.players.remove(player);
        this.notifyObservers();
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public boolean hasCurrent() {
        for (Player player :
                this.players) {
            if (player.isCurrent())
                return true;
        }

        return false;
    }

    public void setType(Type type) {
        this.type = type;
        this.notifyObservers();
    }

    public String toString(){
        return "Case d'état " + this.etat.toString() + ", de type "
                + this.type.toString() + " et de coordonées (" + x + ", " + y + ")";
    }
}

