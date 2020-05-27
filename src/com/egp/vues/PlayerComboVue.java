package com.egp.vues;

import com.egp.constants.enums.PlayerType;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class PlayerComboView extends HBox {

    private final PlayerType playerType;

    public PlayerComboView(PlayerType type) {
        super();
        this.playerType = type;
        this.setAlignment(Pos.CENTER_LEFT);
        ImageView playerView = new ImageView(type.getImageCurrent());
        playerView.setFitWidth(20);
        playerView.setFitHeight(20);
        Text name = new Text(type.toString());

        this.getChildren().addAll(playerView, name);
    }

    public PlayerType getPlayerType() {
        return playerType;
    }
}
