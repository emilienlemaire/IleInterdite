package com.egp.vues;

import com.egp.modeles.Modele;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class StartVue extends Scene {
    private final Pane root;
    private final Stage stage;

    public StartVue(Pane root, Stage stage) {
        super(root, 250, 300);

        this.root = root;
        this.stage = stage;
        Button button = new Button("Passer au jeu");

        button.setOnMouseClicked(mouseEvent -> setMainScene());

        root.getChildren().add(button);
    }

    private void setMainScene() {
        Modele modele = new Modele(10, 10, 5);
        root.getChildren().clear();

        FlowPane mainRoot = new FlowPane(this.root);

        this.stage.setScene(new MainVue(modele, mainRoot, this.stage));
    }
}
