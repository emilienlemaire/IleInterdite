package com.egp.vues.start;

import com.egp.constants.enums.PlayerType;
import javafx.scene.control.ComboBox;

public class PlayerComboBoxVue extends ComboBox<PlayerType> {
    private PlayerType playerType = null;

    public PlayerComboBoxVue() {
        this.setCellFactory(c -> new PlayerComboCellVue());
        this.setButtonCell(new PlayerComboCellVue());
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }
}
