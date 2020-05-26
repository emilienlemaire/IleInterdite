package com.egp.vues;


import javafx.scene.control.Alert;
import com.egp.constants.Buttons;
import javafx.scene.control.ButtonType;
import java.util.Optional;


public class LostVue extends Alert {

    public LostVue(MainVue mainVue) {
        super(AlertType.CONFIRMATION, "Souhaitez-vous rejouer ?", Buttons.rejouer, Buttons.quitter);

        this.setHeaderText(null);
        this.setTitle("Vous avez perdu :(");
        showAlert();
    }

    public void showAlert() {
        Optional<ButtonType> result = this.showAndWait();
        if (result.get() == Buttons.rejouer){
            System.out.println("rejouer");

        } else if (result.get() == Buttons.quitter){
            System.out.println("quitter");
        } else {
            // ERROR
            System.out.println("??");
        }

    }
}