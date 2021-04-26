package mancala.domain;
import java.util.*;

public class normalPit extends Pit{
    

    public normalPit(int howManyPitsBefore, Player owner) {
        if (Objects.isNull(owner)) {
            this.owner = new Player(null, this);
        }
        this.stones = 4;
        this.owner = owner;
        if(howManyPitsBefore < numberOfPits-1) {            
            normalPit rightNeighbour = new normalPit(howManyPitsBefore + 1,owner);
            this.setRightNeighbour(rightNeighbour);
        } else{
            this.setRightNeighbour(owner.getKalahaPit());
        }
    }

    public void getChosen() {
        if (this.stones == 0) {
            System.out.println("geen geldige keuze.");
            this.owner.takeTurn();
        } else {
        int currentStones = this.getStones();
        this.stones = 0;       
        this.getRightNeighbour().give(currentStones);
        }        
    }

    public normalPit getOpposite() {
        Pit currentPit = this;
        int distanceToKalaha = 0;
        while (!(currentPit instanceof KalahaPit)) {
            distanceToKalaha++;
            currentPit = currentPit.getRightNeighbour();
        }
        while(distanceToKalaha > 0) {
            currentPit = currentPit.getRightNeighbour();
            distanceToKalaha--;
        }
        return (normalPit) currentPit;
    }

    public void robOpposite() {
        if (this.getStones() == 1) {
            this.getOwner().getKalahaPit().giveStolenStones(this.getStones());
            this.stones = 0;
            this.getOpposite().getRobbed();
        }
    }

    public void checkIfLast() {
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
