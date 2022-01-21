package game;



import java.util.ArrayList;
import java.util.List;

public class Game {
	int levelcount = 0;
	int movecount = 0;
	int targetsleft = 0;
	int targetscompleted = 0;
	
	int width =0; //height and width of map
	int height = 0;
	
	int workerx = 0; //x and y of the worker
	int workery = 0;
	
	int cratex = 0; //x and y of the crate
	int cratey = 0;
	
	String underneath = ""; // the object underneath
	String leveldata = "";//leveldata as a string
	String levelname =""; //level name
	
	Placeable[][] levelplaceable = null; //leveldata as a 2d array
	
	protected List<Level> allMyLevels = new ArrayList<Level>();	 //list of all the levels
	
	
	public void addLevel(String string, int i, int j, String string2) {
		Level level = new Level(string, i, j, string2);
		this.levelcount++; //add level count every level made
		this.height = j; //height of map
		this.width = i; //width of map
		this.workerx = level.workerx; //worker x position on array
		this.workery = level.workery; //worker y position on array
		this.levelname = level.getName(); //returns level name from level class
		this.allMyLevels.add(level); // adds level to allmylevels arraylist
		this.targetscompleted = level.getTargetCount(); //gets the total targets on the map
		this.levelplaceable = level.getplaceable(); // gets the level 2d array from level
		
	}
	
	
	public void removeObject() {
			Empty empty = new Empty(0,0);
			this.levelplaceable[this.workerx][this.workery] = empty; //default set the selexted x/y to an empty
		
			if (this.underneath == "+") {
				Target target = new Target(0,0);
				this.levelplaceable[this.workerx][this.workery] = target; // when a worker is on a target when it moves away the map position goes back to a +
			}
	}
	
	
	public void addCrate(int x, int y) {
		
		if (this.checkObjectNext(x, y) == "+") { //checks next object
			Target target = new Target(0,0);
			target.addCrate(); // addsa a crate on target symbol
			this.levelplaceable[x][y] = target; 
		}
		
		if(this.checkObjectNext(x,y) == ".") {
			Crate crate = new Crate(0,0);
			this.levelplaceable[x][y] = crate; 
		}
	}
	
	
	public void addWorker(int x,int y) {
		
		removeObject();
		
		if (this.checkObjectNext(x, y) == ".") {
			this.underneath = ".";
			
			Empty empty = new Empty(x,y);
			empty.addWorker();
			this.levelplaceable[x][y] = empty;
			this.workerx = x;
			this.workery = y;
			
		}
		
		if (this.checkObjectNext(x, y) == "+") {
			this.underneath = "+";
			
			Target target = new Target(x,y);
			target.addWorker();
			this.levelplaceable[x][y] = target;
			this.workerx = x;
			this.workery = y;
			
		}
		
		if (this.checkObjectNext(x, y) == "#") {
			
			Worker worker = new Worker(x,y);
			this.levelplaceable[this.workerx][this.workery] = worker;
			if (this.underneath == "+") {
				Target target = new Target(0,0);
				target.addWorker();
				this.levelplaceable[this.workerx][ this.workery] = target;
			}
		}
		
		if (this.checkObjectNext(x, y) == "x") {
			
			this.underneath = "x";
			
			this.cratex = x;
			this.cratey = y;
			
			Empty empty = new Empty(x,y);
			empty.addWorker();
			this.levelplaceable[x][y] = empty;
			
			this.workerx = x;
			this.workery = y;
		}
		
		if (this.checkObjectNext(x, y) == "X") {
			this.underneath = "X";
			this.cratex = x;
			this.cratey = y;
			this.workerx = x;
			this.workery = y;
			
			addCrate(this.workerx,this.workery+1);

			this.underneath = "+";
			
			Target empty = new Target(x,y);
			empty.addWorker();
			
			this.levelplaceable[x][y] = empty;
		}		
	}
	
	
	public void move(Direction where) {
		
		if (where == Direction.LEFT) { //moves worker left 
			addWorker(this.workerx,this.workery-1);
			if (this.underneath == "x" || this.underneath == "X") {
				addCrate(this.workerx,this.workery-1);
				
			}
			addMoveCount();	
		}
		
		else if(where == Direction.RIGHT) { //moves worker right
			addWorker(this.workerx,this.workery+1);
			if (this.underneath == "x" || this.underneath == "X") {
				addCrate(this.workerx,this.workery+1);
				
			}
			addMoveCount();
		}
		
		else if(where == Direction.DOWN) {//move worker down
			addWorker(this.workerx+1,this.workery);
			if (this.underneath == "x" || this.underneath == "X") {
				addCrate(this.workerx+1,this.workery);
				
			}
			addMoveCount();
		}
		
		else if(where == Direction.UP) { //moves worker up 
			addWorker(this.workerx-1,this.workery);
			if (this.underneath == "x" || this.underneath == "X") {
				addCrate(this.workerx-1,this.workery);
				
			}
			addMoveCount(); 
		}
		checkCompleted();
	}
	
	
	public String CheckWorkerTile(int x, int y) {
		String string = "";
		string = this.levelplaceable[x][y].toString();
		return string;
	}
	
	
	public String checkObjectNext(int x, int y) {
		String result = "";
		if(this.levelplaceable[x][y].toString() == "#" ) {
			result = "#";
		}
		if(this.levelplaceable[x][y].toString() == "." ) {
			result = ".";
		}
		if(this.levelplaceable[x][y].toString() == "x" ) {
			result = "x";
			
		}
		if(this.levelplaceable[x][y].toString() == "+" ) {
			result = "+";
		}
		if(this.levelplaceable[x][y].toString() == "X" ) {
			result = "X";
		}
		return result;
	}
	
	
	public int getLevelCount() {
		return this.levelcount;
	}
	
	
	public void addMoveCount() {
		this.movecount++;
	}
	

