package com.egp.vues;

import com.egp.controllers.Controller;
import com.egp.modeles.Modele;
import com.egp.modeles.Zone;
import com.egp.observer.Observer;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ZoneVue extends Pane {

    private final Modele modele;
    private final Zone zone;
    private final MainVue mainVue;


    public ZoneVue(Modele modele, Zone zone, int nbPlayers, int currentPlayer, MainVue mainVue) {
        this.modele = modele;
        this.zone = zone;
        this.mainVue = mainVue;

        Group typeGrp = zone.type.getImage();
        Group etatGrp = zone.etat.getImage(typeGrp);

        GridPane playerPane = new GridPane();

        if (nbPlayers > 0) {
            for (int i = 0; i < nbPlayers; i++) {
                Image player =
                        (i + 1) == currentPlayer ? new Image(new File("resources/players/player.png").toURI().toString()) :
                        new Image(new File("resources/players/not-playing.png").toURI().toString());

                ImageView playerView = new ImageView(player);
                if (nbPlayers > 1) {
                    playerView.setFitHeight(16);
                    playerView.setFitWidth(16);
                    int v = i > 1 ? 1 : 0;
                    int h = i % 2 == 1 ? 1 : 0;
                    playerPane.add(playerView, v, h);
                } else {
                    playerView.setFitHeight(32);
                    playerView.setFitWidth(32);
                    playerPane.add(playerView, 0, 0);
                }
            }
        }

        etatGrp.getChildren().add(playerPane);

        this.getChildren().add(etatGrp);
    }

    public void setGoodCursor() {
        mainVue.setCursor(Cursor.HAND);
    }

    public void setBadCursor() {
        Image cursor = new Image(new File("resources/cursors/invalid32x32.gif").toURI().toString());
        mainVue.setCursor(new ImageCursor(cursor));
    }

    public Zone getZone() { return this.zone; }
}
