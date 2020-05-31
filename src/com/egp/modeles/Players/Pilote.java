package com.egp.modeles.Players;

import com.egp.constants.enums.Etat;
import com.egp.constants.enums.PlayerType;
import com.egp.modeles.Zone;

public class Pilote extends Player{
    public Pilote(Zone position, int ID, PlayerType type) {
        super(position, ID, type);
    }

    @Override
    public boolean atteignable(Zone c) {
        if (c.etat == Etat.Submergee)
            return false;
        return true;
    }

}
