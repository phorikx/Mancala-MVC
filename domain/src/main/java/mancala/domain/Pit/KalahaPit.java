package mancala.domain.Pit;

import mancala.domain.*;

public class KalahaPit extends Pit{
    public KalahaPit (Player owner) {
        this.stones = 0;
        this.owner = owner;
    }
    public KalahaPit () {
        this.stones = 0;
    }

    @Override
    public void give(int numberOfStones) {
        if(this.getOwner().getTurn()) {
            super.give(numberOfStones);
        } else{
            this.getRightNeighbour().give(numberOfStones);
        }
    }

    @Override
    public void executeWhenLastPit() {
        this.getOwner().takeTurn();
    }

    public void giveStolenStones(int numberOfStones) {
        this.stones += numberOfStones;
    }
    
    // The following function should not be activated.
    protected NormalPit getOpposite(int distanceToKalaha, boolean hasVisitedKalaha){
        return rightNeighbour.getOpposite(distanceToKalaha, true);
    } 
}
