package com.egp.constants.enums;


import com.egp.constants.Images;
import javafx.scene.Group;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public enum Type {
    Normale("normal.png", null){
        public String toString() {
            return "Normale";
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

    private final String basePath;
    private final String overlayPath;

    Type(String basePath, String overlay) {
        this.basePath = basePath;
        this.overlayPath = overlay;
    }

    private Image pathToImage(String name) {
        if (name.equals("normal.png"))
            return Images.normalCase;
        if (name.equals("air.png"))
            return Images.airCase;
        if (name.equals("earth.png"))
            return Images.earthCase;
        if (name.equals("fire.png"))
            return Images.fireCase;
        if (name.equals("heliport.png"))
            return Images.heliportCase;
        if (name.equals("water.png"))
            return Images.waterCase;

        return null;
    }

    public Group getImage() {
        Group res;
        ImageView base = new ImageView(pathToImage(this.basePath));
        base.setFitWidth(32);
        base.setFitHeight(32);
        if (this.overlayPath != null) {
            ImageView overlayView = new ImageView(pathToImage(this.overlayPath));
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
