package com.egp.vues;

import com.egp.controllers.Controller;
import com.egp.modeles.Modele;
import com.egp.modeles.Player;
import com.egp.modeles.Zone;
import com.egp.observer.Observer;
import javafx.scene.Cursor;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Popup;


import java.util.ArrayList;

public class GrilleVue extends GridPane implements Observer {

    private final Modele modele;
    private final Controller controller;
    private final int ROWS;
    private final int COLS;
    private final MainVue mainVue;

    public GrilleVue(Modele modele, MainVue mainVue) {
        this.modele = modele;
        this.controller = new Controller(modele, this);
        modele.setController(this.controller);
        this.mainVue = mainVue;
        this.ROWS = modele.getNbRows();
        this.COLS = modele.getNbCols();

        modele.addObserver(this);

        generateGrid();
    }

    @Override
    public void update() {
        generateGrid();
    }

    public Controller getController() {
        return controller;
    }

    private void generateGrid() {
        ArrayList<Zone> zones = this.modele.getCases();
        ArrayList<Player> players = modele.getPlayers();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                Zone zone = zones.get(i * COLS + j);
                int nbPlayers = 0;
                int currentPlayer = 0;
                for (Player player :
                        players) {
                    if (player.getPosition() == zone) {
                        nbPlayers ++;
                        if (modele.getCurrentPlayer() == player) {
                            currentPlayer = nbPlayers;
                        }
                    }
                }
                ZoneVue newZone = new ZoneVue(zone, nbPlayers, currentPlayer, this.mainVue);

                newZone.setOnMouseClicked(mouseEvent -> controller.zoneClicked(zone, mouseEvent));
                newZone.setOnMouseEntered(mouseEvent -> controller.mouseEnteredZone(newZone));
                newZone.setOnMouseExited(mouseEvent -> controller.mouseExitedZone(newZone));

                this.add(newZone, i, j);
            }
        }
    }

    public MainVue getMainVue() {
        return mainVue;
    }
}
