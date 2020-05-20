package com.egp.vues;

import com.egp.modeles.Modele;
import com.egp.modeles.Zone;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

public class ActionsVue extends ContextMenu {

    public ActionsVue(Zone zone, MouseEvent mouseEvent, GrilleVue grilleVue, Modele modele, boolean canMove, boolean canDry) {
        super();
        MenuItem moveItem = new MenuItem("Se déplacer");
        MenuItem dryItem = new MenuItem("Assécher");

        moveItem.setOnAction(actionEvent -> modele.deplace(zone));
        dryItem.setOnAction(actionEvent -> modele.asseche(zone));

        this.getItems().addAll(moveItem, dryItem);

        moveItem.setDisable(!canMove);
        dryItem.setDisable(!canDry);

        this.show(grilleVue, mouseEvent.getScreenX(), mouseEvent.getScreenY());
    }
}