	public String getCurrentLevelName() {
		String str = "";
		if (this.levelcount  == 0) {
			str = "no levels";
		}else if (this.levelcount > 0) {
			str = this.levelname;
		}
		return str;
	}


	public String getMoveCount() {
		
		int count = this.movecount;
		String countstr = "move " + count;
		return countstr;
	}
	
	
	public void checkCompleted() {
		int result = 0;
		for (int i = 0;i<this.width;i++) {
			for(int j = 0 ; j< this.height;j++) {
				String c = this.levelplaceable[i][j].toString();
				if (c == "X") {
					result++;
					
				}
			}
		}
		
		this.targetsleft = result;
	}
	
	
	public String getCompleted() {
		int tocomplete = this.targetsleft;
		
		int completed = this.targetscompleted;
		String completestr = "completed " + tocomplete + " of " + completed;
		this.targetsleft=0;
		return completestr;
	}
	
	
	public void getLevelMap() {
		String string ="";
		int x = 0;
		
		for (int i = 0; i< this.width;i++) {
			for(int j = 0;j<this.height;j++) {
				string = string + this.updateLevel().charAt(x);
				x++;
				
			}
		string = string + "\n";		
		}
		this.leveldata = string;
	}
	
	
	public String updateLevel() {
		String string = "";
		for(int i = 0; i<this.width;i++) {
			
			for(int j = 0; j<this.height;j++) {
			string = string + this.levelplaceable[i][j].toString();
			
			}
		}
		return string.trim();	
	}

	
	public String toString(){
		
		this.levelcount = getLevelCount();
		if (this.levelcount == 0) {
		  return "no levels";
		 }
		else if (this.levelcount > 0){
			 getLevelMap();
			 
			return this.getCurrentLevelName() 
					+ "\n" + this.leveldata 
					+ this.getMoveCount()
					+"\n"+ this.getCompleted()  
					+ "\n";
		 }
		 else {
			return null;
		 }
	}
	
	
	public List<String> getLevelNames() {

		List<String> allLevelNames = new ArrayList<String>();
		if (this.levelcount  != 0) {
		for(int i = 0 ; i < this.levelcount; i++) {
			String name = getCurrentLevelName();
			allLevelNames.add(name);
			}
		}
		return allLevelNames;	
	}
}