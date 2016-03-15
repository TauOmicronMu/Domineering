public class DomineeringMove {
    public Cell posA;
    public Cell posB;
    
    public DomineeringMove(Cell posA, Cell posB) {
    	this.posA = posA;
    	this.posB = posB;
    }
    
    public String toString() {
    	return "(" + posA + "," + posB + ")";
    }
}
