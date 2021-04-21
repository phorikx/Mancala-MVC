package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class normalPitTest {

    @Test
    public void newNormalPitHasFourStones() {
        Player player = new Player(true);
        normalPit firstNormalPit = new normalPit(0, player);
        assertEquals(firstNormalPit.getStones(),4);
    }

    @Test
    public void normalPitHasNoStonesAfterSelection () {
        Player player = new Player(true);
        player.getFirstPit().activate();
        assertEquals(player.getFirstPit().getStones(),0);
    }

    @Test
    public void doesTurnSwitchWhenNotEndInKalaha () {
        Player player = new Player(true);
        player.takeTurn(2);
        assertEquals(player.getTurn(),false);
    }
    
}
