package mancala.domain;
import mancala.domain.Pit.*;

public class MancalaImpl implements MancalaInterface {
    public MancalaImpl() {
        this.firstPlayer = new Player(null, null);
        this.secondPlayer = this.firstPlayer.getOpponent();
    }
    private Player firstPlayer;
    private Player secondPlayer;

    public Player getFirstPlayer() {return this.firstPlayer;}
    public Player getSecondPlayer() {return this.secondPlayer;}

    @Override
    public boolean isPlayersTurn(int player) {
        if(player == 1) {
            return firstPlayer.getTurn();
        } else if (player == 2) {
            return secondPlayer.getTurn();
        } else{
            return false;
        }
    }

    @Override
	public void playPit(int index) throws MancalaException {
        if (index < 6) {
            firstPlayer.getSpecificPit(index+1).Choose();
        } else if (index > 6 && index < 13) {
            secondPlayer.getSpecificPit(index-6).Choose();
        } else{
            throw new MancalaException("not a valid index");
        }
    }
	
	@Override
	public int getStonesForPit(int index) {
        if (index < 6) {
            return firstPlayer.getSpecificPit(index+1).getStones();
        } else if (index == 6) {
            return firstPlayer.getKalahaPit().getStones();
        } else if (index < 13) {
            return secondPlayer.getSpecificPit(index-6).getStones();
        }
        return secondPlayer.getKalahaPit().getStones();
    }

	@Override
	public boolean isEndOfGame() {
        return firstPlayer.checkGameEnd();
    }

	@Override
	public int getWinner() {
        Player[] winners = firstPlayer.determineWinner();
        if (winners.length == 0) {
            return 0;
        } else if (winners.length == 2) {
            return 3;
        } else if (winners[0] == firstPlayer) {
            return 1;
        } 
        return 2;
    }
}