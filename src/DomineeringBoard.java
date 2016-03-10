import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import com.sun.rowset.internal.Row;

public class DomineeringBoard extends Board<DomineeringMove> {
    private static final Player VERT = Player.MAXIMIZER;
    private static final Player HORIZ = Player.MINIMIZER;
    
    private final DomineeringTile[][] board;
    private final int movesPlayed;
    
    public DomineeringBoard() {
        this.board = new DomineeringTile[4][4];
        for(int i = 0; i < 4; i++) {
        	for(int j = 0; j < 4; j++) {
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
    }
    
	@Override
	Player nextPlayer() {
	    return (this.movesPlayed % 2 == 0) ? VERT : HORIZ;
	}
	@Override
	Set<DomineeringMove> availableMoves() {
		Set<DomineeringMove> moves = new TreeSet<DomineeringMove>();
		/*
		 * Work out if we're the vertical or horizontal player.
		 */
		Player player = this.nextPlayer();
		if(player.equals(VERT)) {
		    /*
		     * We can't play a move from the 1st row, so start checking
		     * from the 2nd row. To ensure that there's no overlap, only
		     * check up (not down).
		     */
			 for(int i = 0; i < board.length; i++) {
				 for(int j = 1; j < board[i].length; j++) {
					 if(!this.board[i][j].taken) {
						 if(!this.board[i][j - 1].taken) {
							 moves.add(new DomineeringMove(new Cell(i, j), new Cell(i, j - 1)));
						 }
					 }
				 }
			 }
		}
		if(player.equals(HORIZ)) {
			/*
			 * We can't play a move on the last row, so count back from there.
			 * To avoid overlap, only check right (not left).
			 */
			for(int i = board.length - 2; i >= 0; i--) {
				for(int j = 0; j < board[i].length; j++) {
					if(!this.board[i][j].taken) {
						if(!this.board[i+1][j].taken) {
							moves.add(new DomineeringMove(new Cell(i, j), new Cell(i + 1, j)));
						}
					}
				}
			}
		}
		return moves;
	}
	@Override
	int value() {
		Player player = this.nextPlayer();
		int moveNum = availableMoves().size();
		return (player.equals(VERT)) ? moveNum : -moveNum;
	}
	
	public String toString() {
		String s = "";
		  for(int i = 0; i < this.board.length; i++) {
			  for(int j = 0; i < this.board[i].length; j++) {
			      s += " " + this.board[i][j] + " " + "|";  	  
			  }
			  s += "\n" + "---------------------------------------------" + "\n";
		  }
	    return s;
	}
	
	@Override
	Board<DomineeringMove> play(DomineeringMove move) {
		if(this.board[move.posA.column][move.posA.row].taken || this.board[move.posB.column][move.posB.row].taken) {
			System.err.println("TRIED TO PLAY IN A SPACE THAT WAS ALREADY TAKEN!");
			System.exit(1);
		}
		
		DomineeringTile[][] boardCopy = new DomineeringTile[this.board.length][this.board[0].length];
		for(int i = 0; i < boardCopy.length; i++){
			for(int j = 0; j < boardCopy[i].length; j++) {
				boardCopy[i][j] = this.board[i][j];
			}
		}
		return new DomineeringBoard(boardCopy, (this.movesPlayed + 1));
	}  
}
