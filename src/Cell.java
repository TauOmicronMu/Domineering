
public class Cell {
    public int column;
	public int row;
    
	public Cell(int m, int n) {
		this.column = m;
		this.row = n;
	}
	
	public String toString() {
		return "(" + column + "," + row + ")";
	}
}
