package com.egp.vues;

import com.egp.constants.Images;
import com.egp.modeles.Modele;
import com.egp.modeles.Player;
import com.egp.modeles.Zone;
import com.egp.observer.Observer;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.util.ArrayList;

public class ZoneVue extends Pane implements Observer {

    private final Modele modele;
    private final Zone zone;
    private final MainVue mainVue;
    private final Image hover = new Image(new File("resources/cases/hover2.png").toURI().toString());
    private final ImageView hoverView = new ImageView(hover);
    private final Group typeGrp;
    private Group etatGrp;
    private GridPane playerPane;

    public ZoneVue(Modele modele, Zone zone, MainVue mainVue) {
        this.modele = modele;
        this.zone = zone;
        this.mainVue = mainVue;
        this.typeGrp = zone.type.getImage();
        this.etatGrp = zone.etat.getImage(typeGrp);

        ArrayList<Player> players = this.modele.getPlayers();

        for (Player player : players) {
            player.addObserver(this);
        }

        zone.addObserver(this);

        playerPane = new GridPane();

        int nbPlayers = this.zone.getPlayers().size();

        for (int i = 0; i < nbPlayers; i++) {
            ImageView player =
                    this.zone.getPlayers().get(i).isCurrent() ?
                            new ImageView(Images.playerNormal) :
                            new ImageView(Images.playerNormalBW);

            if (nbPlayers > 1) {
                player.setFitHeight(16);
                player.setFitWidth(16);
                int v = i > 1 ? 1 : 0;
                int h = i % 2 == 1 ? 1 : 0;
                playerPane.add(player, v, h);
            } else {
                player.setFitHeight(32);
                player.setFitWidth(32);
                playerPane.add(player, 0, 0);
            }
        }

        etatGrp.getChildren().add(playerPane);

        this.getChildren().add(etatGrp);
    }

    public void setHover() {
        this.getChildren().add(hoverView);
    }

    public void deleteHover() {
        this.getChildren().remove(hoverView);
    }

    public void setGoodCursor() {
        mainVue.setCursor(Cursor.HAND);
    }

    public void setBadCursor() {
        Image cursor = new Image(new File("resources/cursors/invalid32x32.gif").toURI().toString());
        mainVue.setCursor(new ImageCursor(cursor));
    }

    public Zone getZone() { return this.zone; }

    @Override
    public void update() {

        this.getChildren().remove(etatGrp);
        etatGrp = zone.etat.getImage(typeGrp);
        this.playerPane.getChildren().clear();

        int nbPlayers = this.zone.getPlayers().size();
        for (int i = 0; i < nbPlayers; i++) {
            ImageView playerView;
            if (this.zone.hasCurrent()) {
                playerView =
                        this.zone.getPlayers().get(i).isCurrent() ?
                                new ImageView(Images.playerNormal) :
                                new ImageView(Images.playerNormalBW);
            } else {
                playerView = new ImageView(Images.playerNormalBW);
            }
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
        this.getChildren().add(etatGrp);
    }
}
