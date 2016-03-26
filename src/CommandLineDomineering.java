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

	    	DomineeringMove move = new DomineeringMove(y, x, Player.MAXIMIZER);
	    	
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
		//board.tree().firstPlayer(new CommandLineDom());
		board.tree().secondPlayer(new CommandLineDom());
	}
}
