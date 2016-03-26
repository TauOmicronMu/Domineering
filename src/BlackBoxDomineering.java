import java.util.Scanner;

/**
 * Created by tom on 25/03/16.
 */
public class BlackBoxDomineering {

    /**
     - It takes EXACTLY four command line arguments (and you should
     have an assertion for the number of command line arguments).

     - The first argument is either "first" or "second", indicating
     whether your black box plays first or second.

     - The second argument is either "vertical" or "horizontal",
     indicating whether your black box is the player H or the player V.

     (After discussion with the students on 11-12 of March, we are
     keeping this specification, but we promise to use your black box
     with the combinations first-H and second-V only, and hence you
     don't need to implement the combinations first-V and second-H.)

     - The third argument is the width of the board.

     - The fourth argument is the height of the board.

     - The main method then starts to play as the appropriate player as follows.

     - It should print what we tell you below, and nothing else, because we
     are going to parse the output and give you marks based on that.

     - Anything you want to print for your own benefit, such as debugging,
     should be printed to "System.err" rather than "System.out".

     - Your program will read opponent moves from System.in, and write its
     moves to System.out. It is important here that the reading and
     writing is WITHOUT BUFFERING. After writing each move, you should
     flush() the output.

     - Each move in the input or the output is terminated by a new line.

     - If your opponent plays an invalid move, you should exit with
     System.exit(1). You may print a message in System.err, but
     definitely not System.out.

     - A move is a pair of numbers separated by comma, such as "3,5", in
     ZERO-BASED coordinates.

     - A vertical move x,y covers the positions x,y and x,(y+1).

     - A horizontal move x,y covers the positions x,y and (x+1),y.

     - We emphasize again your program should NOT PRINT ANYTHING ELSE to
     standard output. Prompts or boards or debugging output or error
     messages MUST go to System.err.

     - When the game has finished because there are no available moves for
     the next player, your program should end silently without error or
     output other than System.err.

     */

    private static class CommandLineDom implements MoveChannel<DomineeringMove> {

        public DomineeringMove getMove() {
            //Do nothing - we don't want input from a human player.
            //#WatDoIDoHere :'(
            return null;
        }

        public void giveMove(DomineeringMove move) {
            System.out.println("I play " + move);
        }

        public void comment(String msg) {
            System.out.println(msg);
        }

        public void end(int value) {
            System.out.println("Game over. The result is " + value);
        }
    }

    public static void main(String[] args) {

        assert(args.length == 4);

        String firstOrSecond = args[0];
        String vertOrHoriz = args[1];
        int m = Integer.parseInt(args[2]);
        int n = Integer.parseInt(args[3]);

        DomineeringBoard board = new DomineeringBoard(m,n);

        switch(firstOrSecond) {
            case "first" :
                board.tree().firstPlayer(new CommandLineDom());
                break;
            case "second" :
                board.tree().secondPlayer(new CommandLineDom());
                break;
            default :
                System.err.println("1st arg was wrong!");
                System.exit(1);
        }
    }
}
