package com.egp.constants;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;

import static com.egp.constants.utils.getPath;

public class Images {
    public static final Image background = new Image(getPath("resources/background/background.png"));

    public static final Image playerNormal = new Image(getPath("resources/players/normal.png"));
    public static final Image playerNormalBW = new Image(getPath("resources/players/normal-b&w.png"));
    public static final Image playerPlonguer = new Image(getPath("resources/players/plongeur.png"));
    public static final Image playerPlongeurBW = new Image(getPath("resources/players/plongeur-b&w.png"));
    public static final Image playerExplorateur = new Image(getPath("resources/players/explorateur.png"));
    public static final Image playerExplorateurBW = new Image(getPath("resources/players/explorateur-b&w.png"));

    public static final Image key1 = new Image(getPath("resources/keys/cle.png"));
    public static final Image key2 = new Image(getPath("resources/keys/cle2.png"));
    public static final Image key3 = new Image(getPath("resources/keys/cle3.png"));
    public static final Image key4 = new Image(getPath("resources/keys/cle4.png"));
    public static final ArrayList<Image> keys = new ArrayList<>(Arrays.asList(key1, key2, key3, key4));

    public static final Image artifact1 = new Image(getPath("resources/artifacts/artf1.png"));
    public static final Image artifact2 = new Image(getPath("resources/artifacts/artf2.png"));
    public static final Image artifact3 = new Image(getPath("resources/artifacts/artf3.png"));
    public static final Image artifact4 = new Image(getPath("resources/artifacts/artf4.png"));
    public static final ArrayList<Image> artifacts = new ArrayList<>(Arrays.asList(artifact1, artifact2, artifact3, artifact4));

    public static final Image overlay = new Image(getPath("resources/states/submerged.png"));
    public static final Image hover = new Image(getPath("resources/cases/hover2.png"));

    public static final Image normalCase = new Image(getPath("resources/cases/normal.png"));
    public static final Image airCase = new Image(getPath("resources/cases/air.png"));
    public static final Image earthCase = new Image(getPath("resources/cases/earth.png"));
    public static final Image fireCase = new Image(getPath("resources/cases/fire.png"));
    public static final Image heliportCase = new Image(getPath("resources/cases/heliport.png"));
    public static final Image waterCase = new Image(getPath("resources/cases/water.png"));

    public static final Image cursor = new Image(getPath("resources/cursors/invalid32x32.gif"));

    public static final Image winIcon = new Image(getPath("resources/icons/winIcon.png"));
}
