package com.egp.vues.game;

import com.egp.controllers.Controller;
import com.egp.modeles.Modele;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.net.MalformedURLException;

import static com.egp.constants.utils.getURL;


public class MainVue extends Scene {

    private final Controller controller;
    private final Stage stage;
    private Popup popup;

    public MainVue(Modele modele, Pane root, Stage stage) {
        super(root, modele.getNbRows() * 32 + 150, modele.getNbCols() * 32);

        try {
            this.getStylesheets().add(getURL("resources/styles/stylesheet.css").toExternalForm());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        root.getStyleClass().add("main");

        this.stage = stage;
        GrilleVue grilleVue = new GrilleVue(modele, this);
        this.controller = grilleVue.getController();
        CommandesVue commandesVue = new CommandesVue(modele, this.controller);

        root.getChildren().addAll(grilleVue, commandesVue);
    }


    public void showPopUp(Label label) {
        this.popup = new Popup();
        this.popup.setAutoHide(true);
        this.popup.setHideOnEscape(true);
        BackgroundFill backgroundFill = new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY, Insets.EMPTY);
        label.setBackground(new Background(backgroundFill));

        this.popup.getContent().add(label);

        this.popup.setX(this.stage.getX());
        this.popup.setY(this.stage.getY() + 21);
        this.popup.show(this.stage);
    }

    public void hidePopup() {
        this.popup.hide();
    }

    public Stage getStage() {
        return stage;
    }
}
