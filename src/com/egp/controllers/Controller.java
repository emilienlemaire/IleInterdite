package com.egp.controllers;

import com.egp.modeles.Modele;
import com.egp.modeles.Zone;
import com.egp.vues.ActionsVue;
import com.egp.vues.GrilleVue;
import com.egp.vues.ZoneVue;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Controller {

    private final Modele modele;
    private final GrilleVue grilleVue;
    private ActionsVue actionsVue = null;


    public Controller(Modele modele, GrilleVue grilleVue) {
        this.modele = modele;
        this.grilleVue = grilleVue;
    }

    public void finDeTour() {
        modele.inondeCases();
        modele.dropCles();
        modele.incrementeTour();
    }


    /*
    * Il faut vérifier
    * que la case soit atteignable ou asséchable,
    * si elle est que l'une des deux alors tu appelles
    * modele.deplace(zone) ou modele.asseche(zone)
    * si elle est les deux alors tu crée une nouvelle actionVue
    */
    public void zoneClicked(Zone zone, MouseEvent mouseEvent) {

        if (this.modele.atteignable(zone) && this.modele.assechable(zone)) {
            if (actionsVue != null) {
                actionsVue.hide();
            }
            actionsVue = new ActionsVue(zone, mouseEvent, this.grilleVue, this.modele, true, true);
        }
        else if (this.modele.assechable(zone)) {
            if (actionsVue != null) {
                actionsVue.hide();
            }
            actionsVue = new ActionsVue(zone, mouseEvent, this.grilleVue, this.modele, false, true);
        }

        else if (this.modele.atteignable(zone)) {
            if (actionsVue != null) {
                actionsVue.hide();
            }
            actionsVue = new ActionsVue(zone, mouseEvent, this.grilleVue, this.modele, true, false);
        } else {
            if (actionsVue != null) {
                actionsVue.hide();
            }
        }
    }


    /*
     * Si la zone est atteignable ou asséchable, alors
     * zoneVue.setGoodCursor()
     * sinon
     * zoneVue.setBadCursor()
     * */
    public void mouseEnteredZone(ZoneVue zoneVue) {

        Zone.setHover(zoneVue.getZone());
        zoneVue.setHover();
        if (this.modele.atteignable(zoneVue.getZone()) || this.modele.assechable(zoneVue.getZone()))
            zoneVue.setGoodCursor();
        else
            zoneVue.setBadCursor();
    }

    public void mouseExitedZone(ZoneVue zoneVue) {
        zoneVue.deleteHover();
        zoneVue.setGoodCursor();
    }

    public void gotKey() {
        grilleVue.getMainVue().showPopUp();
    }
}
