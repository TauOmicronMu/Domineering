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
    
    @Override
    public int hashCode() {
        return (posA.hashCode() << 10) + posB.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DomineeringMove) {
            DomineeringMove otherMove = (DomineeringMove) obj;
            return otherMove.posA.equals(this.posA) && otherMove.posB.equals(this.posB);
        } else return false;     
    }
}
