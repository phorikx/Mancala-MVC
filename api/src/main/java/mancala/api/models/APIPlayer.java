package mancala.api.models;

public class APIPlayer {
    public APIPlayer(mancala.domain.Player player, 
            String name, boolean isFirstPlayer) {
		this.name = name;
		type = isFirstPlayer ? "player1" : "player2";
        hasTurn = player.getTurn();
		this.pits = new APIPit[7];
		var firstHole = isFirstPlayer ? 0 : 7;
		for(int i = 0; i < 6; ++i) {
			this.pits[i] = new APIPit(i + firstHole, player.getSpecificPit(i+1).getStones());
		}
		this.pits[6] = new APIPit(6 + firstHole, player.getKalahaPit().getStones());
    }
    
    String name;
	public String getName() { return name; }
	
	String type;
	public String getType() { return type; }

	boolean hasTurn;
	public boolean getHasTurn() { return hasTurn; }

	APIPit[] pits;
	public APIPit[] getPits() { return pits; }
}