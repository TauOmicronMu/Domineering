
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
	
	@Override
	public int hashCode() {
		return (column << 20) + row;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		else if (obj instanceof Cell) {
			Cell otherCell = (Cell) obj;
			return (otherCell.column == this.column) && (otherCell.row == this.row);
		} else return false;
	}
}
