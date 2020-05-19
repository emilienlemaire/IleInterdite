package com.egp.vues;

import com.egp.modeles.Modele;
import com.egp.modeles.Zone;
import com.egp.observer.Observer;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ZoneVue extends Pane implements Observer {

    private final Modele modele;
    private final Zone zone;

    public ZoneVue(Modele modele, Zone zone) {
        this.modele = modele;
        modele.addObserver(this);
        this.zone = zone;

        Group typeGrp = zone.type.getImage();
        Group etatGrp = zone.etat.getImage(typeGrp);

        this.getChildren().add(etatGrp);
    }

    @Override
    public void update() {
    }

    public void paintComponent(Graphics g) {
        paint(g);
    }

    public void paint(Graphics g) {
        /*BufferedImage typeImg = zone.type.getImage();
        BufferedImage etatImg = zone.etat.getImage(typeImg);

        g.drawImage(etatImg, zone.x * 32, zone.y * 32, null);*/
    }
}
