package com.egp;

import com.egp.modeles.Modele;
import com.egp.vues.MainVue;

public class Main {
    public static void main(String[] args) {
        Modele modele = new Modele(5,5);
        System.out.println(modele);
        modele.inondeCases();
        modele.inondeCases();
        modele.inondeCases();
        modele.inondeCases();
        System.out.println(modele);

        MainVue vue = new MainVue(modele);
    }
}
