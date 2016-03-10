import java.util.ArrayList;

public class DomineeringBoard extends Board<DomineeringMove> {
	/*
	 * We will always play as the Vertical player.
	 */
    private static final Player VERTICAL = Player.MAXIMIZER;
    private static final Player HORIZONTAL = Player.MINIMIZER;
    
    private final ArrayList<DomineeringMove> verticalMoves;
    private final ArrayList<DomineeringMove> horizontalMoves;
    
    public DomineeringBoard() {
    	this.verticalMoves = new ArrayList<>();
    	this.horizontalMoves = new ArrayList<>();
    }
    
    private DomineeringBoard(ArrayList<DomineeringMove> verticalMoves, ArrayList<DomineeringMove> horizontalMoves) {
        this.verticalMoves = verticalMoves;
        this.horizontalMoves = horizontalMoves;
    }
    
    public Player nextPlayer() {
    	return ((this.verticalMoves.size() + this.horizontalMoves.size()) % 2 == 0 ? VERTICAL : HORIZONTAL);
    }
    
    public ArrayList<DomineeringMove> availableMoves() {
    	/*
    	 * First of all, work out which player is going next. This will change
    	 * the available moves.
    	 */
    	Player nextPlayer = this.nextPlayer();
    	/*
    	 * A move is available iff:
    	 *     >> No move by either player has been played directly on that
    	 *        square 
    	 *     >> The vertical player has not played a move that is directly
    	 *        below that square
    	 *     >> The horizontal player has not played a move that is directly
    	 *        left of that square
    	 *     >> The move isn't on the top row (for the vertical player)
    	 *     >> The move isn't on the right column (for the horizontal player)
    	 */
    	if(nextPlayer.equals(VERTICAL)) {
    	
    	}
    	
    	return null;
    }
}
