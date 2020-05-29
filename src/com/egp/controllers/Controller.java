package com.egp.controllers;

import com.egp.constants.Sounds;
import com.egp.modeles.Modele;
import com.egp.modeles.Zone;
import com.egp.vues.game.ActionsVue;
import com.egp.vues.game.GrilleVue;
import com.egp.vues.end.LostVue;
import com.egp.vues.game.ZoneVue;
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
    }

    public void finDeTour() {
        if (modele.Event()){
            gotKey();
        }
        modele.inondeCases();

        if (modele.checkLoose()){
            LostVue lostVue = new LostVue(grilleVue.getMainVue());
        }

        modele.incrementeTour();
    }


    /*
    * Il faut vérifier
    * que la case soit atteignable ou asséchable,
    * si elle est que l'une des deux alors tu appelles
    * modele.deplace(zone) ou modele.asseche(zone)
    * si elle est les deux alors tu crée une nouvelle actionVue
    */
    public void zoneClicked(ZoneVue zoneVue, MouseEvent mouseEvent) {

        if (actionsVue != null)
            actionsVue.hide();

        ArrayList<String> actions = new ArrayList<>();

        if (this.modele.atteignable(zoneVue.getZone())) {
            actions.add("move");
        }
        if (this.modele.assechable(zoneVue.getZone())) {
            if (actionsVue != null) {
                actionsVue.hide();
            }
            actions.add("dry");
        }

        if (this.modele.recuperable(zoneVue.getZone())) {
            actions.add("get");
        }

        if (!actions.isEmpty())
            actionsVue = new ActionsVue(zoneVue, mouseEvent, this.grilleVue, this.modele, actions);
    }


    /*
     * Si la zone est atteignable, asséchable ou récupérable, alors
     * zoneVue.setGoodCursor()
     * sinon
     * zoneVue.setBadCursor()
     * */
    public void mouseEnteredZone(ZoneVue zoneVue) {

        Zone.setHover(zoneVue.getZone());
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

    public void gotKey() {
        MediaPlayer keyPlayer = new MediaPlayer(Sounds.key);
        keyPlayer.setAutoPlay(true);
        keyPlayer.setOnEndOfMedia(grilleVue.getMainVue()::hidePopup);
        grilleVue.getMainVue().showPopUp();
    }
}
