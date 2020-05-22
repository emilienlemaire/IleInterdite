package com.egp.vues;

import com.egp.modeles.Modele;
import com.egp.modeles.Zone;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class ActionsVue extends ContextMenu {

    public ActionsVue(ZoneVue zoneVue, MouseEvent mouseEvent, GrilleVue grilleVue, Modele modele, ArrayList<String> actions) {
        super();

        if (actions.size() < 1) {
            throw new IllegalArgumentException("Actions must contains at least one action");
        }

        ArrayList<MenuItem> menuItems = new ArrayList<>();

        for (String action :
                actions) {
            if (action.equals("move")) {
                MenuItem moveItem = new MenuItem("Se déplacer");
                moveItem.setOnAction(actionEvent -> {
                    zoneVue.setBadCursor();
                    modele.deplace(zoneVue.getZone());
                });
                menuItems.add(moveItem);
            }

            if (action.equals("dry")) {
                MenuItem dryItem = new MenuItem("Assécher");
                dryItem.setOnAction(actionEvent -> {
                    zoneVue.setBadCursor();
                    modele.asseche(zoneVue.getZone());
                });
                menuItems.add(dryItem);
            }

            if (action.equals("get")) {
                MenuItem getItem = new MenuItem("Récupérer");
                getItem.setOnAction(actionEvent -> {
                    zoneVue.setBadCursor();
                    modele.recupere(zoneVue.getZone());
                });
                menuItems.add(getItem);
            }
        }

        this.getItems().addAll(menuItems);


        this.show(grilleVue, mouseEvent.getScreenX(), mouseEvent.getScreenY());
    }
}
