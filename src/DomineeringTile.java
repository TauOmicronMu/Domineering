
public class DomineeringTile {
    public boolean taken = false;
    
    public void flip() { this.taken = true; }
    
    public String toString() {
    	return (this.taken) ? "X" : " ";
    }
}
