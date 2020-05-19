package com.egp;

import javafx.application.Application;

import com.egp.modeles.Modele;
import com.egp.vues.MainVue;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Modele modele = new Modele(12, 20, 5);
        FlowPane root = new FlowPane();
        stage.setTitle("Ile Interdite");
        stage.setScene(new MainVue(modele, root));
        stage.show();
    }
}
