package com.egp.vues;

import com.egp.controllers.Controller;
import com.egp.modeles.Modele;
import com.egp.observer.Observer;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class CommandesVue extends BorderPane implements Observer {

    private final Modele modele;
    private final ProgressBar actionLeftBar;
    private StackPane progressPane;
    private Text progressText;

    public CommandesVue(Modele modele, Controller controller) {
        this.modele = modele;

        modele.addObserver(this);

        actionLeftBar = new ProgressBar();
        actionLeftBar.setProgress(1);

        progressText = new Text("3 / 3");

        progressPane = new StackPane();
        progressPane.setMinWidth(150);
        progressPane.getChildren().addAll(actionLeftBar, progressText);
        this.setTop(progressPane);

        Button finTourButton = new Button("Fin de tour");

        StackPane buttonPane = new StackPane();
        buttonPane.setMinWidth(150);
        buttonPane.getChildren().addAll(finTourButton);

        finTourButton.setOnAction(actionEvent -> controller.finDeTour());

        this.setBottom(buttonPane);
    }

    @Override
    public void update() {
        int actionLeft = modele.getCurrentPlayer().getActions();
        actionLeftBar.setProgress(actionLeft / 3.);
        progressText.setText( actionLeft + " / 3");
    }
}
