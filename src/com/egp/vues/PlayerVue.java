package com.egp.vues;

import com.egp.modeles.Player;
import com.egp.observer.Observer;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.io.File;
import java.util.ArrayList;

public class PlayerVue extends FlowPane {
    private final Player player;
    private int keys;
    private final ArrayList<ImageView> keyViews;

    public PlayerVue(Player player) {
        super();
        this.player = player;
        this.keys = player.getKeys().size();

        Image playerImg = new Image(new File("resources/players/normal.png").toURI().toString());
        ImageView playerView = new ImageView(playerImg);
        playerView.setFitWidth(64);
        playerView.setFitHeight(64);

        this.getChildren().add(playerView);

        keyViews = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Image key = new Image(new File("resources/keys/cle" +
                    (i == 0 ? "" : (i + 1)) + ".png").toURI().toString());
            ImageView keyView = new ImageView(key);

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
}
