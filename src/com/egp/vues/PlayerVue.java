package com.egp.vues;

import com.egp.constants.Images;
import com.egp.modeles.Artefact;
import com.egp.modeles.Key;
import com.egp.modeles.Player;
import com.egp.observer.Observer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class PlayerVue extends FlowPane implements Observer{
    private final Player player;
    private boolean isCurrent;
    private final ArrayList<Key> keys;
    private final ArrayList<Artefact> artifacts;
    private final ArrayList<VBox> artKeyViews;
    private ImageView playerView;

    public PlayerVue(Player player) {
        super();
        this.player = player;
        this.keys = player.getKeys();
        this.artifacts = player.getArtefacts();
        this.isCurrent = this.player.isCurrent();

        this.player.addObserver(this);

        playerView = player.isCurrent() ?
                new ImageView(Images.playerNormal) :
                new ImageView(Images.playerNormalBW);
        playerView.setFitWidth(64);
        playerView.setFitHeight(64);

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

        for (Key key :
                keys) {
            VBox artKeyView = artKeyViews.get(keyToInt(key));
            artKeyView.getChildren().get(0).setEffect(null);
        }

        this.getChildren().addAll(artKeyViews);
    }

    @Override
    public void update() {
        if (this.isCurrent != this.player.isCurrent()) {
            this.getChildren().remove(playerView);
            this.isCurrent = this.player.isCurrent();
            this.playerView = this.player.isCurrent() ?
                    new ImageView(Images.playerNormal) :
                    new ImageView(Images.playerNormalBW);
            this.playerView.setFitWidth(64);
            this.playerView.setFitHeight(64);
            this.getChildren().add(0, playerView);
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

        for (Key key :
                keys) {
            VBox artKeyView = artKeyViews.get(keyToInt(key));
            artKeyView.getChildren().get(0).setEffect(null);
        }

        for (Artefact artifact :
                artifacts) {
            VBox artKeyView = artKeyViews.get(artefactToInt(artifact));
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
