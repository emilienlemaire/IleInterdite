package com.egp.vues;

import com.egp.modeles.Modele;

import javax.swing.*;

public class CommandesVue extends JPanel {

    private Modele modele;

    public CommandesVue(Modele modele) {
        this.modele = modele;

        JButton finTourButton = new JButton("Fin de tour");

        this.add(finTourButton);
    }

}
