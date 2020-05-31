package com.egp.vues.start;

import com.egp.constants.enums.PlayerType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerSelectVue extends VBox {
    private final ArrayList<PlayerType> playersSelected = new ArrayList<>();
    private final ArrayList<PlayerType> availablePlayers = new ArrayList<>();
    private final ArrayList<PlayerComboBoxVue> comboBoxVues = new ArrayList<>();
    private final ArrayList<HBox> hBoxes = new ArrayList<>();
    private final Button plusButton = new Button("+");
    private final Button minusButton = new Button("-");

    public PlayerSelectVue() {
        super();
        this.plusButton.setOnMouseClicked(mouseEvent -> addPlayer());
        this.minusButton.setOnMouseClicked(mouseEvent -> removePlayer());
        this.availablePlayers.addAll(
                Arrays.asList(PlayerType.Normal,
                        PlayerType.Explorateur,
                        PlayerType.Pilote,
                        PlayerType.Plongeur)
        );
        PlayerComboBoxVue comboBoxVue = new PlayerComboBoxVue(availablePlayers);
        comboBoxVue.setItems(FXCollections.observableArrayList(availablePlayers));
        this.comboBoxVues.add(comboBoxVue);
        this.playersSelected.add(null);
        comboBoxVue.setOnAction(actionEvent -> {
            int idx = this.comboBoxVues.indexOf(comboBoxVue);

            PlayerType currentPlayerType = comboBoxVue.getValue();
            this.playersSelected.set(idx, currentPlayerType);

            if (currentPlayerType != PlayerType.Normal) {
                this.availablePlayers.remove(currentPlayerType);
                for (PlayerComboBoxVue playerComboBoxVue
                        : comboBoxVues) {
                    if (playerComboBoxVue != comboBoxVue) {
                        playerComboBoxVue.getItems().remove(currentPlayerType);
                    }
                }
            }
        });

        HBox hBox = new HBox(comboBoxVue, plusButton);
        hBox.setSpacing(7.5);
        this.hBoxes.add(hBox);
        this.setMaxWidth(250);
        this.getChildren().addAll(this.hBoxes);
    }

    private void removePlayer() {
        this.getChildren().clear();

        this.hBoxes.remove(this.hBoxes.size() - 1);
        this.hBoxes.get(this.hBoxes.size() - 1).getChildren().add(this.plusButton);
        if (this.hBoxes.size() > 1) {
            this.hBoxes.get(this.hBoxes.size() - 1).getChildren().add(this.minusButton);
        }

        this.comboBoxVues.remove(this.comboBoxVues.size() - 1);

        this.playersSelected.remove(this.playersSelected.size() - 1);

        this.getChildren().addAll(this.hBoxes);
    }

    public void addPlayer() {
        PlayerComboBoxVue comboBoxVue = new PlayerComboBoxVue(this.availablePlayers);
        comboBoxVue.setItems(FXCollections.observableArrayList(this.availablePlayers));
        this.comboBoxVues.add(comboBoxVue);

        this.playersSelected.add(null);
        comboBoxVue.setOnAction(actionEvent -> {
            int idx = this.comboBoxVues.indexOf(comboBoxVue);
            PlayerType currentPlayerType = comboBoxVue.getValue();
            this.playersSelected.set(idx, currentPlayerType);
            if (currentPlayerType != PlayerType.Normal) {
                this.availablePlayers.remove(currentPlayerType);
                for (PlayerComboBoxVue playerComboBoxVue :
                        comboBoxVues) {
                    if (playerComboBoxVue != comboBoxVue) {
                        playerComboBoxVue.getItems().remove(currentPlayerType);
                    }
                }
            }
        });
        HBox hBox = new HBox(comboBoxVue, this.plusButton, this.minusButton);
        hBox.setSpacing(7.5);
        this.hBoxes.add(hBox);
        this.getChildren().clear();
        this.getChildren().addAll(this.hBoxes);

        if (this.comboBoxVues.size() > 4) {
            for (HBox hBox1 : this.hBoxes) {
                hBox1.getChildren().remove(this.plusButton);
            }
        }
    }

    public ArrayList<PlayerType> getPlayersSelected() {
        return this.playersSelected;
    }
}
