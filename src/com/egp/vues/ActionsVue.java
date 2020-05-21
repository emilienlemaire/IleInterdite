package com.egp.vues;

import com.egp.modeles.Modele;
import com.egp.modeles.Zone;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class ActionsVue extends ContextMenu {

    public ActionsVue(Zone zone, MouseEvent mouseEvent, GrilleVue grilleVue, Modele modele, ArrayList<String> actions) {
        super();

        if (actions.size() < 1) {
            throw new IllegalArgumentException("Actions must contains at least one action");
        }

        ArrayList<MenuItem> menuItems = new ArrayList<>();

        for (String action :
                actions) {
            if (action.equals("move")) {
                MenuItem moveItem = new MenuItem("Se déplacer");
                moveItem.setOnAction(actionEvent -> modele.deplace(zone));
                menuItems.add(moveItem);
            }

            if (action.equals("dry")) {
                MenuItem dryItem = new MenuItem("Assécher");
                dryItem.setOnAction(actionEvent -> modele.asseche(zone));
                menuItems.add(dryItem);
            }

            if (action.equals("get")) {
                MenuItem getItem = new MenuItem("Récupérer");
                getItem.setOnAction(actionEvent -> modele.recupere(zone));
                menuItems.add(getItem);
            }
        }

        this.getItems().addAll(menuItems);

        this.show(grilleVue, mouseEvent.getScreenX(), mouseEvent.getScreenY());
    }
}
