package mancala.api.models;

public class GameStatus {
    boolean endOfGame;
    public boolean getEndOfGame() { return endOfGame; }
    
    String winner;
    public String getWinner() { return winner; }

    public GameStatus(mancala.domain.Player player, 
            String namePlayer1, String namePlayer2) {
        this.endOfGame = player.checkGameEnd();
        mancala.domain.Player[] winner = player.determineWinner();
        if(winner.length == 0) {
            this.winner = null;
        } else if(winner.length == 1 && winner[0] == player) {
            this.winner = namePlayer1;
        } else if(winner.length == 1 && winner[0] == player.getOpponent()) {
            this.winner = namePlayer2;
        } else {
            this.winner = namePlayer1  + "and" + namePlayer2;
        }
    }
}