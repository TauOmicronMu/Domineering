
public class CommandLineDomineering {

	private static class CommandLineDom implements MoveChannel<DomineeringMove> {
		
		public DomineeringMove getMove() {
	    	String input = System.console().readLine("Enter your move (x1,y1,x2,y2): ");
	    	String[] splitInput = input.split(",");
	    	if(splitInput.length != 4) {
	    		System.err.println("Your input was wrong!");
	    		System.exit(1);
	    	}
	    	int x1 = Integer.parseInt(splitInput[0]);
	    	int y1 = Integer.parseInt(splitInput[1]);
	    	int x2 = Integer.parseInt(splitInput[2]);
	    	int y2 = Integer.parseInt(splitInput[3]);
	    	
	    	Cell pos1 = new Cell(x1, y1);
	    	Cell pos2 = new Cell(x2, y2);
	    	
	    	DomineeringMove move = new DomineeringMove(pos1, pos2);
	    	
	    	return move;
	    }
		
	    public void giveMove(DomineeringMove move) {
	    	System.out.println("I play " + move);
	    }
	    
	    public void comment(String msg) {
	    	System.out.println(msg);
	    }
	    
	    public void end(int value) {
	    	System.out.println("Game over. The result is " + value);
	    }
	}
    
	public static void main(String[] args) {
		DomineeringBoard board = new DomineeringBoard();
		board.tree().secondPlayer(new CommandLineDom());
	}
}
