package mancala.domain;

import mancala.domain.Pit.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    public static Player createScenario(int[] moveArray) {
        Player player = new Player(null,null);
        Player currentPlayer = player;
        for (int i = 0; i < moveArray.length; i++) {
            currentPlayer.takeTurn(moveArray[i]);
            if (!currentPlayer.getTurn()) {
                currentPlayer = currentPlayer.getOpponent();
            }
        }
        return player;
    }

    @Test 
    public void firstPlayerHasTurn() {
        Player player = new Player(null,null);
        assertEquals(true, player.getTurn());
    }

    @Test
    public void onlyOnePlayerHasTurn() {
        Player player = new Player(null,null);
        assertEquals(player.getTurn(), !player.getOpponent().getTurn());
    }

    @Test
    public void playersAreOpponentsOfEachother () {
        Player player = new Player(null,null);
        assertEquals(player.getOpponent().getOpponent(), player);
    }

    @Test
    public void playerHasPit () {
        Player player = new Player(null,null);
        assertNotNull(player.getFirstPit());
    }

    @Test
    public void playerHasSixPits () {
        Player player = new Player(null,null);
        int numberOfNormalPits = 0;
        Pit currentPit = player.getFirstPit();
        while(currentPit instanceof normalPit) {
            numberOfNormalPits++;
            currentPit = currentPit.getRightNeighbour();
        }
        assertEquals(numberOfNormalPits,6);
    }

    @Test
    public void opponentHasSixPits () {
        Player player = new Player(null,null);
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
        Player player = new Player(null,null);
        Pit currentPit = player.getFirstPit();
        while(currentPit instanceof normalPit) {
            assertEquals(currentPit.getOwner(), player);
            currentPit = currentPit.getRightNeighbour();
        }
    }

    @Test
    public void playerHasKalaha () {
        Player player = new Player(null,null);
        assertNotNull(player.getKalahaPit());
    }

    @Test
    public void ownerSwitchesAfterKalaha () {
        Player player = new Player(null,null);
        assertNotEquals(player.getKalahaPit().getOwner(), player.getKalahaPit().getRightNeighbour().getOwner());
        assertNotEquals(player.getOpponent().getKalahaPit().getOwner(), player.getOpponent().getKalahaPit().getRightNeighbour().getOwner());
    }

    @Test
    public void playerOwnsOwnKalaha () {
        Player player = new Player(null,null);
        assertEquals(player.getKalahaPit().getOwner(), player);
        assertEquals(player.getOpponent().getKalahaPit().getOwner(), player.getOpponent());
    }

    @Test 
    public void boardIsCircular () {
        Player player = new Player(null,null);
        Pit currentPit = player.getFirstPit();
        for(int i = 0; i  < 14; i++) {
            currentPit = currentPit.getRightNeighbour();
        }
        assertEquals(currentPit, player.getFirstPit());
    }

    @Test 
    public void gameEndsWhenPlayerHasNoStones () {
        // Met deze sequence van moves speelt speler 1 zijn stenen weg.
        int[] moveArray = {1,1,2,3,1,4,1,5,1,6};
        Player player = createScenario(moveArray);
        assertEquals(player.checkGameEnd(),true);
    }

    @Test
    public void gameEndsWhenOpponentHasNoStones () {
        // Met deze sequence van moves speelt speler 2 zijn stenen weg.
        int[] moveArray = {2,1,1,2,3,1,4,1,5,1,6};
        Player player = createScenario(moveArray);
        assertEquals(player.checkGameEnd(),true);
    }

    @Test
    public void correctPlayerWins () {
        // We gebruiken dezelfde twee sequences als bij de vorige twee tests.
        int[] moveArray = {1,1,2,3,1,4,1,5,1,6};
        Player player = createScenario(moveArray);
        assertEquals(player.determineWinner().length,1);
        assertEquals(player.determineWinner()[0],player.getOpponent());
        int[] secondMoveArray= {2,1,1,2,3,1,4,1,5,1,6};
        Player player2 = createScenario(secondMoveArray);
        assertEquals(player2.determineWinner().length,1);
        assertEquals(player2.determineWinner()[0],player2);
    }

    @Test
    public void playerWinsWhenTakingTurn () {
        int[] moveArray = {2,1,1,2,3,1,4,1,5,1,6};
        Player player = createScenario(moveArray);
        player.takeTurn(1);
        assertEquals(player.getFirstPit().getStones(),1);
    }

    public void otherplayerWinsWhenTakingTurn () {
        int[] moveArray = {1,1,2,3,1,4,1,5,1,6};
        Player player = createScenario(moveArray);
        player.takeTurn(1);
        assertEquals(player.getFirstPit().getStones(),0);
    }

    @Test
    public void firstPitIsEmptyAfterTakingTurn () {
        Player player = new Player(null,null);
        player.takeTurn(2);
        assertEquals(player.getSpecificPit(2).getStones(),0);
    }

    @Test 
    public void totalStonesIs48 () {
        Player player = new Player(null,null);
        int totalStones = 0;
        Pit currentPit = player.getFirstPit();
        for (int i = 0; i < 14 ; i++) {
            totalStones += currentPit.getStones();
            currentPit = currentPit.getRightNeighbour();
        }
        assertEquals(totalStones,48);
    }


}