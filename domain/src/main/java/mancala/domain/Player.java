package mancala.domain;

import java.util.*;

public class Player{
    private boolean hasTurn;
    private Player opponent;
    private normalPit firstPit;
    private KalahaPit kalahaPit = new KalahaPit(this);

    public boolean getTurn() {
        return this.hasTurn;
    }

    public void setTurn(boolean hasTurn){this.hasTurn=hasTurn;} 

    public normalPit getFirstPit() {
        return firstPit;
    }

    public Player getOpponent() {
        return this.opponent;
    }

    public KalahaPit getKalahaPit() {
        return this.kalahaPit;
    }

   private int checkStonesInPit() {
        int currentPlayerStonesInPit = 0;
        Pit currentPit = this.getFirstPit();
        for (int i = 0; i < currentPit.getTotalNumberOfPits(); i++) {
            currentPlayerStonesInPit += currentPit.getStones();
            currentPit = currentPit.getRightNeighbour();
        }
        return currentPlayerStonesInPit;
    }

    public boolean checkGameEnd() {
        if(this.checkStonesInPit() == 0) {
            return true;
        } else if(this.opponent.checkStonesInPit() == 0) {
            return true;
        } else{
            return false;
        }
    }

    public normalPit getSpecificPit(int numberOfPit) { //Input should start at 1 with the first pit
        normalPit currentPit = this.getFirstPit();
        for (int i = 0; i < Math.min(numberOfPit - 1, currentPit.getTotalNumberOfPits() - 1); i++) {
            currentPit = (normalPit) currentPit.getRightNeighbour();
        }
        return currentPit;
    }

    public Player[] determineWinner() {
        Player[] returnPlayerArrray;
        if(!this.checkGameEnd()) {
             returnPlayerArrray = new Player[]{};
             return returnPlayerArrray;
        } else {
            int thisPlayerScore = this.checkStonesInPit() + this.getKalahaPit().getStones();
            int otherPlayerScore = this.getOpponent().checkStonesInPit() + this.getOpponent().getKalahaPit().getStones();
            if (thisPlayerScore > otherPlayerScore) {
                returnPlayerArrray = new Player[]{this};
                return returnPlayerArrray;
            } else if( thisPlayerScore < otherPlayerScore) {
                returnPlayerArrray = new Player[]{this.getOpponent()};
                return returnPlayerArrray;
            } else{
                returnPlayerArrray = new Player[]{this,this.getOpponent()};
                return returnPlayerArrray;
            }

        }
    }

    public Player(Player Opponent, normalPit firstPit) {
        if (Objects.isNull(firstPit)) {
            this.firstPit = new normalPit(0,this);
        }  
        this.hasTurn = false;    
        if( Objects.isNull(Opponent)) {
            this.hasTurn = true;
            Opponent = new Player(this,null);
        }
        this.opponent = Opponent;        
        this.kalahaPit.setRightNeighbour(this.opponent.getFirstPit());                 
    }

    public void takeTurn(int input) {
        this.hasTurn = true;
        this.opponent.setTurn(false);
        if (this.checkGameEnd()) {
            System.out.print("The game has ended.This is the result:");
            System.out.print(this.determineWinner());
        } else{
            this.getSpecificPit(input).getChosen();
        }
    }

    public void takeTurn() {
        this.hasTurn = true;
        this.opponent.setTurn(false);        
        if (this.checkGameEnd()) {
            System.out.print("The game has ended.This is the result:");
            System.out.print(this.determineWinner());
        }

        // Ask for input. execute input. Example is in the function above, for which the choice is the input of the takeTurn function.
    }

}