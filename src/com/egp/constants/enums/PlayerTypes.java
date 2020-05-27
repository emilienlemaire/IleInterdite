package com.egp.constants.enums;

import com.egp.constants.Images;
import javafx.scene.image.Image;

public enum PlayerTypes {
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
    };

    public Image getImageCurrent() {
        switch (this) {
            case Normal:
                return Images.playerNormal;
            case Plongeur:
                return Images.playerPlonguer;
            case Explorateur:
                return Images.playerExplorateur;
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
        }
        return Images.playerNormalBW;
    }
}
