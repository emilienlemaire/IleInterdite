package com.egp.vues.end;


import com.egp.constants.Buttons;
import com.egp.constants.Sounds;
import com.egp.vues.game.MainVue;
import com.egp.vues.start.StartVue;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.MediaPlayer;

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

        this.showAlert();
    }

    public void showAlert() {
        Optional<ButtonType> result = this.showAndWait();
        if (result.isPresent() && result.get() == Buttons.rejouer){
            this.mainVue.getStage().setScene(
                    new StartVue(
                            new FlowPane(),
                            this.mainVue.getStage()
                    ));
        } else if (result.isPresent() && result.get() == Buttons.quitter){
            this.mainVue.getStage().close();
        } else {
            // ERROR
            System.out.println("Bouton invalide.");
        }
    }
}