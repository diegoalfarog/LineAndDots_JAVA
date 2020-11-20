/** 
 * Game class that starts the game
 * 
 * @author Diego Alfaro
 * @author Nicole Garcia
 * @author Gabriel Guzman
 * @version 5 November 2020
 */

public class Game {

    // Define attributes
    private int noMarkedSquares;
    private PlayerList playerList;
    private Board board;
    private GameHelper gameHelper;
    private Player currentPlayerOne;
    private Player currentPlayerTwo;
    private ReaderManager reader;
    private WriterManager writer;

    /**
     * Constructor with parameters
     */
    public Game() {
        playerList = new PlayerList();
        gameHelper = new GameHelper();
        reader = new ReaderManager();
        writer = new WriterManager();
    }

    /**
     * Have all functions and objects for develop the game
     * 
     */
    public void startGame() {
        try {
            reader.open("PlayerList.txt");
            playerList.setPlayerList(reader.read());
            reader.close();
        } catch (Exception e) {

        }
        gameHelper.initialMessage();
        char mainMenu;
        // Welcome: the program begins with a window that show a welcome
        // of the game, the authors names and Input options.

        do {
            mainMenu = gameHelper.showMenu("\nInicio:\n a) Jugar\n b) Ver Puntaje de los jugadores\n c) Salir\n", 'a',
                    'b', 'c');
            switch (mainMenu) {
                case 'a':
                    byte rowsSize = IO
                            .requestByteWithLimit("\nValores: min 2, max 10 \nIngrese el tamano de las filas: ", 2, 10);
                    IO.printFiftySpaces();
                    byte colsSize = IO.requestByteWithLimit(
                            "\nValores: min 2, max 10 \nIngrese el tamano de las columnas: ", 2, 10);
                    IO.printFiftySpaces();

                    // Operation for created the board
                    noMarkedSquares = rowsSize * colsSize;
                    board = new Board(rowsSize, colsSize);

                    // Request for menu the game mode
                    char mode = gameHelper.showMenu(
                            "Elija el modo del videojuego:\n a) Dos jugadores\n b) contra Computadora\n c) Salir al menu principal",
                            'a', 'b', 'c');
                    IO.printFiftySpaces();

                    // Switch for menu the game mode
                    switch (mode) {

                        case 'a':
                            //Select the players
                            IO.showMessage("Seleccione la informacion del primer jugador");
                            currentPlayerOne = gameHelper.selectNewPlayerOrExisting(playerList);
                            IO.printFiftySpaces();
                            IO.showMessage("Seleccione la informacion del segundo jugador");
                            currentPlayerTwo = gameHelper.selectNewPlayerOrExisting(playerList);
                            IO.printFiftySpaces();
                            play(mode);
                            gameHelper.endGameMessage(board, currentPlayerOne, currentPlayerTwo);
                            playerList.insertion(currentPlayerOne);
                            playerList.insertion(currentPlayerTwo);
                            break;

                        case 'b':
                            currentPlayerOne = gameHelper.selectNewPlayerOrExisting(playerList);
                            currentPlayerTwo = new Player("0", "la consola", 'C');

                            // Choose the difficulty of game1  
                            char modeDifficulty = ' ';
                            modeDifficulty = gameHelper.showMenu(
                                    "\nElija la modalidad del juego: \na) Facil, b) Medio, c) Dificil \n", 'a', 'b',
                                    'c');

                            IO.printFiftySpaces();

                            play(mode, modeDifficulty);
                            IO.wait(3500);
                            gameHelper.endGameMessage(board, currentPlayerOne, currentPlayerTwo);
                            playerList.insertion(currentPlayerOne);

                            break;
                        case 'c':
                            break;
                    }
                    break;

                //Show player score if the list is not empty 
                case 'b':
                    if (!playerList.isEmpty()) {
                        IO.showMessage("\nPuntaje de los jugadores:\n" + playerList.printPlayerList() + "\n");
                        IO.wait(3500);
                    } else {
                        IO.showMessage("\nNo hay Jugadores registrados\n");
                        IO.wait(2500);
                    }
                    break;

                //end the game
                case 'c':

                    try {
                        writer.open("PlayerList.txt");
                        writer.write(playerList);
                        writer.close();
                    } catch (Exception e) {

                    }
                    IO.showMessage("Gracias por jugar!");
                    break;
            }
        } while (mainMenu != 'c');
    }

    private void play(char mode) {
        play(mode, ' ');
    }

