package com.egp.vues.game;

import com.egp.controllers.Controller;
import com.egp.modeles.Modele;
import com.egp.modeles.Players.Player;
import com.egp.modeles.Zone;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class GrilleVue extends GridPane {

    private final Modele modele;
    private final Controller controller;
    private final int ROWS;
    private final int COLS;
    private final MainVue mainVue;
    ArrayList<Zone> zones;
    ArrayList<Player> players;
    ArrayList<ZoneVue> zoneVues = new ArrayList<>();

    public GrilleVue(Modele modele, MainVue mainVue) {
        this.modele = modele;
        this.controller = new Controller(modele, this);
        this.mainVue = mainVue;
        this.ROWS = modele.getNbRows();
        this.COLS = modele.getNbCols();
        this.zones = modele.getCases();
        this.players = modele.getPlayers();

        this.generateGrid();
    }

    public Controller getController() {
        return this.controller;
    }

    private void generateGrid() {
        for (int i = 0; i < this.ROWS; i++) {
            for (int j = 0; j < this.COLS; j++) {
                Zone zone = this.zones.get(i * this.COLS + j);

                ZoneVue newZone = new ZoneVue(modele, zone, this.mainVue);

                newZone.setOnMouseClicked(mouseEvent -> this.controller.zoneClicked(newZone, mouseEvent));
                newZone.setOnMouseEntered(mouseEvent -> this.controller.mouseEnteredZone(newZone));
                newZone.setOnMouseExited(mouseEvent -> this.controller.mouseExitedZone(newZone));

                this.zoneVues.add(newZone);

                this.add(newZone, i, j);
            }
        }
    }

    public MainVue getMainVue() {
        return mainVue;
    }
}
