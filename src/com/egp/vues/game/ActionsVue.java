package com.egp.vues.game;

import com.egp.modeles.Modele;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class ActionsVue extends ContextMenu {

    private static final MouseEvent mouseEntered =  new MouseEvent(MouseEvent.MOUSE_ENTERED, 1, 2, 3, 4,
            MouseButton.NONE, 5,
            true, true,  true, true, true, true, true, true, true, true, null);
    private static final MouseEvent mouseExited =  new MouseEvent(MouseEvent.MOUSE_EXITED, 1, 2, 3, 4,
            MouseButton.NONE, 5,
            true, true,  true, true, true, true, true, true, true, true, null);

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
                    modele.deplace(zoneVue.getZone());
                    zoneVue.fireEvent(mouseEntered);
                    zoneVue.fireEvent(mouseExited);
                });
                menuItems.add(moveItem);
            }

            if (action.equals("dry")) {
                MenuItem dryItem = new MenuItem("Assécher");
                dryItem.setOnAction(actionEvent -> {
                    modele.asseche(zoneVue.getZone());
                    zoneVue.fireEvent(mouseEntered);
                    zoneVue.fireEvent(mouseExited);
                });
                menuItems.add(dryItem);
            }

            if (action.equals("get")) {
                MenuItem getItem = new MenuItem("Récupérer");
                getItem.setOnAction(actionEvent -> {
                    modele.recupere(zoneVue.getZone());
                    zoneVue.fireEvent(mouseEntered);
                    zoneVue.fireEvent(mouseExited);
                });
                menuItems.add(getItem);
            }
        }

        this.getItems().addAll(menuItems);

        this.show(grilleVue, mouseEvent.getScreenX(), mouseEvent.getScreenY());
    }
}
