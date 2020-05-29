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
        this.plusButton.setOnMouseClicked(mouseEvent -> addPlayer());
        this.minusButton.setOnMouseClicked(mouseEvent -> removePlayer());

        PlayerComboBoxVue comboBoxVue = new PlayerComboBoxVue();
        this.comboBoxVues.add(comboBoxVue);
        this.playerTypes.add(null);
        comboBoxVue.setOnAction(actionEvent -> {
            int idx = this.comboBoxVues.indexOf(comboBoxVue);
            this.playerTypes.set(idx, comboBoxVue.getValue());
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

        this.playerTypes.remove(this.playerTypes.size() - 1);

        this.getChildren().addAll(this.hBoxes);
    }

    public void addPlayer() {
        PlayerComboBoxVue comboBoxVue = new PlayerComboBoxVue();
        this.comboBoxVues.add(comboBoxVue);

        this.playerTypes.add(null);
        comboBoxVue.setOnAction(actionEvent -> {
            int idx = this.comboBoxVues.indexOf(comboBoxVue);
            this.playerTypes.set(idx, comboBoxVue.getValue());
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

    public ArrayList<PlayerType> getPlayerTypes() {
        return this.playerTypes;
    }
}
