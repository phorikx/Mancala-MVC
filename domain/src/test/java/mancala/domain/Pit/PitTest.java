package mancala.domain.Pit;

import mancala.domain.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PitTest {
    @Test
    public void hasOneMoreStonesAfterGivingStones() {
        Player player = new Player(null,null);
        int firstStones = player.getFirstPit().getStones();
        player.getFirstPit().give(5);
        int stonesAfterGiving = player.getFirstPit().getStones();
        assertEquals(stonesAfterGiving - firstStones,1);
    }
    
}
