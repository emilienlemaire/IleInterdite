package com.egp.vues;

import com.egp.constants.enums.PlayerTypes;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class SelectPlayerVue extends VBox {
    ArrayList<PlayerTypes> playerTypes;

    public SelectPlayerVue() {
        super();
        ComboBox comboBox = new ComboBox();

        comboBox.getItems().add("Test");
    }
}
