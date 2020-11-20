
/**
 * TestTimbiriche class proof the game
 * 
 * @author Diego Alfaro
 * @author Nicole Garcia
 * @author Gabriel Guzman
 * @version 5 November 2020
 */
public class TestTimbiriche {
    /**
     * Main controls the program
     * 
     * @param args
     */
    public static void main(String[] args) {
        IO.printFiftySpaces();
        //Create instance
        Game gameObject = new Game();
        gameObject.startGame();
    }
}
