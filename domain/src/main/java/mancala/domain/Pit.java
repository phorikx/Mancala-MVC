package mancala.domain;

public class Pit {
    
    protected int stones;
    protected Player owner;
    protected Pit rightNeighbour;

    // Uiteindelijk 
    protected final int numberOfPits = 6;

    public int getTotalNumberOfPits () {
        return numberOfPits;
    }

    public int getStones() {
        return this.stones;
    }

    public Pit getRightNeighbour() {
        return this.rightNeighbour;
    }

    public void setRightNeighbour(Pit neighbourPit) {
        this.rightNeighbour = neighbourPit;
         return;
        }

    public Player getOwner() {return this.owner;}


    public void give(int numberOfStones) {
        this.stones += 1;
        if (numberOfStones > 1) {
            this.rightNeighbour.give(numberOfStones-1);
        } else{
            this.checkIfLast();
        }
    }

    public void checkIfLast() {
        System.out.print("Should not be activated by regular Pit");
    }
    
}
