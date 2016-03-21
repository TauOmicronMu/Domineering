
public class DomineeringTile {
    public boolean taken = false;
    
    public void flip() { this.taken = !this.taken; }
    
    public String toString() {
    	return (this.taken) ? "X" : " ";
    }
}
