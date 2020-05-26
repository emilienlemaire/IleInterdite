package com.egp.vues;


import javafx.scene.control.Alert;
import com.egp.constants.Buttons;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.FlowPane;

import java.io.FileNotFoundException;
import java.util.Optional;


public class LostVue extends Alert {

    private MainVue mainVue;

    public LostVue(MainVue mainVue) {
        super(AlertType.CONFIRMATION, "Souhaitez-vous rejouer ?", Buttons.rejouer, Buttons.quitter);

        this.mainVue = mainVue;

        this.setHeaderText(null);
        this.setTitle("Vous avez perdu :(");

        showAlert();
    }

    public void showAlert() {
        Optional<ButtonType> result = this.showAndWait();
        if (result.get() == Buttons.rejouer){
            mainVue.getStage().setScene(new StartVue(new FlowPane(), mainVue.getStage()));
        } else if (result.get() == Buttons.quitter){
            mainVue.getStage().close();
        } else {
            // ERROR
            System.out.println("??");
        }
    }
}