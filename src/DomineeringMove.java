public class DomineeringMove implements Comparable {
    public Cell posA;
    public Cell posB;
    
    public DomineeringMove(Cell posA, Cell posB) {
    	this.posA = posA;
    	this.posB = posB;
    }
    
    public String toString() {
    	return "(" + posA + "," + posB + ")";
    }

	@Override
	public int compareTo(Object o) {
		DomineeringMove moveO;
		try {
	        moveO = (DomineeringMove) o;
		}
		catch (Exception e) {
			return Integer.MAX_VALUE;
		}
		if(moveO.posA.equals(this.posA) && moveO.posB.equals(posB)) {
			return 0;
		}
		else {
		    return -1;
		}
	}
}
