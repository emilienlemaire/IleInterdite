package com.egp.vues;

import com.egp.constants.Etat;
import com.egp.modeles.Modele;
import com.egp.modeles.Zone;
import com.egp.observer.Observer;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.ArrayList;

public class GrilleVue extends GridPane implements Observer {

    private Modele modele;
    private final int ROWS;
    private final int COLS;
    private ArrayList<ZoneVue> zoneVues;

    public GrilleVue(Modele modele) {
        this.modele = modele;
        ROWS = modele.getNbRows();
        COLS = modele.getNbCols();

        modele.addObserver(this);

        zoneVues = new ArrayList<>();
        ArrayList<Zone> zones = this.modele.getCases();
        for (int i = 0; i < COLS; i++) {
            for (int j = 0; j < ROWS; j++) {
                ZoneVue newZone = new ZoneVue(modele, zones.get(i * ROWS + j));
                this.add(newZone, i, j);
            }
        }

    }

    @Override
    public void update() {
        zoneVues = new ArrayList<>();
        ArrayList<Zone> zones = this.modele.getCases();
        for (int i = 0; i < COLS; i++) {
            for (int j = 0; j < ROWS; j++) {
                ZoneVue newZone = new ZoneVue(modele, zones.get(i * ROWS + j));
                this.add(newZone, i, j);
            }
        }
    }

    public void paintComponent(Graphics graphics) {
        for (int i = 0; i < COLS; i++) {
            for (int j = 0; j < ROWS; j++) {
                paint(graphics, zoneVues.get(i * ROWS + j));
            }
        }
    }

    private void paint(Graphics g, ZoneVue zoneVue) {
        zoneVue.paint(g);
    }
}
