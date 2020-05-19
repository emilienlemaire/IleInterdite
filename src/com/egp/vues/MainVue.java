package com.egp.vues;

import com.egp.controllers.Controller;
import com.egp.modeles.Modele;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MainVue extends Scene {

    public MainVue(Modele modele, Pane root) {
        super(root, modele.getNbCols() * 32 + 150, modele.getNbRows() * 32);

        GrilleVue grilleVue = new GrilleVue(modele, this);
        Controller controller = grilleVue.getController();
        CommandesVue commandesVue = new CommandesVue(modele, controller);

        root.getChildren().addAll(grilleVue, commandesVue);
    }
}
