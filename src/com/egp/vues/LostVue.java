package com.egp.vues;


import com.egp.constants.Buttons;
import com.egp.constants.Sounds;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.MediaPlayer;

import java.io.FileNotFoundException;
import java.util.Optional;


public class LostVue extends Alert {

    private final MainVue mainVue;

    public LostVue(MainVue mainVue) {
        super(AlertType.CONFIRMATION, "Souhaitez-vous rejouer ?", Buttons.rejouer, Buttons.quitter);

        this.mainVue = mainVue;

        this.setHeaderText(null);
        this.setTitle("Vous avez perdu :(");

        MediaPlayer lostPlayer = new MediaPlayer(Sounds.lost);
        lostPlayer.setAutoPlay(true);

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