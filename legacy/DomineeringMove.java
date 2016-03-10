import java.util.ArrayList;
import java.util.EnumSet;

/*
 *   A0 | A1 | A2 | A3 | A4 | A5 | A6 | A7 
 *  ----+----+----+----+----+----+----+----
 *   B0 | B1 | B2 | B3 | B4 | B5 | B6 | B7
 *  ----+----+----+----+----+----+----+----
 *   C0 | C1 | C2 | C3 | C4 | C5 | C6 | C7
 *  ----+----+----+----+----+----+----+----
 *   D0 | D1 | D2 | D3 | D4 | D5 | D6 | D7 
 *  ----+----+----+----+----+----+----+----
 *   E0 | E1 | E2 | E3 | E4 | E5 | E6 | E7
 *  ----+----+----+----+----+----+----+----
 *   F0 | F1 | F2 | F3 | F4 | F5 | F6 | F7
 *  ----+----+----+----+----+----+----+----
 *   G0 | G1 | G2 | G3 | G4 | G5 | G6 | G7
 *  ----+----+----+----+----+----+----+----
 *   H0 | H1 | H2 | H3 | H4 | H5 | H6 | H7
 *   
 *   No boards can exist that are larger than 8x8.
 *   Boards of any other dimension work as follows:
 *   >> m is the width of the board
 *   >> n is the height of the board
 *   >> the board will consist of the first m columns
 *      and the first n rows, starting from A0 (top-left
 *      corner).
 *      
 *  A move from the horizontal player will occupy that
 *  space on the board, and also the space to the right
 *  of it. 
 *  
 *  A move from the vertical player will occupy that
 *  space on the board and also the space above it.
 *  
 *  This makes it easier to define moves, rather than 
 *  having to define them in terms of two spaces.
 */

public enum DomineeringMove {
    A0, A1, A2, A3, A4, A5, A6, A7, 
    B0, B1, B2, B3, B4, B5, B6, B7,
    C0, C1, C2, C3, C4, C5, C6, C7,
    D0, D1, D2, D3, D4, D5, D6, D7,
    E0, E1, E2, E3, E4, E5, E6, E7,
    F0, F1, F2, F3, F4, F5, F6, F7,
    G0, G1, G2, G3, G4, G5, G6, G7,
    H0, H1, H2, H3, H4, H5, H6, H7;
	
	static private final ArrayList<DomineeringMove> columnStarts = new ArrayList<DomineeringMove>() {{
		add(A0); add(A1); add(A2); add(A3); add(A4); add(A5); add(A6); add(A7);
	}};
	
	static private final ArrayList<DomineeringMove> rowStarts = new ArrayList<DomineeringMove>() {{
		add(A0); add(B0); add(C0); add(D0); add(E0); add(F0); add(G0); add(H0); 
	}};
	
	
	
	/*
	 *                                  Define the rows.
	 */
	static private final ArrayList<DomineeringMove> rowA = new ArrayList<DomineeringMove>() {{
		add(A0); add(A1); add(A2); add(A3); add(A4); add(A5); add(A6); add(A7);
	}};
	
	static private final ArrayList<DomineeringMove> rowB = new ArrayList<DomineeringMove>() {{
		add(B0); add(B1); add(B2); add(B3); add(B4); add(B5); add(B6); add(B7);
	}};

	static private final ArrayList<DomineeringMove> rowC = new ArrayList<DomineeringMove>() {{
		add(C0); add(C1); add(C2); add(C3); add(C4); add(C5); add(C6); add(C7);
	}};
	
	static private final ArrayList<DomineeringMove> rowD = new ArrayList<DomineeringMove>() {{
		add(D0); add(D1); add(D2); add(D3); add(D4); add(D5); add(D6); add(D7);
	}};

	static private final ArrayList<DomineeringMove> rowE = new ArrayList<DomineeringMove>() {{
		add(E0); add(E1); add(E2); add(E3); add(E4); add(E5); add(E6); add(E7);
	}};
	
	static private final ArrayList<DomineeringMove> rowF = new ArrayList<DomineeringMove>() {{
		add(F0); add(F1); add(F2); add(F3); add(F4); add(F5); add(F6); add(F7);
	}};
	
	static private final ArrayList<DomineeringMove> rowG = new ArrayList<DomineeringMove>() {{
		add(G0); add(G1); add(G2); add(G3); add(G4); add(G5); add(G6); add(G7);
	}};
	
	static private final ArrayList<DomineeringMove> rowH = new ArrayList<DomineeringMove>() {{
		add(H0); add(H1); add(H2); add(H3); add(H4); add(H5); add(H6); add(H7);
	}};
		
	static private final ArrayList<ArrayList<DomineeringMove>> rows = new ArrayList<ArrayList<DomineeringMove>>() {{
		add(rowA); add(rowB); add(rowC); add(rowD); add(rowE); add(rowF); add(rowG); add(rowH);
	}};
	
	/*
	 *                                  Define the columns.
	 */
	static private final ArrayList<DomineeringMove> col0 = new ArrayList<DomineeringMove>() {{
		add(A0); add(B0); add(C0); add(D0); add(E0); add(F0); add(G0); add(H0);
	}};
	
	static private final ArrayList<DomineeringMove> col1 = new ArrayList<DomineeringMove>() {{
		add(A1); add(B1); add(C1); add(D1); add(E1); add(F1); add(G1); add(H1);
	}};
	
	static private final ArrayList<DomineeringMove> col2 = new ArrayList<DomineeringMove>() {{
		add(A2); add(B2); add(C2); add(D2); add(E2); add(F2); add(G2); add(H2);
	}};
	
	static private final ArrayList<DomineeringMove> col3 = new ArrayList<DomineeringMove>() {{
		add(A3); add(B3); add(C3); add(D3); add(E3); add(F3); add(G3); add(H3);
	}};
	
	static private final ArrayList<DomineeringMove> col4 = new ArrayList<DomineeringMove>() {{
		add(A4); add(B4); add(C4); add(D4); add(E4); add(F4); add(G4); add(H4);
	}};
	
	static private final ArrayList<DomineeringMove> col5 = new ArrayList<DomineeringMove>() {{
		add(A5); add(B5); add(C5); add(D5); add(E5); add(F5); add(G5); add(H5);
	}};
	
	static private final ArrayList<DomineeringMove> col6 = new ArrayList<DomineeringMove>() {{
		add(A6); add(B6); add(C6); add(D6); add(E6); add(F6); add(G6); add(H6);
	}};
	
	static private final ArrayList<DomineeringMove> col7 = new ArrayList<DomineeringMove>() {{
		add(A7); add(B7); add(C7); add(D7); add(E7); add(F7); add(G7); add(H7);
	}};
	
	static private final ArrayList<ArrayList<DomineeringMove>> columns = new ArrayList<ArrayList<DomineeringMove>>() {{
		add(col0); add(col1); add(col2); add(col3); add(col4); add(col5); add(col6); add(col7);
	}};
	
	
	/**
	 * Returns the available moves for a board of dimensions (m x n)
	 * @param m The width of the board.
	 * @param n The height of the board.
	 * @return A list of moves valid for a board of dimensions (m x n)
	 */
    static public ArrayList<DomineeringMove> getMoves(int m, int n) {
    	/*
    	 * Create an empty EnumSet of DomineeringMoves.
    	 */
        ArrayList<DomineeringMove> moves = new ArrayList<DomineeringMove>();
    	/*
    	 * For the first m columns add the first n items to the moves set.
    	 */
    	for(int i = 0; i < m; i++) {for(int j = 0; j < n; j++) moves.add(columns.get(i).get(j));}
    	return moves;
    }
}
