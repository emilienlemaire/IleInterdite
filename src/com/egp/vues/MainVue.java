package com.egp.vues;

import com.egp.modeles.Modele;

import javax.swing.*;
import java.awt.*;

public class MainVue {
    private JFrame jFrame;

    private GrilleVue grilleVue;
    private CommandesVue commandesVue;

    public MainVue(Modele modele) {
        jFrame = new JFrame();
        jFrame.setTitle("Ile Interdite");
        jFrame.setLayout( new FlowLayout());

        grilleVue = new GrilleVue(modele);

        commandesVue = new CommandesVue(modele);

        jFrame.add(grilleVue);
        jFrame.add(commandesVue);

        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
