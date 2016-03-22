import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DomineeringBoard extends Board<DomineeringMove> {
    private static final Player VERT = Player.MAXIMIZER;
    private static final Player HORIZ = Player.MINIMIZER;
    
    private final int defaultBoardWidth = 3;
    private final int defaultBoardHeight = 3;


	//TODO : Use an array of Players instead.
    private final DomineeringTile[][] board;
    private final int movesPlayed;
    
    public DomineeringBoard() {
        this.board = new DomineeringTile[this.defaultBoardWidth][this.defaultBoardHeight];
        for(int i = 0; i < this.defaultBoardWidth; i++) {
        	for(int j = 0; j < this.defaultBoardHeight; j++) {
        		this.board[i][j] = new DomineeringTile();
        	}
        }
        this.movesPlayed = 0;
    }
    
    public DomineeringBoard(int m, int n) {
    	this.board = new DomineeringTile[m][n];
    	for(int i = 0; i < m; i++) {
    		for(int j = 0; j < n; j++) {
    			this.board[i][j] = new DomineeringTile();
    		}
    	}
    	this.movesPlayed = 0;
    }
    
    private DomineeringBoard(DomineeringTile[][] board, int movesPlayed) {
    	this.board = board;
    	this.movesPlayed = movesPlayed;
    	//System.out.println(toString());
    }
    
	@Override
	Player nextPlayer() {
	    return (this.movesPlayed % 2 == 0) ? HORIZ : VERT;
	}
	@Override
	Set<DomineeringMove> availableMoves() {
		ArrayList<DomineeringMove> moveslist = new ArrayList<>();
		
		/*
		 * Work out if we're the vertical or 		System.out.println(boardCopy);horizontal player.
		 */
		Player player = this.nextPlayer();
		
		if(player.equals(HORIZ)) {
		    /*
		     * We can't play a move from the 1st row, so start checking
		     * from the 2nd row. To ensure that there's no overlap, only
		     * check up (not down).
		     */
			 for(int i = 0; i < board.length; i++) {
				 for(int j = 1; j < board[i].length; j++) {
					 if(!this.board[i][j].taken) {
						 if(!this.board[i][j - 1].taken) {
							 moveslist.add(new DomineeringMove(new Cell(i, j), new Cell(i, j - 1)));
						 }
					 }
				 }
			 }
		}
		if(player.equals(VERT)) {
			/*
			 * We can't play a move on the last row, so count back from there.
			 * To avoid overlap, only check right (not left).
			 */
			for(int i = board.length - 2; i >= 0; i--) {
				for(int j = 0; j < board[i].length; j++) {
					if(!this.board[i][j].taken) {
						if(!this.board[i+1][j].taken) {
							moveslist.add(new DomineeringMove(new Cell(i, j), new Cell(i + 1, j)));
						}
					}
				}
			}
		}
		Set<DomineeringMove> moves = new HashSet<DomineeringMove>(moveslist);
		
		return moves;
	}
	@Override
	int value() {
		Player player = this.nextPlayer();
		boolean movesLeft = (this.availableMoves().size() == 0) ? false : true;
		if(movesLeft) { 
			return 0; //noone has won yet, so return 0.
		}
		return (player.equals(VERT) ? 1 : -1); //return 1 if VERT has won or -1 if HORIZ has won.
	}
	
	public String toString() {
		String s = "";
		  s += "\n";
		  s += "-";
		  for(int j = 0; j < this.board.length; j++) s += "----";
		  s += "\n";
		  for(int i = 0; i < this.board.length; i++) {
			  s += "|";
			  for(int j = 0; j < this.board[i].length; j++) {
			      s += " " + this.board[i][j] + " " + "|";  	  
			  }
			  s += "\n";
			  for(int j = 0; j < this.board.length; j++) s += "----";
			  s += "-";
			  s += "\n";
		  }
	    return s;
	}
	
	@Override
	Board<DomineeringMove> play(DomineeringMove move) { 
		
		DomineeringTile[][] boardCopy = new DomineeringTile[this.board.length][this.board[0].length];
		for(int i = 0; i < boardCopy.length; i++){
			for(int j = 0; j < boardCopy[i].length; j++) {
				boardCopy[i][j] = this.board[i][j];
			}
		}
		
		Player player = this.nextPlayer();
		
		int movesCopy = this.movesPlayed;
		movesCopy++;
		
		boardCopy[move.posA.column][move.posA.row].flip();
		//boardCopy[move.posA.column][move.posA.row].owner = player;
		boardCopy[move.posB.column][move.posB.row].flip();
		//boardCopy[move.posB.column][move.posB.row].owner = player;
		
		return new DomineeringBoard(boardCopy, movesCopy);
	}  
	
	public DomineeringBoard clear() {
		DomineeringTile[][] boardCopy = new DomineeringTile[this.board.length][this.board[0].length];
		for(int i = 0; i < boardCopy.length; i++) {
			for(int j = 0; j < boardCopy[0].length; j++) {
				boardCopy[i][j] = this.board[i][j];
				boardCopy[i][j].taken = false;
			}
		}
		return new DomineeringBoard(boardCopy, this.movesPlayed);
	}
}
