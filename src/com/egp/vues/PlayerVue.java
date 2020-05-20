package com.egp.vues;

import com.egp.modeles.Player;
import com.egp.observer.Observer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

import java.io.File;

public class PlayerVue extends FlowPane implements Observer {
    private final Player player;
    private int keys;
    private final Text text;

    public PlayerVue(Player player) {
        super();
        this.player = player;
        this.keys = player.getCles();

        Image playerImg = new Image(new File("resources/players/normal.png").toURI().toString());
        ImageView playerView = new ImageView(playerImg);
        playerView.setFitWidth(32);
        playerView.setFitHeight(32);
        this.text = new Text("Clés: " + keys);

        this.getChildren().addAll(playerView, text);
    }

    @Override
    public void update() {
        this.keys = player.getCles();
        this.text.setText("Clés: " + keys);
    }
}
