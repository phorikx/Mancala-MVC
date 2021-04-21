package mancala.domain;

public class Pit {
    private int stones;
    private Player owner;
    private Pit rightNeighbour;

    public void setStones(int numberOfStones) {
        this.stones = numberOfStones;
        return;
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

    public void setOwner(Player player) {
        this.owner = player;
        return;
    }

    public Player getOwner() {return this.owner;}

   /* public void activate() {
        System.out.print("Cannot activate a generic Pit");
    }*/

    public void give(int numberOfStones) {
        this.stones += 1;
        if (numberOfStones > 1) {
            this.rightNeighbour.give(numberOfStones-1);
        } else{
            if(this.getOwner().getTurn()) {
                this.getOwner().getOpponent().takeTurn();
            } else{
                this.getOwner().takeTurn();
            }
        }
    }
    
}
