package mancala.api.models;

public class APIPit {
	int index;

	public int getIndex() {
		return index;
	}

	int nrOfStones;

	public int getNrOfStones() { return nrOfStones; }

	public APIPit(int index, int nrOfStones) {
		this.index = index;
		this.nrOfStones = nrOfStones;
	}
}