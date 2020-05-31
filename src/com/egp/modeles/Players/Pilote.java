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

   static public boolean assechable(Player pilote, Zone c) {
        if (c.etat == Etat.Submergee)
            return false;

        if (pilote.getPosition().x == c.x)
            return pilote.getPosition().y == c.y - 1 || pilote.getPosition().y == c.y + 1;

        if (pilote.getPosition().y == c.y)
            return pilote.getPosition().x == c.x - 1 || pilote.getPosition().x == c.x + 1;

        return false;
    }

}
