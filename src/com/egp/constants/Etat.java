package com.egp.constants;

import java.awt.*;

public enum Etat {
    Normale("Normale", new Color(165, 220, 142)),
    Innondee("Innondée", new Color(159, 206, 255)),
    Submergee("Submergée", new Color(71, 107, 255));

    private String name;
    private Color color;

    Etat(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String toString() {
        return this.name;
    }

    public Color getColor() {
        return this.color;
    }
}
