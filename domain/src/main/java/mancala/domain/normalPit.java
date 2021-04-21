package mancala.domain;

public class normalPit extends Pit{
    public normalPit(int howManyPitsBefore, Player owner) {
        this.setStones(4);
        this.setOwner(owner);
        if(howManyPitsBefore < 5) {            
            normalPit rightNeighbour = new normalPit(howManyPitsBefore + 1,owner);
            this.setRightNeighbour(rightNeighbour);
        } else{
            this.setRightNeighbour(owner.getKalahaPit());
        }
    }

        public void activate() {
        this.getRightNeighbour().give(this.getStones());
        this.setStones(0);
    }
}
