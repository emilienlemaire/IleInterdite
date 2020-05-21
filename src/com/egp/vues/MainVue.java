package com.egp.vues;

import com.egp.controllers.Controller;
import com.egp.modeles.Modele;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MainVue extends Scene {

    private final Stage stage;
    private Modele modele;
    private Popup popup;

    public MainVue(Modele modele, Pane root, Stage stage) {
        super(root, modele.getNbRows() * 32 + 150, modele.getNbCols() * 32);

        this.stage = stage;
        GrilleVue grilleVue = new GrilleVue(modele, this);
        Controller controller = grilleVue.getController();
        CommandesVue commandesVue = new CommandesVue(modele, controller);

        root.getChildren().addAll(grilleVue, commandesVue);
    }


    public void showPopUp() {
        popup = new Popup();
        popup.setAutoHide(true);
        popup.setHideOnEscape(true);
        Label label = new Label("Vous avez reçu une clef");
        BackgroundFill backgroundFill = new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY, Insets.EMPTY);
        label.setBackground(new Background(backgroundFill));

        popup.getContent().add(label);

        popup.setX(stage.getX());
        popup.setY(stage.getY() + 21);
        popup.show(this.stage);
    }

    public void hidePopup() {
        popup.hide();
    }
}
