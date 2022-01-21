package game;

public class Empty extends Placeable{
	String symbol = ".";
	public Empty(int x, int y) {
		// TODO Auto-generated constructor stub
		toString();
	}

	public String toString() {
		
		return symbol;
		
	}

	public void addWorker() {
		this.symbol = "w";
		
	}

	public void addCrate() {
		this.symbol = "x";
		
	}

}
