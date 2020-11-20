/**
 * GameHelper that modulaizes to help the game with  IO methods
 * 
 * @author Diego Alfaro
 * @author Nicole Garcia
 * @author Gabriel Guzman
 * @version 5 November 2020
 */

public class GameHelper {

    /**
     * Show the authors and instructions 
     */
    public void initialMessage() {
        IO.showMessage(
                "Bienvenido al juego Timbiriche \n\nAutores:\n  Diego Alfaro Gonzalez \n  Nicole Garcia Luna \n  Gabriel Guzman Alfaro");
        IO.showMessage(
                "\n\nPara una menor visibilidad extienda la venta de su consola \nal tamaño de las instruncciones de esta manera el resto de la ejecución del juego podra obsevar todo correctamente");
        // Game instructions
        IO.showMessage("\nInstrucciones del juego:\n  El primer jugador debe de seleccionar un borde"
                + " a marcar. Seguidamente el segundo jugador.\n  Los bordes ya marcados no sera seleccionables."
                + " El jugador que complete mas cuadrados gana la partida.\n  Posterior a definir el"
                + " tamano del tablero debera ubicarse con los indices de las filas y columnas que se muestran.\n\n"
                + "               Columnas      \n\n" + "                  0   1\n\n" + "                o   o   o\n"
                + "            0             \n" + "    Filas       o   o   o\n" + "            1            \n"
                + "                o   o   o\n");

    }

    /**
     * This mehtod show the menu
     * @param message 
     * @param options
     */
    public char showMenu(String message, char... options) {
        char selectedOption = ' ';

        do {
            // Request the game mode 
            selectedOption = IO.requestCharInLowerCase(message);

            for (char c : options) {
                if (selectedOption == c) {
                    return selectedOption;
                }
            }
            IO.errorMessage(1);

        } while (selectedOption == ' ');
        return selectedOption;
    }

    /**
     * This method compute all posibilities of end a game and show a message
     * @param board
     * @param currentPlayerOne
     * @param currentPlayerTwo
     */
    public void endGameMessage(Board board, Player currentPlayerOne, Player currentPlayerTwo) {
        IO.showMessage(board.printBoard());
        byte returnOfWhichPlayerWin = board.whichPlayerWin(currentPlayerOne.getGameIdentifier(),
                currentPlayerTwo.getGameIdentifier()); //identify the player and computer 

        if (returnOfWhichPlayerWin == 0) { //tie
            IO.showMessage("Empate");
            currentPlayerOne.addPoints(0.5);

            if (currentPlayerTwo.getGameIdentifier() != 'C') //add point if the second player isn't computer
                currentPlayerTwo.addPoints(0.5);

        } else if (returnOfWhichPlayerWin == 1) {//Win the player 1  
            IO.showMessage(currentPlayerOne.getName() + " Gana!\n");
            currentPlayerOne.addPoints(1);
        } else {
            if (currentPlayerTwo.getGameIdentifier() != 'C') { // Win the computer
                currentPlayerTwo.addPoints(0.5);
                IO.showMessage(currentPlayerTwo.getName() + " Gana!\n");
            } else {
                IO.showMessage(currentPlayerTwo.getName() + " Gana!\n");

            }
        }
    }

