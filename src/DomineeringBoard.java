import com.sun.media.jfxmedia.events.PlayerEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tom on 25/03/16.
 */
public class DomineeringBoard extends Board<DomineeringMove> {

    private final Player H = Player.MAXIMIZER;
    private final Player V = Player.MINIMIZER;

    private final Player[][] board;
    private final int movesPlayed;

    private final int defaultWidth = 4;
    private final int defaultHeight = 5;
    
    /**
     * Creates an array of dimensions: n x m, and
     * populates each element with null.
     *
     * @param m The width of the desired board.
     * @param n The height of the desired board.
     * @return A populated array of dimensions n x m.
     */
    static Player[][] createBoard(int m, int n) {
        Player[][] board = new Player[m][n];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                board[i][j] = null;
            }
        }
        return board;
    }

    /**
     * Returns a new instance of DomineeringBoard with dimensions
     * defaultWidth x defaultHeight.
     *
     * The board is initially populated with null.
     * The movesPlayed is set to 0.
     */
    public DomineeringBoard () {
        this.board = createBoard(this.defaultHeight, this.defaultWidth);
        this.movesPlayed = 0;
    }

    /**
     * Returns a new instance of DomineeringBoard with dimensions
     * n x m.
     *
     * The board is initially populated with null.
     * The movesPlayed is set to 0.
     *
     * @param m The width of the desired board.
     * @param n The height of the desired board.
     */
     public DomineeringBoard(int m, int n) {
         this.board = createBoard(m, n);
         this.movesPlayed = 0;
     }

    /**
     * Returns a new instance of DomineeringBoard with given board,
     * board and given number of moves, movesPlayed.
     *
     * @param board The Player[][] to use.
     * @param movesPlayed The total number of moves played so far.
     */
    public DomineeringBoard(Player[][] board, int movesPlayed) {
        this.board = board;
        this.movesPlayed = movesPlayed;
    }


    /**
     * Returns the player that is to play next - that is, the player
     * that should make the next move, so, the player that is playing
     * now.
     *
     * @return The player that should make the next move.
     *
     * The vertical player plays first, so has the 0th move.
     * Every two moves, it is the vertical player's turn, so
     * if n % 2 = 0 (where n is the total number of moves played),
     * it is the vertical player's turn, otherwise it is the
     * turn of the horizontal player.
     */
    public Player nextPlayer() {
        return (this.movesPlayed % 2 == 0) ? V : H;
    }

    /**
     * Returns a HashSet of DomineeringMoves equivalent to the
     * legal moves that the current player can play.
     *
     * @return A set containing all legal moves that the current player can play.
     *
     * Maximiser (H) :
     * ---------------
     * For the horizontal player, start on the 2nd (i = 1) row, and
     * check all pairs of cells: (i, j), (i-1, j) to see if
     * they are both empty. If they are, add them to the set.
     *
     * Minimiser (V) :
     * ---------------
     * For the vertical player, start on the (n-1)th column, and
     * check all pairs of cells: (i, j), (i, j+1) to see if
     * they are both empty. If they are, add them to the set.
     */
    public Set<DomineeringMove> availableMoves() {
        Player player = nextPlayer();
        ArrayList<DomineeringMove> moves = new ArrayList<>();
        switch(player) {
        
            case MAXIMIZER:
                for(int i = 1; i < this.board.length; i++) {
                    for (int j = 0; j < this.board[i].length; j++) {
                        if(this.board[i][j] == null && this.board[i-1][j] == null) {
                            moves.add(new DomineeringMove(i, j, player));
                        }
                    }
                }
                break;
            case MINIMIZER:
                for(int i = 0; i < this.board.length; i++) {
                    for(int j = (this.board[i].length - 2); j >= 0; j--) {
                        if(this.board[i][j] == null && this.board[i][j+1] == null) {
                            moves.add(new DomineeringMove(i, j, player));
                        }
                    }
                }
                break;
            }
        return new HashSet<>(moves);
        }


    /**
     * Calculates and returns the value of the current board, relative to the
     * current player:
     * If there are moves left, return 0.
     * If V has won, return 1.
     * If H has won, return -1.
     *
     * @return The value of the current board, with respect to the current player.
     */
    int value() {
        Player player = this.nextPlayer();
        boolean movesLeft = (this.availableMoves().size() == 0) ? false : true;
        if(movesLeft) return 0;
        return (player.equals(V) ? 1 : -1);
    }

    /**
     * Returns a copy of the current board, but with the provided move
     * played.
     *
     * @param move The DomineeringMove to play.
     * @return A new board that is the result of playing move on the current board.
     */
    public Board<DomineeringMove> play(DomineeringMove move) {
        Player player = nextPlayer();

        Player[][] boardCopy = new Player[this.board.length][];

        for(int i = 0; i < this.board.length; i++) {
            boardCopy[i] = this.board[i].clone();
        }

        switch(player) {
            case MAXIMIZER:
                boardCopy[move.x][move.y] = V;
                boardCopy[move.x-1][move.y] = V;
                break;
            case MINIMIZER:
                boardCopy[move.x][move.y] = H;
                boardCopy[move.x][move.y+1] = H;
                break;
        }

        int movesCopy = this.movesPlayed;
        movesCopy++;

        return new DomineeringBoard(boardCopy, movesCopy);
    }

    /**
     * Returns a 2D visual representation of a board.
     * @return A String containing a 2D visual representation of the board.
     */
    public String toString() {
        String s = "";
        s += "\n";
        s += "-";

        for(int j = 0; j < this.board[0].length; j++) s += "----";
        s += "\n";
        for(int i = 0; i < this.board.length; i++) {
            s += "|";
            for(int j = 0; j < this.board[i].length; j++) {
                s += " ";
                s += (this.board[i][j]) == null ? " " :
                        (this.board[i][j].equals(Player.MAXIMIZER) ? "X" : "O");
                s += " " + "|";
            }
            s += "\n";
            for(int j = 0; j < this.board[0].length; j++) s += "----";
            s += "-";
            s += "\n";
        }
        return s;
    }
}
