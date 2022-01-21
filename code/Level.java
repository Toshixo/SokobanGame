package game;

public class Level {
	String levelname;
	int width;
	int height;
	String  leveldata;
	int  targetCount;
	int completedcount;
	Game game;
	int movecount = 0;
	int workerx = 0;
	int workery =0;
	
	String[][] level = null;
	Placeable[][] allPlaceables;

	
	public Level(String a, int b, int c, String d) {
		
		this.levelname = a;
		this.width = c;
		this.height = b;
		this.leveldata = d;
	    int index = 0;
	    Placeable newTile = null;
	    this.allPlaceables = new Placeable[this.height][this.width];
		for (int x = 0; x < b; x ++) { //2D
			for (int y = 0; y < c; y ++) { // 2D
				char symbol = this.leveldata.charAt(index);
				index++;
				newTile = this.makePlacable(x, y, symbol);
				this.allPlaceables[x][y] = newTile;
				 // 1D
		    }
		}
	}
	
	
	protected Placeable makePlacable(int x, int y, char symbol) {
		// TODO Auto-generated method stub
		Placeable placeable = null;
		switch(symbol) {
		case'#':{
			placeable = new  Wall(x,y);
			break;
			}
		case'.':{
			placeable = new Empty(x,y);
			break;
			}
		case'w':{
			placeable = new Worker(x,y);
			this.workerx = x;
			this.workery = y;
			break;
			}
		case'+':{
			placeable = new Target(x,y);
			break;
			}
		case'x':{
			placeable = new Crate(x,y);
			break;
			}
		}
		return placeable;
	}


	public int getWidth() {
		return this.width;
	}

	
	public int getHeight() {
		return this.height;
	}

	
	public int getMoveCount() {
		return this.movecount;
		
	}
	
	
	public Placeable[][] getplaceable(){
		return this.allPlaceables;
	}

	
	public Integer getCompletedCount() {
		int counter = 0;
		for(int i = 0; i < this.leveldata.length(); i++) {
			if(this.leveldata.charAt(i) == 'X') {
				counter++;
			}
		}
		return counter;	
	}

	
	public String getName() {
		// TODO Auto-generated method stub
		return this.levelname;
	}
	
	
	public int getTargetCount() {
		int counter = 0;
		for(int i = 0; i < this.leveldata.length(); i++) {
			if(this.leveldata.charAt(i) == '+' || this.leveldata.charAt(i) == 'X') {
				counter++;
			}
		}
		return counter;
	}
	
	
	public String getLevelData() {
		String string = "";
		
		for(int i = 0; i<height;i++) {
			
			for(int j = 0; j<width;j++) {
			string = string + this.allPlaceables[i][j];
			}
			
		}
		return string;
	}
	
	
	public String getLevelMap() {
		String string ="";
		int x = 0;
		
		for (int i = 0; i< 5;i++) {
			for(int j = 0;j<6;j++) {
				string = string + getLevelData().charAt(x);
				x++;
			}
			string = string + "\n";			
		}
		return string;
	}
		
	
	public String toString() {
		
		String str =  
				this.levelname +"\n"
				+ this.getLevelMap() 
				+ "move "
				+ this.getMoveCount() 
				+ "\n" + "completed "
				+ this.getCompletedCount() 
				+ " of "
				+ this.getTargetCount() + "\n";
		return str;
		
	}

}