    /**
     * Select a new player or select a existing player
     * @return new player or existing player
     */
    public Player selectNewPlayerOrExisting(PlayerList playerList) {

        char optionSelectIfNewPlayerOrExistingPlayer;
        String idToSelectPlayer;

        do {
            // Select New player or player Existing 
            optionSelectIfNewPlayerOrExistingPlayer = showMenu(
                    "\nDesea: \na) Jugar con un jugador existente \nb) Crear un jugador nuevo", 'a', 'b');

            //if not have existing players
            if (optionSelectIfNewPlayerOrExistingPlayer == 'a' && playerList.isEmpty()) {
                optionSelectIfNewPlayerOrExistingPlayer = 'b';
                IO.showMessage("No hay usuarios existentes por favor cree uno nuevo");
            }

        } while (optionSelectIfNewPlayerOrExistingPlayer != 'a' && optionSelectIfNewPlayerOrExistingPlayer != 'b');

        switch (optionSelectIfNewPlayerOrExistingPlayer) {
            case 'a':
                // Select player existing
                do {
                    idToSelectPlayer = IO.requestString(
                            "\nIngrese el Id del jugador para elegirlo\n" + playerList.printPlayerList());

                    if (playerList.searchPlayer(idToSelectPlayer) == -1) {
                        IO.showMessage("La id no corresponde a ningun jugador.");
                    }
                } while (playerList.searchPlayer(idToSelectPlayer) == -1);

                return playerList.deletePlayer(idToSelectPlayer);

            case 'b':
                // Created new player
                // The user choose type the name
                String namePlayer2 = IO.requestString("Ingrese el nombre del jugador.");
                for (int i = 0; i < 50; ++i)
                    IO.showMessage("");

                String idPlayer;
                do { // Input the identification of the Player
                    idPlayer = IO.requestString(
                            "\nIngrese una Identificacion: Ejem C03175 \nMin 4 caracteres, Max 8 valores");

                    if (idPlayer.length() < 4 || idPlayer.length() > 8) {
                        IO.errorMessage(1);
                    }

                    // Test length and if do not exist
                } while ((idPlayer.length() < 4 || idPlayer.length() > 8) && playerList.searchPlayer(idPlayer) == -1);

                char gameId;
                char controlVariable = ' ';
                do { // Input the identification of the Player
                    gameId = IO.requestChar(
                            "\nIngrese un indentificador de Juego:\n Ejem '1' o 'U' \nEl identificador 'C' esta reservado");

                    if (gameId != 'C' && playerList.isValidGameIdentifier(gameId)) {
                        controlVariable = gameId;
                    } else {
                        IO.errorMessage(1);
                    }

                    // Test length and if do not exist
                } while (gameId != controlVariable);

                IO.showMessage("La información ha sido guardada");
                return new Player(idPlayer, namePlayer2, gameId);
        }
        return null;
    }

    /**
     * This method request board rows
     * @param board
     * @return rowOfSquare 
     */
    public byte requestSquaresRow(Board board) {
        // Players input the Square in row
        // And Proof that row don´t exceed the limit
        byte rowOfSquare = IO.requestByteWithLimit("Elija la fila del cuadrado: ", 0, board.getRowsSize() - 1);
        return rowOfSquare;
    }

    /**
     * This method request board cols
     * @param board
     * @return colOfSquare
     */
    public byte requestColOfSquare(Board board) {
        // Players input the Square in cols
        // And Proof that cols don´t exceed the limit   
        byte colOfSquare = IO.requestByteWithLimit("Elija la columna del cuadrado: ", 0, board.getColsSize() - 1);
        return colOfSquare;
    }

    /**
     * Request which border 
     * @return whichBorder
     */
    public byte requestWhichBorder(Board board, byte rowOfSquare, byte colOfSquare) {
        // Player 1 input the border
        // And Proof that row don´t exceed the limit
        byte whichBorder = IO.requestByteWithLimit(
                "Elija el borde que desea marcar: \n1) Arriba \n2) Abajo \n3) Izquierda \n4) Derecha ", 1, 4);
        if (board.isMarkableBorder(rowOfSquare, colOfSquare, whichBorder)) {
            return whichBorder;
        } else {
            IO.errorMessage(3);
            return requestWhichBorder(board, rowOfSquare, colOfSquare);
        }

    }

