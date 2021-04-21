package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    public Player createScenario(int[] stonesArray) {
        Player player = new Player(true);
        Pit currentPit = player.getFirstPit();
        for (int i = 0; i < 14; i++) {
            currentPit.setStones(stonesArray[i]);
            currentPit = currentPit.getRightNeighbour();
        }
        return player;
    }

    @Test 
    public void firstPlayerHasTurn() {
        Player player = new Player(true);
        assertEquals(true, player.getTurn());
    }

    @Test
    public void onlyOnePlayerHasTurn() {
        Player player = new Player(true);
        assertEquals(player.getTurn(), !player.getOpponent().getTurn());
    }

    @Test
    public void playersAreOpponentsOfEachother () {
        Player player = new Player(true);
        assertEquals(player.getOpponent().getOpponent(), player);
    }

    @Test
    public void playerHasPit () {
        Player player = new Player(true);
        assertNotNull(player.getFirstPit());
    }

    @Test
    public void playerHasSixPits () {
        Player player = new Player(true);
        int numberOfNormalPits = 0;
        Pit currentPit = player.getFirstPit();
        while(currentPit instanceof normalPit) {
            numberOfNormalPits++;
            currentPit = currentPit.getRightNeighbour();
        }
        assertEquals(numberOfNormalPits,6);
    }

    @Test
    public void opponentHaSixPits () {
        Player player = new Player(true);
        int numberOfNormalPits = 0;
        Pit currentPit = player.getOpponent().getFirstPit();
        while(currentPit instanceof normalPit) {
            numberOfNormalPits++;
            currentPit = currentPit.getRightNeighbour();
        }
        assertEquals(numberOfNormalPits,6);

    }

    @Test 
    public void playerOwnsOwnPits () {
        Player player = new Player(true);
        Pit currentPit = player.getFirstPit();
        while(currentPit instanceof normalPit) {
            assertEquals(currentPit.getOwner(), player);
            currentPit = currentPit.getRightNeighbour();
        }
    }

    @Test
    public void playerHasKalaha () {
        Player player = new Player(true);
        assertNotNull(player.getKalahaPit());
    }

    @Test
    public void ownerSwitchesAfterKalaha () {
        Player player = new Player(true);
        assertNotEquals(player.getKalahaPit().getOwner(), player.getKalahaPit().getRightNeighbour().getOwner());
        assertNotEquals(player.getOpponent().getKalahaPit().getOwner(), player.getOpponent().getKalahaPit().getRightNeighbour().getOwner());
    }

    @Test
    public void playerOwnsOwnKalaha () {
        Player player = new Player(true);
        assertEquals(player.getKalahaPit().getOwner(), player);
        assertEquals(player.getOpponent().getKalahaPit().getOwner(), player.getOpponent());
    }

    @Test 
    public void boardIsCircular () {
        Player player = new Player(true);
        Pit currentPit = player.getFirstPit();
        for(int i = 0; i  < 14; i++) {
            currentPit = currentPit.getRightNeighbour();
        }
        assertEquals(currentPit, player.getFirstPit());
    }

    @Test 
    public void gameEndsWhenPlayerHasNoStones () {
        int[] stonesArray = {0,0,0,0,0,0,24,1,2,3,4,5,6,7};
        Player player = createScenario(stonesArray);
        assertEquals(player.checkGameEnd(),true);
    }

    @Test
    public void gameEndsWhenOpponentHasNoStones () {
        int[] stonesArray = {1,2,3,4,5,6,7,0,0,0,0,0,0,8};
        Player player = createScenario(stonesArray);
        assertEquals(player.checkGameEnd(),true);
    }

    @Test
    public void correctPlayerWins () {
        int[] stonesArray = {1,2,3,4,5,6,24,0,0,0,0,0,0,8};
        Player player = createScenario(stonesArray);
        assertEquals(player.determineWinner(),Winner.PLAYER1);
        int[] secondStonesArray= {0,0,0,0,0,0,8,1,2,3,4,5,6,24};
        Player player2 = createScenario(secondStonesArray);
        assertEquals(player2.determineWinner(),Winner.PLAYER2);
    }

    @Test
    public void firstPitIsEmptyAfterTakingTurn () {
        Player player = new Player(true);
        player.takeTurn(1);
        assertEquals(player.getFirstPit().getStones(),0);
    }
}