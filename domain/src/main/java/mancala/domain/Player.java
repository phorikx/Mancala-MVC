package mancala.domain;

import java.util.*;
import mancala.domain.Pit.*;

public class Player{
    private boolean hasTurn;
    private Player opponent;
    private NormalPit firstPit;
    private KalahaPit kalahaPit = new KalahaPit(this);

    public Player(Player Opponent, NormalPit firstPit) {
        if (Objects.isNull(firstPit)) {
            this.firstPit = new NormalPit(0,this);
        }  
        this.hasTurn = false;    
        if( Objects.isNull(Opponent)) {
            this.hasTurn =true;
            Opponent = new Player(this,null);
        }
        this.opponent = Opponent;        
        this.kalahaPit.setRightNeighbour(this.opponent.getFirstPit());                 
    }

    public boolean getTurn() {
        return this.hasTurn;
    }

    //public void setTurn(boolean hasTurn){this.hasTurn=hasTurn;} 

    public NormalPit getFirstPit() {
        return firstPit;
    }

    public Player getOpponent() {
        return this.opponent;
    }

    public KalahaPit getKalahaPit() {
        return this.kalahaPit;
    }

   private int checkStonesInPits() {
        int currentPlayerStonesInPit = 0;
        Pit currentPit = this.getFirstPit();
        for (int i = 0; i < currentPit.getTotalNumberOfPits(); i++) {
            currentPlayerStonesInPit += currentPit.getStones();
            currentPit = currentPit.getRightNeighbour();
        }
        return currentPlayerStonesInPit;
    }

    public boolean checkGameEnd() {
        if(this.checkStonesInPits() == 0) {
            return true;
        } else if(this.opponent.checkStonesInPits() == 0) {
            return true;
        } else{
            return false;
        }
    }

    public NormalPit getSpecificPit(int numberOfPit) { //Input should start at 1 with the first pit
        NormalPit currentPit = this.getFirstPit();
        for (int i = 0; i < Math.min(numberOfPit - 1, currentPit.getTotalNumberOfPits() - 1); i++) {
            currentPit = (NormalPit) currentPit.getRightNeighbour();
        }
        return currentPit;
    }

    public Player[] determineWinner() {
        Player[] returnPlayerArrray;
        if(!this.checkGameEnd()) {
             returnPlayerArrray = new Player[]{};
             return returnPlayerArrray;
        } else {
            int thisPlayerScore = this.checkStonesInPits() + this.getKalahaPit().getStones();
            int otherPlayerScore = this.getOpponent().checkStonesInPits() + this.getOpponent().getKalahaPit().getStones();
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

    // We hebben twee functies om een beurt te nemen. Eentje met input die we nu gebruiken als we een sequence 
    // van moves willen testen, en een andere die nog niks uitvoert, maar theoretisch gezien wacht op input van buitenaf.
    // We hebben ze allebei nodig, omdat we in dit geval soms wel een specifieke beurt willen laten uitvoeren, maar
    // vaak ook de beurt aan een speler kunnen geven zonder meteen een input daarbij te geven

    public void takeTurn(int input) {
        this.hasTurn = true;
        this.opponent.hasTurn = false;
        if (this.checkGameEnd()) {
            System.out.print("The game has ended.This is the result:");
            System.out.print(this.determineWinner());
        } else{
            this.getSpecificPit(input).Choose();
        }
    }

    public void takeTurn() {
        this.hasTurn = true;
        this.opponent.hasTurn = false;        
        if (this.checkGameEnd()) {
            System.out.print("The game has ended.This is the result:");
            System.out.print(this.determineWinner());
        }

        // Ask for input. execute input. Example is in the function above, for which the choice is the input of the takeTurn function.
    }

}