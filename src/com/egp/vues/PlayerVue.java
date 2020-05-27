package com.egp.vues;

import com.egp.constants.Images;
import com.egp.modeles.Artefact;
import com.egp.modeles.Key;
import com.egp.modeles.Modele;
import com.egp.modeles.Players.Player;
import com.egp.observer.Observer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class PlayerVue extends HBox implements Observer{
    private final Player player;
    private boolean isCurrent;
    private ArrayList<Key> keys;
    private ArrayList<Artefact> artifacts;
    private final ArrayList<VBox> artKeyViews;
    private ImageView playerView;
    private double maxSize;

    public PlayerVue(Modele modele, Player player) {
        super();
        this.player = player;
        this.keys = player.getKeys();
        this.artifacts = player.getArtefacts();
        this.isCurrent = this.player.isCurrent();
        this.maxSize = ((modele.getNbCols() * 32.) - 60.) / (double)modele.getPlayers().size();

        if (this.maxSize > 64.)
            this.maxSize = 64.;

        this.player.addObserver(this);

        playerView = player.isCurrent() ?
                new ImageView(Images.playerNormal) :
                new ImageView(Images.playerNormalBW);
        playerView.setFitWidth(this.maxSize);
        playerView.setFitHeight(this.maxSize);

        this.getChildren().add(playerView);

        this.setAlignment(Pos.CENTER_LEFT);

        artKeyViews = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            ImageView artView = new ImageView(Images.artifacts.get(i));
            ImageView keyView = new ImageView(Images.keys.get(i));

            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(-1);
            artView.setEffect(colorAdjust);
            keyView.setEffect(colorAdjust);

            VBox artKeyView = new VBox();
            artKeyView.setAlignment(Pos.CENTER_RIGHT);
            artKeyView.getChildren().addAll(artView, keyView);
            this.artKeyViews.add(artKeyView);
        }

        for (Artefact artifact :
                artifacts) {
            VBox artKeyView = artKeyViews.get(artefactToInt(artifact));
            artKeyView.getChildren().get(0).setEffect(null);
        }

        for (Key key :
                keys) {
            VBox artKeyView = artKeyViews.get(keyToInt(key));
            artKeyView.getChildren().get(1).setEffect(null);
        }

        this.getChildren().addAll(artKeyViews);
    }

    @Override
    public void update() {
        this.artifacts = this.player.getArtefacts();
        this.keys = this.player.getKeys();
        if (this.isCurrent != this.player.isCurrent()) {
            this.getChildren().remove(playerView);
            this.isCurrent = this.player.isCurrent();
            this.playerView = this.player.isCurrent() ?
                    new ImageView(Images.playerNormal) :
                    new ImageView(Images.playerNormalBW);
            this.playerView.setFitWidth(this.maxSize);
            this.playerView.setFitHeight(this.maxSize);

            if (this.player.isDead()) {
                ColorAdjust adjust = new ColorAdjust();

                Blend red = new Blend(
                        BlendMode.SRC_ATOP,
                        adjust,
                        new ColorInput(
                                0,
                                0,
                                this.maxSize,
                                this.maxSize,
                                Color.RED
                        )
                );

                red.setOpacity(0.5);

                this.playerView.effectProperty().set(red);
            }

            this.getChildren().add(0, this.playerView);
        }

        for (int i = 0; i < 4; i++) {
            ImageView artView = new ImageView(Images.artifacts.get(i));
            ImageView keyView = new ImageView(Images.keys.get(i));

            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(-1);
            artView.setEffect(colorAdjust);
            keyView.setEffect(colorAdjust);

            VBox artKeyView = new VBox();
            artKeyView.setPadding(new Insets(10));
            artKeyView.getChildren().addAll(artView, keyView);
            this.artKeyViews.add(artKeyView);
        }

        for (Artefact artifact :
                artifacts) {
            VBox artKeyView = this.artKeyViews.get(artefactToInt(artifact));
            artKeyView.getChildren().get(0).setEffect(null);
        }

        for (Key key :
                keys) {
            VBox artKeyView = this.artKeyViews.get(keyToInt(key));
            artKeyView.getChildren().get(1).setEffect(null);
        }

    }

    private int keyToInt(Key key) {
        switch (key.getElement()) {
            case Normale:
            case Heliport:
                break;
            case Air:
                return 2;
            case Terre:
                return 3;
            case Eau:
                return 1;
            case Feu:
                return 0;
        }
        return -1;
    }

    private int artefactToInt(Artefact artefact) {
        switch (artefact.getElement()) {
            case Normale:
            case Heliport:
                break;
            case Air:
                return 2;
            case Terre:
                return 3;
            case Eau:
                return 1;
            case Feu:
                return 0;
        }
        return -1;
    }
}
