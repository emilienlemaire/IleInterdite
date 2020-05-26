package com.egp.vues;

import javafx.scene.control.Alert;

public class LostVue extends Alert {
    public LostVue(MainVue mainVue) {
        super(AlertType.ERROR);
        this.setTitle("Vous avez perdu :(");
    }
}
