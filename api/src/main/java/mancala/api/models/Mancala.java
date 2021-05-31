package mancala.api.models;

// This package is a collection of DTO's (data transfer objects).
// A DTO is a simple datastructure which models the
// data your web API sends back to the client. The Java
// objects will be converted to JSON objects.
public class Mancala {
    public Mancala(mancala.domain.MancalaImpl Mancala, 
            String namePlayer1, String namePlayer2) {
        players = new APIPlayer[2];
        players[0] = new APIPlayer(Mancala.getFirstPlayer(), namePlayer1, Mancala.isPlayersTurn(1));
        players[1] = new APIPlayer(Mancala.getSecondPlayer(), namePlayer2, Mancala.isPlayersTurn(2));
        gameStatus = new GameStatus(Mancala.getFirstPlayer(), namePlayer1, namePlayer2);
    }

    APIPlayer[] players;
    public APIPlayer[] getPlayers() { return players; }
    
    GameStatus gameStatus;
    public GameStatus getGameStatus() { return gameStatus; }
}