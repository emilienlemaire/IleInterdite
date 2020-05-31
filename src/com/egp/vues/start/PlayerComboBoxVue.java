package com.egp.vues.start;

import com.egp.constants.enums.PlayerType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class PlayerComboBoxVue extends ComboBox<PlayerType> {
    public PlayerComboBoxVue() {
        ObservableList<PlayerType> playerTypes = FXCollections.observableArrayList();
        playerTypes.addAll(PlayerType.Normal, PlayerType.Explorateur, PlayerType.Plongeur, PlayerType.Pilote);

        this.setItems(playerTypes);
        this.setCellFactory(c -> new PlayerComboVue());
        this.setButtonCell(new PlayerComboVue());
    }
}
