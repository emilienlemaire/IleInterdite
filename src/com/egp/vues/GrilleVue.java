package com.egp.vues;

import javax.swing.*;

import com.egp.modeles.Modele;
import com.egp.observer.Observer;

import java.awt.*;

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
        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <20; j++) {
                paint(graphics, i, j, (i-1)*TAILLE, (j-1)*TAILLE);
            }
        }
    }

    private void paint(Graphics g, int i, int j, int x, int y) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, TAILLE, TAILLE);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, TAILLE, TAILLE);
    }
}
