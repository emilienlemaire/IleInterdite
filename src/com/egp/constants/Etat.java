package com.egp.constants;

import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public enum Etat {
    Normale("Normale", new Color(165, 220, 142), 0.f),
    Innondee("Innondée", new Color(159, 206, 255), .5f),
    Submergee("Submergée", new Color(71, 107, 255), 1.f);

    private String name;
    private Color color;
    private Float blendFactor;

    Etat(String name, Color color, Float blendFactor) {
        this.name = name;
        this.color = color;
        this.blendFactor = blendFactor;
    }

    public String toString() {
        return this.name;
    }

    public Color getColor() {
        return this.color;
    }

    public BufferedImage getImage(@NotNull BufferedImage img) {
        BufferedImage overlay = null;

        try {
            overlay = ImageIO.read(new File("resources/states/submerged.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Graphics2D g2d = img.createGraphics();
        g2d.setComposite(AlphaComposite.SrcOver.derive(blendFactor));
        g2d.drawImage(overlay, 0, 0, 32, 32, null);
        g2d.dispose();

        return img;
    }
}
