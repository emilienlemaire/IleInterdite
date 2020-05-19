package com.egp.constants;


import javafx.scene.Group;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public enum Type {
    Normale("normal.png", null){
        public String toString() {
            return "Normale";
        }
    },
    Spawn("normal.png", null){
        public String toString() {
            return "Spawn";
        }
    },
    Heliport("normal.png", "heliport.png"){
        public String toString() {
            return "Héliport";
        }
    },
    Air("normal.png", "air.png"){
        public String toString() {
            return "Air";
        }
    },
    Terre("earth.png", null){
        public String toString() {
            return "Terre";
        }
    },
    Eau("normal.png", "water.png"){
        public String toString() {
            return "Eau";
        }
    },
    Feu("normal.png", "fire.png"){
        public String toString() {
            return "Feu";
        }
    };

    private String basePath;
    private String overlayPath;

    Type(String basePath, String overlay) {
        this.basePath = basePath;
        this.overlayPath = overlay;
    }

    public Group getImage() {
        Image img;
        Image overlay;

        img = new Image(new File("resources/cases/" + this.basePath).toURI().toString());
        overlay = new Image(new File("resources/cases/" + this.overlayPath).toURI().toString());

        Group res;
        ImageView base = new ImageView(img);
        base.setFitWidth(32);
        base.setFitHeight(32);
        if (this.overlayPath != null) {
            ImageView overlayView = new ImageView(overlay);
            overlayView.setBlendMode(BlendMode.SRC_ATOP);
            overlayView.setFitWidth(32);
            overlayView.setFitHeight(32);
            res = new Group(base, overlayView);
        } else {
            res = new Group(base);
        }

        return res;
    }
}
