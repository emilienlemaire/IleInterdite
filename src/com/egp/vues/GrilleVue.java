package com.egp.vues;

import com.egp.controllers.Controller;
import com.egp.modeles.Modele;
import com.egp.modeles.Player;
import com.egp.modeles.Zone;
import com.egp.observer.Observer;
import javafx.scene.layout.GridPane;

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
        this.mainVue = mainVue;
        ROWS = modele.getNbRows();
        COLS = modele.getNbCols();

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
        ArrayList<ZoneVue> zoneVues = new ArrayList<>();
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
                ZoneVue newZone = new ZoneVue(modele, zone, nbPlayers, currentPlayer, this.mainVue);

                newZone.setOnMouseClicked(mouseEvent -> controller.zoneClicked(zone, mouseEvent));
                newZone.setOnMouseEntered(mouseEvent -> controller.mouseEnteredZone(newZone));
                newZone.setOnMouseExited(mouseEvent -> controller.mouseExitedZone(newZone));

                this.add(newZone, i, j);
            }
        }
    }

}
