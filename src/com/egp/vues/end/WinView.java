package com.egp.vues.end;

import com.egp.constants.Buttons;
import com.egp.constants.Images;
import com.egp.constants.Sounds;
import com.egp.vues.game.MainVue;
import com.egp.vues.start.StartVue;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.MediaPlayer;

import java.util.Optional;

public class WinView extends Alert {

    private final MainVue mainVue;

    public WinView(MainVue mainVue) {
        super(AlertType.INFORMATION, "Souhaitez-vous rejouer ?", Buttons.rejouer, Buttons.quitter);

        this.mainVue = mainVue;

        this.setHeaderText("C'est gagné :D");
        this.setTitle("Vous avez gagné :)");

        ImageView winView = new ImageView(Images.winIcon);
        winView.setFitWidth(111);
        winView.setFitHeight(114);

        this.setGraphic(winView);

        MediaPlayer winPlayer = new MediaPlayer(Sounds.win);
        winPlayer.setVolume(0.25);
        winPlayer.setAutoPlay(true);
        this.showAlert();
    }

    private void showAlert() {
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
            System.out.println("Button non reconnu");
        }
    }
}
