package com.egp.controllers;

import com.egp.constants.Sounds;
import com.egp.modeles.Events.Event;
import com.egp.modeles.Modele;
import com.egp.modeles.Zone;
import com.egp.vues.end.LostVue;
import com.egp.vues.end.WinView;
import com.egp.vues.game.ActionsVue;
import com.egp.vues.game.GrilleVue;
import com.egp.vues.game.ZoneVue;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;

public class Controller {

    private final Modele modele;
    private final GrilleVue grilleVue;
    private ActionsVue actionsVue = null;


    public Controller(Modele modele, GrilleVue grilleVue) {
        this.modele = modele;
        this.grilleVue = grilleVue;
        this.modele.setController(this);
    }

    public void finDeTour() {
        Event e = modele.eventDrop();
        modele.inondeCases();

        if (modele.checkLoose())
            new LostVue(grilleVue.getMainVue());
        else {

            if (e.getName().equals("Montée")) {
                risingWater();
            } else if (!e.getName().equalsIgnoreCase("Rien")) {
                gotKey(e.getName());
            }

            modele.incrementeTour();
        }
    }


    /*
    * Il faut vérifier
    * que la case soit atteignable ou asséchable,
    * si elle est que l'une des deux alors tu appelles
    * modele.deplace(zone) ou modele.asseche(zone)
    * si elle est les deux alors tu crée une nouvelle actionVue
    */
    public void zoneClicked(ZoneVue zoneVue, MouseEvent mouseEvent) {

        if (this.actionsVue != null)
            this.actionsVue.hide();

        ArrayList<String> actions = new ArrayList<>();

        if (this.modele.atteignable(zoneVue.getZone())) {
            actions.add("move");
        }
        if (this.modele.assechable(zoneVue.getZone())) {
            if (this.actionsVue != null) {
                this.actionsVue.hide();
            }
            actions.add("dry");
        }

        if (this.modele.recuperable(zoneVue.getZone())) {
            actions.add("get");
        }

        if (!actions.isEmpty())
            this.actionsVue = new ActionsVue(zoneVue, mouseEvent, this.grilleVue, this.modele, actions);
    }


    /*
     * Si la zone est atteignable, asséchable ou récupérable, alors
     * zoneVue.setGoodCursor()
     * sinon
     * zoneVue.setBadCursor()
     * */
    public void mouseEnteredZone(ZoneVue zoneVue) {

        zoneVue.setHover();
        if (this.modele.atteignable(zoneVue.getZone()) || this.modele.assechable(zoneVue.getZone()) ||
            this.modele.recuperable(zoneVue.getZone()))
            zoneVue.setGoodCursor();
        else
            zoneVue.setBadCursor();
    }

    public void mouseExitedZone(ZoneVue zoneVue) {
        zoneVue.deleteHover();
        zoneVue.setDefaultCursor();
    }

    public void gotKey(String el) {
        String msg = "Vous avez reçu la clé ";
        switch (el){
            case "Terre":
                msg += "de la ";
                break;
            case "Feu":
                msg += "du ";
                break;
            default:
                msg += "de l'";
        }

        msg += el;
        MediaPlayer keyPlayer = new MediaPlayer(Sounds.key);
        keyPlayer.setAutoPlay(true);
        keyPlayer.setOnEndOfMedia(this.grilleVue.getMainVue()::hidePopup);
        this.grilleVue.getMainVue().showPopUp(new Label(msg));
    }

    /**
     * Averti avec un popup un joueur lorsque la case sur laquelle il se trouve est innondée
     */
    public void risingWater() {
        MediaPlayer keyPlayer = new MediaPlayer(Sounds.danger);
        keyPlayer.setAutoPlay(true);
        keyPlayer.setOnEndOfMedia(this.grilleVue.getMainVue()::hidePopup);
        this.grilleVue.getMainVue().showPopUp(new Label("Attention, l'eau monte !"));
    }

    public void gameWon() {
        new WinView(this.grilleVue.getMainVue());
    }
}
