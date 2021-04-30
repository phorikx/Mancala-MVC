package mancala.domain.Pit;

import java.util.*;
import mancala.domain.*;

public class NormalPit extends Pit{
    

    public NormalPit(int howManyPitsBefore, Player owner) {
        if (Objects.isNull(owner)) {
            this.owner = new Player(null, this);
        }
        this.stones = 4;
        this.owner = owner;
        if(howManyPitsBefore < numberOfPits-1) {            
            NormalPit rightNeighbour = new NormalPit(howManyPitsBefore + 1,owner);
            this.setRightNeighbour(rightNeighbour);
        } else{
            this.setRightNeighbour(owner.getKalahaPit());
        }
    }

    public void Choose() {
        if (this.stones == 0) {
            System.out.println("geen geldige keuze.");
            this.owner.takeTurn();
        } else {
        int currentStones = this.getStones();
        this.stones = 0;       
        this.getRightNeighbour().give(currentStones);
        }        
    }

    public NormalPit getOpposite() {
        return rightNeighbour.getOpposite(0,false);
    }

    protected NormalPit getOpposite(int distanceToKalaha, boolean hasVisitedKalaha){
        if (!hasVisitedKalaha) {
            return rightNeighbour.getOpposite(distanceToKalaha+1, false);
        }
        if (distanceToKalaha == 0) {
            return this;
        }
        return rightNeighbour.getOpposite(distanceToKalaha-1,true);
    } 

    public void robOpposite() {
        if (this.getStones() == 1) {
            this.getOwner().getKalahaPit().giveStolenStones(this.getStones());
            this.stones = 0;
            this.getOpposite().getRobbed();
        }
    }

    @Override
    public void executeWhenLastPit() {
        if(this.getStones() == 1) {
            this.robOpposite();
        }
        if(this.getOwner().getTurn()) {
            this.getOwner().getOpponent().takeTurn();
        } else{
            this.getOwner().takeTurn();
        }
    }

    public void getRobbed() {
        this.getOwner().getOpponent().getKalahaPit().giveStolenStones(this.getStones());
        this.stones = 0;
    }
}
