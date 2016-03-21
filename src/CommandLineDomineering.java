import java.util.Scanner;

public class CommandLineDomineering {
	
	private static class CommandLineDom implements MoveChannel<DomineeringMove> {
		
		public DomineeringMove getMove() {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter your move (x,y): ");
	    	String input = scanner.nextLine();
	    	String[] splitInput = input.split(",");
	    	if(splitInput.length != 2) {
	    		System.err.println("Your input was wrong!");
	    		System.exit(1);
	    	}
	    	int x = Integer.parseInt(splitInput[0]);
	    	int y = Integer.parseInt(splitInput[1]);
	    	
	    	Cell pos1 = new Cell(x, y);
	    	Cell pos2 = new Cell(x+1 , y);
	    	
	    	DomineeringMove move = new DomineeringMove(pos1, pos2);
	    	
	    	scanner.close();
	    	
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
		System.out.println(board);
		//board.tree().firstPlayer(new CommandLineDom());
		board.tree().secondPlayer(new CommandLineDom());
	}
}
