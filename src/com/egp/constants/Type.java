package com.egp.constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

    private String base;
    private String overlay;

    Type(String base, String overlay) {
        this.base = base;
        this.overlay = overlay;
    }

    public BufferedImage getImage() {
        BufferedImage img = null;
        BufferedImage overlay = null;

        try {
            img = ImageIO.read(new File("resources/cases/" + this.base));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (this.overlay != null) {
            try {
                overlay = ImageIO.read(new File("resources/cases/" + this.overlay));
            } catch (IOException e) {
                e.printStackTrace();
            }

            assert img != null;
            Graphics2D g2d = img.createGraphics();
            g2d.setComposite(AlphaComposite.SrcOver.derive(.8f));
            g2d.drawImage(overlay, 0, 0, 32, 32, null);
            g2d.dispose();
        }

        return img;
    }
}
