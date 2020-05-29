package com.egp.modeles.Players;

import com.egp.constants.enums.PlayerType;
import com.egp.modeles.Zone;

public class Plongeur extends Player{
    public Plongeur(Zone position, int ID, PlayerType type) {
        super(position, ID, type);
    }

    @Override
    public boolean atteignable(Zone c) {
        if (this.getPosition().x == c.x)
            return this.getPosition().y == c.y - 1 || this.getPosition().y == c.y + 1;

        if (this.getPosition().y == c.y)
            return this.getPosition().x == c.x - 1 || this.getPosition().x == c.x + 1;

        return false;
    }

    @Override
    public boolean isDead() {
        return false;
    }


}
