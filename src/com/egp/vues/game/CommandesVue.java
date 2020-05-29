package com.egp.vues.game;

import com.egp.controllers.Controller;
import com.egp.modeles.Modele;
import com.egp.modeles.Players.Player;
import com.egp.observer.Observer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class CommandesVue extends BorderPane implements Observer {

    private final Modele modele;
    private final ProgressBar actionLeftBar;
    private final Text progressText;

    public CommandesVue(Modele modele, Controller controller) {
        this.modele = modele;

        this.setPadding(new Insets(10));

        modele.addObserver(this);

        actionLeftBar = new ProgressBar();
        actionLeftBar.setProgress(1);

        progressText = new Text("3 / 3");

        StackPane progressPane = new StackPane();
        progressPane.setMinWidth(130);
        progressPane.getChildren().addAll(actionLeftBar, progressText);
        this.setTop(progressPane);

        Button finTourButton = new Button("Fin de tour");

        StackPane buttonPane = new StackPane();
        buttonPane.setMinWidth(130);
        buttonPane.getChildren().addAll(finTourButton);

        finTourButton.setOnAction(actionEvent -> controller.finDeTour());

        this.setBottom(buttonPane);

        drawPlayers();
    }

    @Override
    public void update() {
        int actionLeft = modele.getCurrentPlayer().getActions();
        actionLeftBar.setProgress(actionLeft / 3.);
        progressText.setText( actionLeft + " / 3");
    }

    public void drawPlayers() {
        ArrayList<Player> players = modele.getPlayers();
        VBox playerBox = new VBox();
        playerBox.setAlignment(Pos.CENTER);
        for (Player player :
                players) {
            PlayerVue playerVue = new PlayerVue(modele, player);
            playerBox.getChildren().add(playerVue);
        }
        playerBox.setMaxWidth(130);
        this.setMaxHeight(modele.getNbRows() * 32);
        this.setCenter(playerBox);
        setAlignment(playerBox, Pos.BOTTOM_CENTER);
    }
}
