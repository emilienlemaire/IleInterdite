package com.egp.modeles.Players;

import com.egp.constants.enums.Etat;
import com.egp.constants.enums.PlayerType;
import com.egp.modeles.Zone;

public class Explorateur extends Player{
    public Explorateur(Zone position, PlayerType type) {
        super(position, type);
    }

    @Override
    public boolean atteignable(Zone c) {
        if (c.etat == Etat.Submergee)
            return false;

        if (this.getPosition().x == c.x+1 || this.getPosition().x == c.x-1 &&
                (this.getPosition().y == c.y+1 || this.getPosition().y == c.y-1)){
            return true;
        }

        return super.atteignable(c);
    }

}
