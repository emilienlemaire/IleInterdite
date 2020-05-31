package com.egp.vues.start;

import com.egp.constants.enums.PlayerType;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

public class PlayerComboCellVue extends ListCell<PlayerType> {
    @Override
    protected void updateItem(PlayerType playerType, boolean b) {
        super.updateItem(playerType, b);
        setGraphic(null);
        setText(null);

        if (playerType != null) {
            ImageView playerView = new ImageView(playerType.getImageCurrent());
            playerView.setFitWidth(20);
            playerView.setFitHeight(20);
            setGraphic(playerView);
            setText(playerType.toString());
        }
    }
}
