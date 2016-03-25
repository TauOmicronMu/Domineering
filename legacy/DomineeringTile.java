/*
 * OLD CLASS.
 */
public class DomineeringTile {
    private static final Player VERT = Player.MAXIMIZER;
    
	public boolean taken = false;
    public Player owner = null;
    
    public void flip() { this.taken = !this.taken; }
    
    public String toString() {

    	return (this.taken) ?
    		       (this.owner.equals(VERT) ? "X" : "O") 
    				    : " ";
    }
}
