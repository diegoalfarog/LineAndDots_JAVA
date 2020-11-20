import java.util.Scanner;

/**
 * IO class that request information
 * 
 * @author Diego Alfaro
 * @author Nicole Garcia
 * @author Gabriel Guzman
 * @version 5 November 2020
 */
public class IO {

    //Define atributes
    private static Scanner input = new Scanner(System.in);;

    /**
     * Request a byte value
     *@return number
     */
    public static byte requestByte(String message) {
        boolean isInvalidValue = false;
        byte valueInByte = ' ';

        do {
            showMessage(message);
            try {
                valueInByte = Byte.parseByte(input.next());
                isInvalidValue = false;
            } catch (Exception e) {
                showMessage("Valor invalido");
                isInvalidValue = true;
            }
        } while (isInvalidValue);
        return valueInByte;
    }

    /**
     * Request a String value
     *@return text
     */
    public static String requestString(String message) {

        showMessage(message);

        String text = input.next();

        return text;
    }

    /**
    * Request a char value 
    *@return text
    */
    public static char requestChar(String message) {
        char text;
        showMessage(message);
        text = input.next().charAt(0);
        return text;
    }

    /**
     * Request a char value
     *@return text
     */
    public static char requestCharInLowerCase(String message) {
        char text;
        showMessage(message);

        text = input.next().toLowerCase().charAt(0);

        return text;
    }

    /**
     * This method request a byte that identified  a limit in the board
     * @param message
     * @param max
     * @param min
     * @return value
     */
    public static byte requestByteWithLimit(String message, int min, int max) {
        byte value = 0;
        // Request the rows size, value > min  and  value < max
        value = IO.requestByte(message);
        if (value >= min && value <= max) {
            return value;
        } else {
            IO.errorMessage(1);
            return requestByteWithLimit(message, min, max);
        }
    }

    /**
     *Show a message 
     *@param message
     */
    public static void showMessage(String message) {

        System.out.println(message);
    }



    /**
     * Print fifty spaces in the board
     */
    public static void printFiftySpaces() {
        for (int i = 0; i < 50; ++i)
            showMessage("");
    }

    /**
      * This method is used to wait before show a message  
      * @param miliSeconds 
      */
    public static void wait(int miliSeconds) {
        try {
            Thread.sleep(miliSeconds);
        } catch (Exception e) {
        }
    }

    /**
     * Show a movement of a player before play
     * @param board
     * @param currentPlayer
     */
    public static void showBoardAfterTurn(Board board, Player currentPlayer) {
        IO.showMessage("Esta fue la jugada de " + currentPlayer.getName() + board.printBoard());
    }

    /**
     * This method show the turn of a player
     * @param board
     * @param currentPlayer
     */
    public static void showBoardBeforeTurn(Board board, Player currentPlayer) {
        IO.showMessage("Turno de " + currentPlayer.getName() + board.printBoard());

    }

    /**
     * This mehtod show a message to the incorrect values is entered
     * @param kindMessage 1 invalidValue, 2 Invalid Square, 3 Invalid Border
     */
    public static void errorMessage(int kindMessage) {
        switch (kindMessage) {
            case 1:
                IO.showMessage("El valor ingresado es invalido.");
                break;
            case 2:
                IO.showMessage("Este cuadrado ya tiene todos los border marcados");
                break;
            case 3:
                IO.showMessage("Este borde ya ha sido marcado");
                break;
            default:
                break;
        }
    }

}