package mancala.domain.Pit;

import mancala.domain.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
// Een normalePit creëert ook zijn eigen speler en zijn eigen buurmannen als hij wordt aangeroepen zonder eigenaar.
// Het is echter handiger om een los spelerobject te creëeren, omdat die ook beurten moet uitvoeren waarna het gedrag van de Pits getest wordt.
public class normalPitTest {

    // Bij het creeëren van scenario's is de eerste Pit een 1.
    public static Player createScenario(int[] moveArray) {
        Player player = new Player(null,null);
        Player currentPlayer = player;
        for (int i = 0; i < moveArray.length; i++) {
            currentPlayer.takeTurn(moveArray[i]);
            if (! currentPlayer.getTurn()) {
                currentPlayer = currentPlayer.getOpponent();
            }
        }
        return player;
    }

    @Test
    public void newNormalPitHasFourStones() {
        Player player = new Player(null,null);
        NormalPit firstNormalPit = new NormalPit(0, player);
        assertEquals(firstNormalPit.getStones(),4);
    }

    @Test
    public void normalPitHasNoStonesAfterSelection () {
        Player player = new Player(null,null);
        player.getFirstPit().Choose();
        assertEquals(player.getFirstPit().getStones(),0);
    }

    @Test
    public void doesTurnSwitchWhenNotEndInKalaha () {
        Player player = new Player(null,null);
        player.takeTurn(2);
        assertEquals(player.getTurn(),false);
    }

    @Test
    public void oppositesAreOppositeEachOther () {
        Player player = new Player(null,null);
        assertEquals(player.getFirstPit(), player.getFirstPit().getOpposite().getOpposite());
    }

    @Test 
    public void firstPitOfOpponentIsOppositeLastPitPlayer () {
        Player player = new Player(null,null);
        NormalPit sixthPit = player.getSpecificPit(6);
        sixthPit.getOpposite().Choose();
        assertEquals(player.getOpponent().getFirstPit().getStones(),0);

    }

    @Test
    public void onlyRobsWhenPitIsEmpty() {
        Player player = new Player(null,null);
        player.getFirstPit().robOpposite();
        Pit currentPit = player.getOpponent().getSpecificPit(6);
        assertEquals(currentPit.getStones(),4);
    }

    @Test
    public void doesRobWhenEndPitIsEmpty () {
        // Deze sequence van moves zorgt ervoor dat met zijn laatste zet speler 1 de tegenstander berooft.
        int[] moveArray = {3,5,1,1};
        Player player = createScenario(moveArray);
        assertEquals(player.getSpecificPit(5).getOpposite().getStones(),0);
    }

    @Test
    public void emptiesItselfWhenRobbing () {
        // Deze sequence van moves zorgt ervoor dat met zijn laatste zet speler 1 de tegenstander berooft.
        int[] moveArray = {3,5,1,1};
        Player player = createScenario(moveArray);
        assertEquals(player.getSpecificPit(5).getStones(),0);
    }

    @Test
    public void addsCorrectAmountOfStonesToKalaha () {
        // Deze sequence van moves zorgt ervoor dat met zijn laatste zet speler 1 de tegenstander berooft.
        int[] moveArray = {3,5,1,1};
        Player player = createScenario(moveArray);
        assertEquals(player.getKalahaPit().getStones(),9);
    }
    
}
