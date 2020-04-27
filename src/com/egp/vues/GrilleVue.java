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
    private final static int TAILLE = 50;

    public GrilleVue(Modele modele) {
        this.modele = modele;

        modele.addObserver(this);

        Dimension dim = new Dimension(TAILLE * 20, TAILLE * 20);

        this.setPreferredSize(dim);

    }

    @Override
    public void update() {
        repaint();
    }

    public void paintComponent(Graphics graphics) {
        ArrayList<Zone> zones = this.modele.getCases();
        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <20; j++) {
                paint(graphics, zones.get((i-1) * 20 + (j-1)).etat, i, j, (i-1)*TAILLE, (j-1)*TAILLE);
            }
        }
    }

    private void paint(Graphics g, Etat e, int i, int j, int x, int y) {

        switch (e) {
            case Normale:
                g.setColor(Color.GREEN);
                break;
            case Submergee:
                g.setColor(new Color(50, 100, 255));
                break;
            case Innondee:
                g.setColor(new Color(0, 0, 255));
                break;
            default:
                g.setColor(Color.GREEN);
                break;
        }

        g.fillRect(x, y, TAILLE, TAILLE);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, TAILLE, TAILLE);
    }
}
