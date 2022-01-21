
package game;

public class Target extends Placeable {
	String symbol = "+" ;
	public Target(int i, int j) {
		// TODO Auto-generated constructor stub
		toString();
	}
	
	public void addWorker() {
		// TODO Auto-generated method stub
		this.symbol = "W";
	}

	public void addCrate() {
		// TODO Auto-generated method stub 
		this.symbol = "X";
	}
	public String toString() {
		
		return symbol;
	}

	
}
