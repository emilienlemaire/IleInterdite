package com.egp.modeles.Players;

import com.egp.constants.enums.Etat;
import com.egp.constants.enums.PlayerType;
import com.egp.modeles.Zone;

public class Explorateur extends Player{
    public Explorateur(Zone position, int ID, PlayerType type) {
        super(position, ID, type);
    }

    @Override
    public boolean atteignable(Zone c) {
        if (c.etat == Etat.Submergee)
            return false;

        if (this.getPosition().x == c.x)
            return this.getPosition().y == c.y - 1 || this.getPosition().y == c.y + 1;

        if (this.getPosition().y == c.y)
            return this.getPosition().x == c.x - 1 || this.getPosition().x == c.x + 1;

        if (this.getPosition().x == c.x+1 || this.getPosition().x == c.x-1){
            return this.getPosition().y == c.y+1 || this.getPosition().y == c.y-1;
        }

        return false;
    }

}
