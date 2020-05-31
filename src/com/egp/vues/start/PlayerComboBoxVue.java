package com.egp.vues.start;

import com.egp.constants.enums.PlayerType;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;

public class PlayerComboBoxVue extends ComboBox<PlayerType> {
    private PlayerType playerType = null;

    public PlayerComboBoxVue(ArrayList<PlayerType> playerTypes) {
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
