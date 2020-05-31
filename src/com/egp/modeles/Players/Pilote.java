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

        if (this.getPosition().x == c.x)
            if (this.getPosition().y == c.y - 1 || this.getPosition().y == c.y + 1)
                return true;

        if (this.getPosition().y == c.y)
            if (this.getPosition().x == c.x - 1 || this.getPosition().x == c.x + 1)
                return true;

        if (this.getActions() > 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean assechable(Zone c) {
        if (c.etat == Etat.Submergee)
            return false;

        if (this.getPosition().x == c.x)
            return this.getPosition().y == c.y - 1 || this.getPosition().y == c.y + 1;

        if (this.getPosition().y == c.y)
            return this.getPosition().x == c.x - 1 || this.getPosition().x == c.x + 1;
        return false;
    }

    @Override
    public void doubleCost(Zone c){
        boolean estLoin = !
                (((this.getPosition().x == c.x)
                        && (this.getPosition().y == c.y - 1 || this.getPosition().y == c.y + 1))
            ||
                ((this.getPosition().y == c.y)
                        && (this.getPosition().x == c.x - 1 || this.getPosition().x == c.x + 1)));

        if (estLoin){
            this.setActions(this.getActions() - 1);
        }
    }

}
