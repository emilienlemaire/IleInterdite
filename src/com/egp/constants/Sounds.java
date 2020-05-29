package com.egp.constants;

import javafx.scene.media.Media;

import static com.egp.constants.utils.getPath;

public class Sounds {
    public static void init() {
        // Here to load the statics attributes.
        System.out.println("Loading sounds");
    }
    public static final Media flooding = new Media(getPath("resources/sounds/flooding.wav"));
    public static final Media die = new Media(getPath("resources/sounds/die.wav"));
    public static final Media key = new Media(getPath("resources/sounds/key.wav"));
    public static final Media artifact = new Media(getPath("resources/sounds/artifact.wav"));
    public static final Media lost = new Media(getPath("resources/sounds/lost.wav"));
    public static final Media win = new Media(getPath("resources/sounds/win.wav"));
}
