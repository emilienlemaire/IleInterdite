package com.egp.vues;

import javax.swing.*;

import com.egp.constants.Etat;
import com.egp.modeles.Modele;
import com.egp.modeles.Zone;
import com.egp.observer.Observer;

import java.awt.*;
import java.util.ArrayList;

public class GrilleVue extends JPanel implements Observer {

    private Modele modele;
    private final int ROWS;
    private final int COLS;
    private final ArrayList<ZoneVue> zoneVues;

    public GrilleVue(Modele modele) {
        this.modele = modele;
        ROWS = modele.getNbRows();
        COLS = modele.getNbCols();

        modele.addObserver(this);

        Dimension dim = new Dimension(COLS * 32, ROWS * 32);
        this.setPreferredSize(dim);

        zoneVues = new ArrayList<>();
        ArrayList<Zone> zones = this.modele.getCases();
        for (int i = 0; i < COLS; i++) {
            for (int j = 0; j < ROWS; j++) {
                ZoneVue newZone = new ZoneVue(this.modele, zones.get(i * ROWS +j));
                zoneVues.add(newZone);
            }
        }

    }

    @Override
    public void update() {
        repaint();
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
