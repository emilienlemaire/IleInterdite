package com.egp.vues;

import com.egp.constants.enums.PlayerType;
import com.egp.modeles.Players.Player;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class PlayerSelectVue extends VBox {
    private final ArrayList<PlayerType> playerTypes = new ArrayList<>();
    private final ArrayList<PlayerComboBoxVue> comboBoxVues = new ArrayList<>();
    private final ArrayList<Button> plusButtons = new ArrayList<>();

    public PlayerSelectVue() {
        super();
        PlayerComboBoxVue comboBoxVue = new PlayerComboBoxVue();
        comboBoxVues.add(comboBoxVue);
        playerTypes.add(PlayerType.Normal);
        comboBoxVue.setOnAction(actionEvent -> {
            int idx = comboBoxVues.indexOf(comboBoxVue);
            playerTypes.set(idx, comboBoxVue.getValue());
        });

        Button plusButton = new Button("+");
        plusButton.setOnMouseClicked(mouseEvent -> addPlayer());

        HBox hBox = new HBox(comboBoxVue, plusButton);
        hBox.setSpacing(10);
        this.setMaxWidth(250);
        this.getChildren().addAll(hBox);
    }

    public void addPlayer() {
        PlayerComboBoxVue comboBoxVue = new PlayerComboBoxVue();
        comboBoxVues.add(comboBoxVue);
        playerTypes.add(PlayerType.Normal);
        comboBoxVue.setOnAction(actionEvent -> {
            int idx = comboBoxVues.indexOf(comboBoxVue);
            playerTypes.set(idx, comboBoxVue.getValue());
        });
        this.getChildren().clear();
    }
}