    /**
     * Method that develop the mode and difficult
     * 
     * @param mode
     * @param difficulty
     */
    private void play(char mode, char difficulty) {

        // Define in a aleatory way the order of players
        int i = (int) (Math.random() * 2) + 1;

        // While squaresNoMarked
        while (noMarkedSquares > 0) {
            if (i % 2 != 0) {
                // turn player 1 
                SquareUbication temp = gameHelper.requestSquareUbication(board, currentPlayerOne);
                byte whichBorder = gameHelper.requestWhichBorder(board, temp.getRow(), temp.getCol());

                // Complete squares
                switch (board.markBorder(temp.getRow(), temp.getCol(), whichBorder,
                        currentPlayerOne.getGameIdentifier())) {
                    case 1:
                        i++;
                        break;
                    case 2:
                        noMarkedSquares -= 1;
                        break;
                    case 3:
                        noMarkedSquares -= 2;
                        break;
                }
                IO.printFiftySpaces();
                IO.showBoardAfterTurn(board, currentPlayerOne);
                IO.wait(3500);
                IO.printFiftySpaces();

            } else if (i % 2 == 0) {
                byte rowOfSquare = -1;
                byte colOfSquare = -1;
                byte whichBorder = -1;

                // Develop player vs player
                if (mode == 'a') {
                    //turn player 2
                    IO.showBoardBeforeTurn(board, currentPlayerTwo);
                    SquareUbication temp = gameHelper.requestSquareUbication(board, currentPlayerOne);
                    rowOfSquare = temp.getRow();
                    colOfSquare = temp.getCol();
                    whichBorder = gameHelper.requestWhichBorder(board, rowOfSquare, colOfSquare);

                    IO.printFiftySpaces();

                    // Develop player vs machine
                } else if (mode == 'b') {

                    // Develop easy mode
                    if (difficulty == 'a') {

                        SquareUbication temp = gameHelper.getSquareUbicationToEasyMode(board);
                        rowOfSquare = temp.getRow();
                        colOfSquare = temp.getCol();
                        whichBorder = gameHelper.getRandomBorderToEasyMode(board, rowOfSquare, colOfSquare);
                    }

                    // Develop medium mode
                    if (difficulty == 'b') {
                        SquareUbication temp = gameHelper.getSquareUbicationToMediumMode(board, noMarkedSquares);
                        rowOfSquare = temp.getRow();
                        colOfSquare = temp.getCol();
                        int cont = 0;
                        whichBorder = gameHelper.getRandomBorderToMediumMode(board, rowOfSquare, colOfSquare, cont);
                    }

                    //hard mode
                    if (difficulty == 'c') {
                        boolean found = false;
                        // valid if a square have 3 bordes marked for mark and win the square
                        for (byte row = 0; row < board.getRowsSize(); row++) {
                            for (byte col = 0; col < board.getColsSize(); col++) {
                                if (board.howManyBordersHaveMarkedThisSquare(row, col) == 3) {
                                    for (int j = 1; j <= 4; j++) {
                                        if (board.isMarkableBorder(row, col, (byte) j)) {
                                            rowOfSquare = row;
                                            colOfSquare = col;
                                            whichBorder = (byte) j;
                                            found = true;
                                            break;
                                        }
                                    }
                                }

                                if (found) {
                                    break;
                                }
                            }
                            if (found) {
                                break;
                            }
                        }

                        //if the square doesn´t have 3 borders marked , the difficult is less and change to medium
                        if (!found) {
                            SquareUbication temp = gameHelper.getSquareUbicationToMediumMode(board, noMarkedSquares);
                            rowOfSquare = temp.getRow();
                            colOfSquare = temp.getCol();
                            int cont = 0;
                            whichBorder = gameHelper.getRandomBorderToMediumMode(board, rowOfSquare, colOfSquare, cont);
                        }
                    }
                }

                // Complete squares
                switch (board.markBorder(rowOfSquare, colOfSquare, whichBorder, currentPlayerTwo.getGameIdentifier())) {
                    case 1:
                        i++;
                        break;
                    case 2:
                        noMarkedSquares -= 1;
                        break;
                    case 3:
                        noMarkedSquares -= 2;
                        break;
                }

                IO.printFiftySpaces();
                IO.showBoardAfterTurn(board, currentPlayerTwo);
                IO.wait(3500);
                IO.printFiftySpaces();
            }
        }
    }
}
