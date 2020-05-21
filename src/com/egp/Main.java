package com.egp;

import com.egp.constants.Images;
import com.egp.modeles.Modele;
import com.egp.vues.MainVue;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class IleInterdite extends Application {
    private Modele modele;
    private FlowPane root;
    private FirstPreloader preloader = new FirstPreloader();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        this.notifyPreloader(new Preloader.ProgressNotification(0));
        modele = new Modele(12, 20, 5);
        this.notifyPreloader(new Preloader.ProgressNotification(1. / 3.));
        root = new FlowPane();
        this.notifyPreloader(new Preloader.ProgressNotification(2. / 3.));
        Images.init();
        this.notifyPreloader(new Preloader.ProgressNotification(3. / 3.));
    }

    @Override
    public void start(Stage stage) {
        root = new FlowPane();
        stage.setTitle("Ile Interdite");
        stage.setScene(new MainVue(modele, root, stage));
        stage.show();
    }
}
