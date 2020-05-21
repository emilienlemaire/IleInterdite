package com.egp.vues;

import com.egp.constants.Images;
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
    private int keys;
    private final ArrayList<ImageView> keyViews;
    private ImageView playerView;

    public PlayerVue(Player player) {
        super();
        this.player = player;
        this.keys = player.getKeys().size();
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

        for (int i = 0; i< player.getKeys().size(); i++) {
            Image key = new Image(new File("resources/keys/cle" +
                    (i == 0 ? "" : (i + 1)) + ".png").toURI().toString());
            ImageView keyView = new ImageView(key);

            this.keyViews.set(i, keyView);
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

        if (this.keys != this.player.getKeys().size()) {
            this.keys = this.player.getKeys().size();
            ImageView keyView = keyViews.get(this.keys - 1);
            keyView.setEffect(null);
        }
    }
}
