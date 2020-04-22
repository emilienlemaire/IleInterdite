package com.egp;

import com.egp.modeles.Modele;
import com.egp.vues.MainVue;

public class Main {
    public static void main(String[] args) {
        Modele modele = new Modele();

        MainVue vue = new MainVue(modele);
    }
}
