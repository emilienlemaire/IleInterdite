package com.egp.modeles;
import com.egp.constants.*;
import com.egp.observer.Observable;

import java.util.ArrayList;
import java.util.Random;

public class Modele extends Observable {
    private ArrayList<Zone> cases;
    private int nbCols;
    private int nbRows;
    private int[] indices;

    public Modele(int nbCols, int nbRows) {
        this.nbCols = nbCols;
        this.nbRows = nbRows;

        this.cases = new ArrayList<>();

        this.init();
    }

    public void init() {
        for (int i = 0; i < nbRows; i++) {
            for (int j = 0; j < nbCols; j++) {
                this.cases.add(new Zone(Etat.Normale, Type.Normale, j, i));
            }
        }

        this.indices = new Random().ints(0, nbCols * nbRows).distinct().limit(6).toArray();

        for (int i = 0; i < this.indices.length; i++) {
            this.cases.get(this.indices[i]).type = Type.values()[i + 1];
        }
    }

    public void inondeCases() {
        int i = 0;
        Random r = new Random();
        while (i < 3) {
            int idx = r.nextInt(this.nbCols * this.nbRows);
            if (this.cases.get(idx).etat == Etat.Normale) {
                this.cases.get(idx).etat = Etat.Submergee;
                i++;
            }
        }
    }

    public String toString() {
        String msg = "";
        for (Zone c : this.cases) {
            msg += c.toString() + "\n";
        }
        return msg;
    }
}
