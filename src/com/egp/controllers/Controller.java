package com.egp.controllers;

import com.egp.modeles.Modele;

public class Controller {

    Modele modele;
    public Controller(Modele modele) { this.modele = modele; }

    public void finDeTour() {
        modele.inondeCases();
        System.out.println("Fin de tour");
    }
}
