package mancala.domain;

enum Winner {
    PLAYER1,
    PLAYER2,
    DRAW,
    NOWINNER    
}

public class Player{
    private boolean hasTurn;
    private Player opponent;
    private normalPit firstPit;
    private kalahaPit playersKalahaPit = new kalahaPit(this);
    public final Winner identifier;

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

    public void setOpponent(){
        this.opponent = new Player(false);
        this.opponent.opponent = this;
        this.opponent.playersKalahaPit.setRightNeighbour(this.firstPit);
    }

    public kalahaPit getKalahaPit() {
        return this.playersKalahaPit;
    }

    public int checkStonesInPit() {
        int currentPlayerStonesInPit = 0;
        Pit currentPit = this.getFirstPit();
        for (int i = 0; i < 5; i++) {
            currentPlayerStonesInPit = currentPlayerStonesInPit + currentPit.getStones();
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

    public Winner determineWinner() {
        if(!this.checkGameEnd()) {
            return Winner.NOWINNER;
        } else {
            int thisPlayerScore = this.checkStonesInPit() + this.getKalahaPit().getStones();
            int otherPlayerScore = this.getOpponent().checkStonesInPit() + this.getOpponent().getKalahaPit().getStones();
            if (thisPlayerScore > otherPlayerScore) {
                return this.identifier;
            } else if( thisPlayerScore < otherPlayerScore) {
                return this.opponent.identifier;
            } else{
                return Winner.DRAW;
            }

        }
    }

    public Player(boolean isFirstPlayer) {     
        this.firstPit = new normalPit(0,this);   
        if(isFirstPlayer) {
            this.identifier= Winner.PLAYER1;
            this.hasTurn = true;
            this.setOpponent();
            this.playersKalahaPit.setRightNeighbour(opponent.getFirstPit());            
        } else {
            this.identifier=Winner.PLAYER2;
            this.hasTurn = false;
        }        
    }

    public void takeTurn(int input) {
        this.hasTurn = true;
        this.opponent.setTurn(false);
        if (this.checkGameEnd()) {
            System.out.print("The game has ended.This is the result:");
            System.out.print(this.determineWinner());
        } else{
            normalPit currentPit = this.getFirstPit();
            for (int i = 0; i < Math.max(input-1,5); i++) {
                currentPit = (normalPit) currentPit.getRightNeighbour();
            }
            currentPit.activate();
        }
    }

    public void takeTurn() {
        this.hasTurn = true;
        this.opponent.setTurn(false);
        if (this.checkGameEnd()) {
            System.out.print("The game has ended.This is the result:");
            System.out.print(this.determineWinner());
        }
    }

}