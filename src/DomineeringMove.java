/**
 * Created by tom on 25/03/16.
 */
public class DomineeringMove {

    public final int x;
    public final int y;

    public final Player player;

    public DomineeringMove(int x, int y, Player player) {
        this.x = x;
        this.y = y;

        this.player = player;
    }

    @Override
    public int hashCode() {
        return (y << 20) + x;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        else if (obj instanceof DomineeringMove) {
            DomineeringMove otherMove = (DomineeringMove) obj;
            return (otherMove.x == this.x) && (otherMove.y == this.y);
        }
        else return false;
    }

    public String toString() {
        return "(" + y + "," + x + ")" + " : " + player.name();
    }
}