    /**
     * This method computes a random border to mark in medium difficulty
     * @param board
     * @param rowOfSquare
     * @param colOfSquare
     * @param cont
     * @return whichBorder
     */
    public byte getRandomBorderToMediumMode(Board board, byte rowOfSquare, byte colOfSquare, int cont) {

        byte whichBorder = (byte) ((Math.random() * 4) + 1);
        if (cont >= 15 && board.isMarkableBorder(rowOfSquare, colOfSquare, whichBorder)) {
            return whichBorder;
        }
        //valid if the markable border is different to rows , cols and if wich border is marked
        if (!board.isMarkableBorder(rowOfSquare, colOfSquare, whichBorder)) {
            cont++;
            return getRandomBorderToMediumMode(board, rowOfSquare, colOfSquare, cont);
        } else {
            switch (whichBorder) {
                //valid if the up square have to marked borders
                case 1:
                    if ((rowOfSquare - 1) >= 0) {
                        if (board.howManyBordersHaveMarkedThisSquare((byte) (rowOfSquare - 1), colOfSquare) != 2) {
                            return whichBorder;
                        } else {
                            cont++;
                            return getRandomBorderToMediumMode(board, rowOfSquare, colOfSquare, cont);
                        }
                    } else {
                        return whichBorder;
                    }

                    //valid if the down square have to marked borders    
                case 2:

                    if ((rowOfSquare + 1) < board.rowsSize) {
                        if (board.howManyBordersHaveMarkedThisSquare((byte) (rowOfSquare + 1), colOfSquare) != 2) {
                            return whichBorder;
                        } else {
                            cont++;
                            return getRandomBorderToMediumMode(board, rowOfSquare, colOfSquare, cont);
                        }
                    } else {
                        return whichBorder;
                    }
                    //valid if the left border have to marked borders    
                case 3:
                    if ((colOfSquare - 1) >= 0) {
                        if (board.howManyBordersHaveMarkedThisSquare(rowOfSquare, (byte) (colOfSquare - 1)) != 2) {
                            return whichBorder;
                        } else {
                            cont++;
                            return getRandomBorderToMediumMode(board, rowOfSquare, colOfSquare, cont);
                        }
                    } else {
                        return whichBorder;
                    }

                    //valid if the right border have to marked borders     
                case 4:
                    if ((colOfSquare + 1) < board.colsSize) {
                        if (board.howManyBordersHaveMarkedThisSquare(rowOfSquare, (byte) (colOfSquare + 1)) != 2) {
                            return whichBorder;
                        } else {
                            cont++;
                            return getRandomBorderToMediumMode(board, rowOfSquare, colOfSquare, cont);
                        }
                    } else {
                        return whichBorder;
                    }

            }
            return -1;
        }

    }

    public SquareUbication getSquareUbicationToMediumMode(Board board, int noMarkedSquares) {
        byte rowOfSquare;
        byte colOfSquare;
        int cont = 0;
        // random rows and cols values 
        if (noMarkedSquares == 1) {
            SquareUbication temp = getSquareUbicationToEasyMode(board);
            return new SquareUbication(temp.getRow(), temp.getCol());
        } else {

            byte controlVariable = 2;

            //valid if the square to mark have 2 marked borders 
            do {
                controlVariable = 2;
                rowOfSquare = (byte) ((Math.random() * (board.rowsSize)));
                colOfSquare = (byte) ((Math.random() * (board.colsSize)));
                cont += 1;

                //valid if the four borders of a square are not marked
                if (cont >= 10 && board.howManyBordersHaveMarkedThisSquare(rowOfSquare, colOfSquare) != 4) {
                    break;
                }

                //valid if the four borders of a square are  marked
                if (board.howManyBordersHaveMarkedThisSquare(rowOfSquare, colOfSquare) == 4) {
                    controlVariable = 4;
                }
            } while (board.howManyBordersHaveMarkedThisSquare(rowOfSquare, colOfSquare) == controlVariable);
            return new SquareUbication(rowOfSquare, colOfSquare);
        }

    }

    public byte getRandomBorderToEasyMode(Board board, byte rowOfSquare, byte colOfSquare) {
        byte whichBorder;
        do {
            whichBorder = (byte) ((Math.random() * 4) + 1);
            //repets while rows and cols are unable to mark    
        } while (!board.isMarkableBorder(rowOfSquare, colOfSquare, whichBorder));
        return whichBorder;
    }

    public SquareUbication getSquareUbicationToEasyMode(Board board) {
        byte rowOfSquare;
        byte colOfSquare;   
        do {
            rowOfSquare = (byte) ((Math.random() * (board.rowsSize)));
            colOfSquare = (byte) ((Math.random() * (board.colsSize)));

            //if the 4 borders of square is marked , random to mark other square border
        } while (board.howManyBordersHaveMarkedThisSquare(rowOfSquare, colOfSquare) == 4);
        return new SquareUbication(rowOfSquare, colOfSquare);
    }
    public  SquareUbication requestSquareUbication(Board board, Player currentPlayerOne) {
        byte rowOfSquare;
        byte colOfSquare;  
        boolean isInvalidSquare;
        do {
            rowOfSquare = requestSquaresRow(board);
    
            colOfSquare = requestColOfSquare(board);
            isInvalidSquare = board.howManyBordersHaveMarkedThisSquare(rowOfSquare, colOfSquare) == 4;
            if (isInvalidSquare) {
                IO.showBoardBeforeTurn(board, currentPlayerOne);
                IO.errorMessage(2);
            }
        } while (isInvalidSquare);
        return new SquareUbication(rowOfSquare,colOfSquare);
    }
}