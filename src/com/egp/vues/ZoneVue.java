package com.egp.vues;

import com.egp.modeles.Modele;
import com.egp.modeles.Zone;
import com.egp.observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ZoneVue extends JPanel implements Observer {

    private final Modele modele;
    private final Zone zone;

    public ZoneVue(Modele modele, Zone zone) {
        this.modele = modele;
        this.zone = zone;
    }

    @Override
    public void update() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        paint(g);
    }

    public void paint(Graphics g) {
        BufferedImage img = zone.type.getImage();
        BufferedImage finalImg = zone.etat.getImage(img);

        g.drawImage(finalImg, zone.x * 32, zone.y * 32, null);
    }
}
