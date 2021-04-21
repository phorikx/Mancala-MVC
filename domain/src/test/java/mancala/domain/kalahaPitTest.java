package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class kalahaPitTest {
    @Test
    public void newKalahaPitHasNoStones() {
        kalahaPit firstKalahaPit = new kalahaPit();
        assertEquals(firstKalahaPit.getStones(),0);
    }

    @Test
    public void kalahaPitGetsStoneWhenCurrentPlayerIsOwner() {
        Player player = new Player(true);
        int firstStones = player.getKalahaPit().getStones();
        player.getKalahaPit().give(4);
        int secondStones = player.getKalahaPit().getStones();
        assertEquals(secondStones-firstStones,1);
    }

    @Test
    public void kalahaPitGetsNoStoneWhenCurrentPlayerIsNotOwner() {
        Player player = new Player(true);
        int firstStones = player.getOpponent().getKalahaPit().getStones();
        player.getOpponent().getKalahaPit().give(4);
        int secondStones = player.getOpponent().getKalahaPit().getStones();
        assertEquals(secondStones-firstStones,0);
    }

    @Test 
    public void doesTurnSwitchWhenEndInKalaha() {
        Player player = new Player(true);
        player.takeTurn(2);
        assertEquals(player.getTurn(),true);
    }
}
