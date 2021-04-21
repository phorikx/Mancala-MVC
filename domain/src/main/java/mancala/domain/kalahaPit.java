package mancala.domain;

public class kalahaPit extends Pit{
    public kalahaPit (Player owner) {
        this.setStones(0);
        this.setOwner(owner);
    }
    public kalahaPit () {
        this.setStones(0);
    }

    @Override
    public void give(int numberOfStones) {
        if(this.getOwner().getTurn()) {
            this.setStones(this.getStones() + 1);
            if (numberOfStones > 1) {
                this.getRightNeighbour().give(numberOfStones-1);
            } else{
                this.getOwner().takeTurn();
            }
        } else{
            this.getRightNeighbour().give(numberOfStones);
        }

    }
    
}
