package com.egp.vues;

import com.egp.constants.Images;
import com.egp.modeles.Key;
import com.egp.modeles.Player;
import com.egp.observer.Observer;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.io.File;
import java.util.ArrayList;

public class PlayerVue extends FlowPane implements Observer{
    private final Player player;
    private boolean isCurrent = false;
    private ArrayList<Key> keys;
    private final ArrayList<ImageView> keyViews;
    private ImageView playerView;

    public PlayerVue(Player player) {
        super();
        this.player = player;
        this.keys = player.getKeys();
        this.isCurrent = this.player.isCurrent();

        this.player.addObserver(this);

        playerView = player.isCurrent() ?
                new ImageView(Images.playerNormal) :
                new ImageView(Images.playerNormalBW);
        playerView.setFitWidth(64);
        playerView.setFitHeight(64);

        this.getChildren().add(playerView);

        keyViews = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            ImageView keyView = new ImageView(Images.keys.get(i));

            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(-1);
            keyView.setEffect(colorAdjust);
            this.keyViews.add(keyView);
        }

        for (Key key :
                keys) {
            ImageView keyView = keyViews.get(keyToInt(key));
            keyView.setEffect(null);
        }

        this.getChildren().addAll(keyViews);
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

        for (Key key :
                keys) {
            ImageView keyView = keyViews.get(keyToInt(key));
            keyView.setEffect(null);
        }
    }

    private int keyToInt(Key key) {
        switch (key.getElement()) {
            case Normale:
            case Heliport:
                break;
            case Air:
                return 3;
            case Terre:
                return 2;
            case Eau:
                return 1;
            case Feu:
                return 0;
        }
        return -1;
    }
}
