package com.egp.vues;

import com.egp.controllers.Controller;
import com.egp.modeles.Modele;
import com.egp.observer.Observer;

import javax.swing.*;
import java.awt.*;

public class CommandesVue extends JPanel implements Observer {

    private final Modele modele;
    private final JProgressBar actionLeftBar;

    public CommandesVue(Modele modele) {
        this.modele = modele;

        Dimension dim = new Dimension(150,32 * modele.getNbRows());

        actionLeftBar = new JProgressBar(0, 3);
        actionLeftBar.setValue(3);
        actionLeftBar.setStringPainted(true);
        actionLeftBar.setString("3 / 3");

        JButton finTourButton = new JButton("Fin de tour");

        this.setLayout(new BorderLayout());
        this.setPreferredSize(dim);

        this.add(actionLeftBar);

        this.add(finTourButton);

        Controller ctrl = new Controller(modele);
        finTourButton.addMouseListener(ctrl);
    }

    @Override
    public void update() {
        int actionLeft = modele.getActionLeft();
        actionLeftBar.setValue(actionLeft);
        this.repaint();
    }
}
