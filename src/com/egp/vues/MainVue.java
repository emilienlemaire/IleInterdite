package com.egp.vues;

import com.egp.modeles.Modele;

import javax.swing.*;
import java.awt.*;

public class MainVue {
    private static JFrame jFrame;

    private static GrilleVue grilleVue;
    private static CommandesVue commandesVue;
    private static Cursor invalidCursor = null;

    public MainVue(Modele modele) {
        Cursor invalidCursorTmp;
        jFrame = new JFrame();
        jFrame.setTitle("Ile Interdite");
        jFrame.setLayout( new FlowLayout());

        grilleVue = new GrilleVue(modele);

        commandesVue = new CommandesVue(modele);

        jFrame.add(grilleVue);
        jFrame.add(commandesVue);

        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

        try {
            invalidCursorTmp = Cursor.getSystemCustomCursor("invalid32x32");
        } catch (AWTException e) {
            invalidCursorTmp = null;
        }
        invalidCursor = invalidCursorTmp;
    }

    public static void setCursorGood() {
        jFrame.setCursor(Cursor.getDefaultCursor());
    }

    public static void setCursorWrong() {
        if (invalidCursor == null) {
            jFrame.setCursor(Cursor.getDefaultCursor());
        } else {
            jFrame.setCursor(invalidCursor);
        }
    }
}
