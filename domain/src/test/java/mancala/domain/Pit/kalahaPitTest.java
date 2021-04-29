package mancala.domain.Pit;

import mancala.domain.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class kalahaPitTest {
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
    public void newKalahaPitHasNoStones() {
        KalahaPit firstKalahaPit = new KalahaPit();
        assertEquals(firstKalahaPit.getStones(),0);
    }

    @Test
    public void kalahaPitGetsStoneWhenCurrentPlayerIsOwner() {
        Player player = new Player(null,null);
        int firstStones = player.getKalahaPit().getStones();
        player.getKalahaPit().give(4);
        int secondStones = player.getKalahaPit().getStones();
        assertEquals(secondStones-firstStones,1);
    }

    @Test
    public void kalahaPitGetsNoStoneWhenCurrentPlayerIsNotOwner() {
        Player player = new Player(null,null);
        int firstStones = player.getOpponent().getKalahaPit().getStones();
        player.getOpponent().getKalahaPit().give(4);
        int secondStones = player.getOpponent().getKalahaPit().getStones();
        assertEquals(secondStones-firstStones,0);
    }

    @Test 
    public void doesTurnSwitchWhenEndInKalaha() {
        Player player = new Player(null,null);
        player.takeTurn(3);
        assertEquals(player.getTurn(),true);
    }

    @Test
    public void skipsEnemyKalaha() {
        int[] moveArray = {2,2,3,3,1,4,4,2,6};
        Player player = createScenario(moveArray);
        //player.takeTurn();
        assertEquals(player.getFirstPit().getStones(),2);
    }
}
