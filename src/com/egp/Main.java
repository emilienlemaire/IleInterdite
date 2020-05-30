package com.egp;

import com.egp.constants.Sounds;
import com.egp.vues.start.StartVue;
import javafx.application.Application;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Sounds.init();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FlowPane root = new FlowPane();
        stage.setTitle("Ile Interdite");
        stage.setScene(new StartVue(root, stage));
        stage.setResizable(false);
        stage.show();
    }
}
