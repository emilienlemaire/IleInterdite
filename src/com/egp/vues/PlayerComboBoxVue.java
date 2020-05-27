package com.egp.vues;

import com.egp.constants.enums.PlayerType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class PlayerComboBoxVue extends ComboBox<PlayerType> {
    public PlayerComboBoxVue() {
        ObservableList<PlayerType> playerTypes = FXCollections.observableArrayList();
        playerTypes.addAll(PlayerType.Normal, PlayerType.Explorateur, PlayerType.Plongeur);

        this.setItems(playerTypes);
        this.setCellFactory(c -> new PlayerComboVue());
        this.setButtonCell(new PlayerComboVue());
    }
}
