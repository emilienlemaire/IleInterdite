package com.egp;
import com.egp.modeles.Modele;
import com.egp.vues.MainVue;

public class Main {
    public static void main(String[] args) {
        Modele modele = new Modele(12,20, 2);
        System.out.println(modele);
        MainVue vue = new MainVue(modele);
    }
}
