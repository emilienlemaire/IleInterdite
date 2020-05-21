package com.egp.constants;

import javafx.scene.Group;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;

public enum Etat {
    Normale("Normale", 0.f),
    Innondee("Innondée", .5f),
    Submergee("Submergée", 1.f);

    private String name;
    private Float blendFactor;

    Etat(String name, Float blendFactor) {
        this.name = name;
        this.blendFactor = blendFactor;
    }

    public String toString() {
        return this.name;
    }

    public Group getImage(Group img) {

        if (blendFactor == 0.f)
            return img;

        ImageView overlayView = new ImageView(Images.overlay);
        overlayView.setFitWidth(32);
        overlayView.setFitHeight(32);

        if (blendFactor == .5f) {
            overlayView.setBlendMode(BlendMode.HARD_LIGHT);
        } else {
            overlayView.setBlendMode(BlendMode.MULTIPLY);
        }

        return new Group(img, overlayView);
    }
}
