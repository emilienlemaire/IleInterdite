package com.egp.vues.start;

import com.egp.constants.enums.PlayerType;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class PlayerSelectVue extends VBox {
    private final ArrayList<PlayerType> playerTypes = new ArrayList<>();
    private final ArrayList<PlayerComboBoxVue> comboBoxVues = new ArrayList<>();
    private final ArrayList<HBox> hBoxes = new ArrayList<>();
    private final Button plusButton = new Button("+");
    private final Button minusButton = new Button("-");

    public PlayerSelectVue() {
        super();
        plusButton.setOnMouseClicked(mouseEvent -> addPlayer());
        minusButton.setOnMouseClicked(mouseEvent -> removePlayer());

        PlayerComboBoxVue comboBoxVue = new PlayerComboBoxVue();
        comboBoxVues.add(comboBoxVue);
        playerTypes.add(null);
        comboBoxVue.setOnAction(actionEvent -> {
            int idx = comboBoxVues.indexOf(comboBoxVue);
            playerTypes.set(idx, comboBoxVue.getValue());
        });

        HBox hBox = new HBox(comboBoxVue, plusButton);
        hBox.setSpacing(7.5);
        hBoxes.add(hBox);
        this.setMaxWidth(250);
        this.getChildren().addAll(hBoxes);
    }

    private void removePlayer() {
        this.getChildren().clear();

        hBoxes.remove(hBoxes.size() - 1);
        hBoxes.get(hBoxes.size() - 1).getChildren().add(plusButton);
        if (hBoxes.size() > 1) {
            hBoxes.get(hBoxes.size() - 1).getChildren().add(minusButton);
        }

        comboBoxVues.remove(comboBoxVues.size() - 1);

        playerTypes.remove(playerTypes.size() - 1);

        this.getChildren().addAll(hBoxes);
    }

    public void addPlayer() {
        PlayerComboBoxVue comboBoxVue = new PlayerComboBoxVue();
        comboBoxVues.add(comboBoxVue);

        playerTypes.add(null);
        comboBoxVue.setOnAction(actionEvent -> {
            int idx = comboBoxVues.indexOf(comboBoxVue);
            playerTypes.set(idx, comboBoxVue.getValue());
        });
        HBox hBox = new HBox(comboBoxVue, plusButton, minusButton);
        hBox.setSpacing(7.5);
        hBoxes.add(hBox);
        this.getChildren().clear();
        this.getChildren().addAll(hBoxes);

        if (comboBoxVues.size() > 4) {
            for (HBox hBox1 : hBoxes) {
                hBox1.getChildren().remove(plusButton);
            }
        }
    }

    public ArrayList<PlayerType> getPlayerTypes() {
        return playerTypes;
    }
}
