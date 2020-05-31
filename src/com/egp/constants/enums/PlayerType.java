package com.egp.constants.enums;

import com.egp.constants.Images;
import javafx.scene.image.Image;

public enum PlayerType {
    Normal {
        public String toString() {
            return "Normal";
        }
    },
    Plongeur {
        public String toString() {
            return "Plongeur";
        }
    },
    Explorateur {
        public String toString() {
            return "Explorateur";
        }
    },
    Pilote {
        public String toString() { return "Pilote"; }
    };

    public Image getImageCurrent() {
        switch (this) {
            case Normal:
                return Images.playerNormal;
            case Plongeur:
                return Images.playerPlonguer;
            case Explorateur:
                return Images.playerExplorateur;
            case Pilote:
                return Images.playerPilote;
        }
        return Images.playerNormal;
    }

    public Image getImageNotCurrent() {
        switch (this) {
            case Normal:
                return Images.playerNormalBW;
            case Plongeur:
                return Images.playerPlongeurBW;
            case Explorateur:
                return Images.playerExplorateurBW;
            case Pilote:
                return Images.playerPiloteBW;
        }
        return Images.playerNormalBW;
    }

    public String getInformation() {
        switch (this) {
            case Normal:
                return "C'est un jouer normal qui ne peut se déplacer qu'à l'horizontale ou à la verticale";
            case Plongeur:
                return "C'est un joueur qui peut se déplacer dans les cases submergées";
            case Explorateur:
                return "Ce joueur spécial peut se déplacer dans les zones submergées";
            case Pilote:
                return "TODO";
        }
        return "Veuillez selectionner un type de joueur";
    }
}
