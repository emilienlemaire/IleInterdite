package com.egp.vues;

import com.egp.modeles.Modele;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MainVue extends Scene {

    public MainVue(Modele modele, Pane root) {
        super(root, modele.getNbCols() * 32 + 150, modele.getNbRows() * 32);


        GrilleVue grilleVue = new GrilleVue(modele);
        CommandesVue commandesVue = new CommandesVue(modele);

        root.getChildren().addAll(grilleVue, commandesVue);
    }
}
