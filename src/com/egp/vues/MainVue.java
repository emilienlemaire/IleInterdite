package com.egp.vues;

import com.egp.controllers.Controller;
import com.egp.modeles.Modele;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;


public class MainVue extends Scene {

    private Stage stage;
    private Modele modele;

    public MainVue(Modele modele, Pane root, Stage stage) {
        super(root, modele.getNbRows() * 32 + 150, modele.getNbCols() * 32);

        this.stage = stage;
        GrilleVue grilleVue = new GrilleVue(modele, this);
        Controller controller = grilleVue.getController();
        CommandesVue commandesVue = new CommandesVue(modele, controller);

        root.getChildren().addAll(grilleVue, commandesVue);
    }


    public void showPopUp() {
        Popup popup = new Popup();
        popup.setAutoFix(true);
        popup.setAutoHide(true);
        popup.setHideOnEscape(true);
        Text text = new Text("Vous avez reçu une clef");

        popup.getContent().add(text);

        popup.setX(stage.getX() + stage.getWidth()/2 - popup.getWidth()/2);
        popup.setY(stage.getY() + stage.getHeight()/2 - popup.getHeight()/2);

        popup.show(this.stage);
    }
}
