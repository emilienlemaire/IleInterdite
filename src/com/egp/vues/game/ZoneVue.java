package com.egp.vues.game;

import com.egp.constants.Images;
import com.egp.constants.enums.Type;
import com.egp.modeles.Modele;
import com.egp.modeles.Players.Player;
import com.egp.modeles.Zone;
import com.egp.observer.Observer;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ZoneVue extends Pane implements Observer {

    private final MainVue mainVue;
    private final Zone zone;
    private final GridPane playerPane;
    private final ImageView hoverView = new ImageView(Images.hover);
    private Group typeGrp;
    private Group etatGrp;
    private Type type;

    public ZoneVue(Modele modele, Zone zone, MainVue mainVue) {
        this.zone = zone;
        this.type = zone.type;
        this.mainVue = mainVue;
        this.typeGrp = zone.type.getImage();
        this.etatGrp = zone.etat.getImage(typeGrp);

        ArrayList<Player> players = modele.getPlayers();

        for (Player player : players) {
            player.addObserver(this);
        }

        zone.addObserver(this);

        playerPane = new GridPane();

        int nbPlayers = this.zone.getPlayers().size();

        for (int i = 0; i < nbPlayers; i++) {
            Player player = this.zone.getPlayers().get(i);
            ImageView playerView =
                    player.isCurrent() ?
                            new ImageView(player.getType().getImageCurrent()) :
                            new ImageView(player.getType().getImageNotCurrent());

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
        mainVue.setCursor(new ImageCursor(Images.cursor));
    }

    public void setDefaultCursor() {
        mainVue.setCursor(Cursor.DEFAULT);
    }

    public Zone getZone() { return this.zone; }

    public MainVue getMainVue() {
        return mainVue;
    }

    @Override
    public void update() {

        this.getChildren().remove(etatGrp);
        if (this.zone.type != this.type) {
            this.type = this.zone.type;
            this.typeGrp = this.type.getImage();
            this.etatGrp = this.zone.etat.getImage(typeGrp);
            this.etatGrp.getChildren().add(playerPane);
        } else {
            this.etatGrp = this.zone.etat.getImage(typeGrp);
        }
        this.playerPane.getChildren().clear();

        int nbPlayers = this.zone.getPlayers().size();
        for (int i = 0; i < nbPlayers; i++) {
            Player player = this.zone.getPlayers().get(i);
            ImageView playerView;
            if (this.zone.hasCurrent()) {
                playerView =
                        player.isCurrent() ?
                                new ImageView(player.getType().getImageCurrent()) :
                                new ImageView(player.getType().getImageNotCurrent());
            } else {
                playerView = new ImageView(player.getType().getImageNotCurrent());
            }
            if (nbPlayers > 1) {
                playerView.setFitHeight(16);
                playerView.setFitWidth(16);
                int v = i > 1 ? 1 : 0;
                int h = i % 2 == 1 ? 1 : 0;
                this.playerPane.add(playerView, v, h);
            } else {
                playerView.setFitHeight(32);
                playerView.setFitWidth(32);
                this.playerPane.add(playerView, 0, 0);
            }
        }
        this.getChildren().add(etatGrp);
    }
}