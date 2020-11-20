
/**
 * Board class that saves all data about the board game
 * 
 * @author Diego Alfaro
 * @author Nicole Garcia
 * @author Gabriel Guzman
 * @version 5 November 2020
 */

public class Board {

    // Define attributes
    public int colsSize;
    public int rowsSize;
    private Shape board[][];

    /**
     * Constructor with parameters
     * 
     * @param colsSize.
     * @param rowsSize. 
     */
    public Board(int rowsSize, int colsSize) {
        this.rowsSize = rowsSize;
        this.colsSize = colsSize;
        board = new Shape[this.rowsSize * 2 + 1][this.colsSize * 2 + 1];
        this.generateBoard();
    }

    /**
     * @return the rowsSize
     */
    public int getRowsSize() {
        return rowsSize;
    }

    /**
     * @return the colsSize
     */
    public int getColsSize() {
        return colsSize;
    }

    /**
     * This method fills the board according with its dimensions
     */
    public void generateBoard() {
        for (int col = 0; col < board.length; col++) {
            for (int row = 0; row < board[col].length; row++) {
                if (col % 2 == 0 && row % 2 != 0 || col % 2 != 0 && row % 2 == 0) {
                    board[col][row] = new Border(false);
                    continue;
                }
                if (col % 2 != 0 && row % 2 != 0) {
                    board[col][row] = new Square(false, '0');
                }
            }
        }
    }

    /**
     * Find the position to mark, and test if the current border marked complete a
     * square.
     * 
     * @param rowOfSquare
     * @param colOfSquare
     * @param whichBorder
     * @param who 
     * @return completesSquare or -1
     */
    public int markBorder(byte rowOfSquare, byte colOfSquare, byte whichBorder, char who) {
        int completesSquare = 1;
        switch (whichBorder) {
            case 1:
                board[rowOfSquare * 2][colOfSquare * 2 + 1].setIsMarked(true);// Marked up
                if (thisBorderCompleteItsSquare(rowOfSquare, colOfSquare, whichBorder, who)) {
                    markSquare(rowOfSquare, colOfSquare, who);// mark Square
                    completesSquare += 1;
                }
                completesSquare += thisBorderCompleteItsTheSquareInItsDirection(rowOfSquare, colOfSquare, whichBorder,
                        who);
                return completesSquare;

            case 2:
                board[rowOfSquare * 2 + 2][colOfSquare * 2 + 1].setIsMarked(true);// Mark down
                if (thisBorderCompleteItsSquare(rowOfSquare, colOfSquare, whichBorder, who)) {
                    markSquare(rowOfSquare, colOfSquare, who);// mark Square
                    completesSquare += 1;
                }
                completesSquare += thisBorderCompleteItsTheSquareInItsDirection(rowOfSquare, colOfSquare, whichBorder,
                        who);
                return completesSquare;

            case 3:
                board[rowOfSquare * 2 + 1][colOfSquare * 2].setIsMarked(true);// Mark left
                if (thisBorderCompleteItsSquare(rowOfSquare, colOfSquare, whichBorder, who)) {
                    markSquare(rowOfSquare, colOfSquare, who);// mark Square
                    completesSquare += 1;
                }
                completesSquare += thisBorderCompleteItsTheSquareInItsDirection(rowOfSquare, colOfSquare, whichBorder,
                        who);
                return completesSquare;

            case 4:
                board[rowOfSquare * 2 + 1][colOfSquare * 2 + 2].setIsMarked(true);// Marked right
                if (thisBorderCompleteItsSquare(rowOfSquare, colOfSquare, whichBorder, who)) {
                    markSquare(rowOfSquare, colOfSquare, who);// mark Square
                    completesSquare += 1;
                }
                completesSquare += thisBorderCompleteItsTheSquareInItsDirection(rowOfSquare, colOfSquare, whichBorder,
                        who);
                return completesSquare;

        }
        return -1;
    }

    /**
     * Test the sides of board and mark if complete a square
     * @param colOfSquare
     * @param rowOfSquare
     * @param whichBorder
     * @param who
     * @return true or false
     */
    private boolean thisBorderCompleteItsSquare(byte rowOfSquare, byte colOfSquare, byte whichBorder, char who) {
        switch (whichBorder) {
            case 1:
                if (board[rowOfSquare * 2 + 1][colOfSquare * 2].getIsMarked()// check left
                        && board[rowOfSquare * 2 + 1][colOfSquare * 2 + 2].getIsMarked()// check right
                        && board[rowOfSquare * 2 + 2][colOfSquare * 2 + 1].getIsMarked()) {// check down
                    return true;
                }

                break;
            case 2:
                if (board[rowOfSquare * 2 + 1][colOfSquare * 2].getIsMarked()// check left
                        && board[rowOfSquare * 2 + 1][colOfSquare * 2 + 2].getIsMarked()// check right
                        && board[rowOfSquare * 2][colOfSquare * 2 + 1].getIsMarked()) {// check up
                    return true;
                }

                break;
            case 3:
                if (board[rowOfSquare * 2 + 1][colOfSquare * 2 + 2].getIsMarked()// check right
                        && board[rowOfSquare * 2][colOfSquare * 2 + 1].getIsMarked()// check up
                        && board[rowOfSquare * 2 + 2][colOfSquare * 2 + 1].getIsMarked()) {// check down
                    return true;
                }

                break;
            case 4:
                if (board[rowOfSquare * 2 + 1][colOfSquare * 2].getIsMarked()// check left
                        && board[rowOfSquare * 2][colOfSquare * 2 + 1].getIsMarked()// check up
                        && board[rowOfSquare * 2 + 2][colOfSquare * 2 + 1].getIsMarked()) {// check down
                    return true;
                }

                break;
        }
        return false;
    }

    /**
     * Mark the correct square
     *@param rowOfSquare 
     *@param colOfSquare 
     *@param who 
     */
    private void markSquare(byte rowOfSquare, byte colOfSquare, char who) {
        board[rowOfSquare * 2 + 1][colOfSquare * 2 + 1].setIsMarked(true);// mark Square
        ((Square) board[rowOfSquare * 2 + 1][colOfSquare * 2 + 1]).setBy(who);
    }

    /**
     * Select the correct direction of square to mark
     * @param rowOfSquare
     * @param colOfSquare
     * @param whichBorder
     * @return completesSquare
     */
    private int thisBorderCompleteItsTheSquareInItsDirection(byte rowOfSquare, byte colOfSquare, byte whichBorder,
            char who) {
        int completesSquare = 0;

        switch (whichBorder) {
            case 1:
                if ((rowOfSquare - 1) >= 0
                        && thisBorderCompleteItsSquare((byte) (rowOfSquare - 1), colOfSquare, (byte) 2, who)) { // check right, up and left Border of up Square

                    markSquare((byte) (rowOfSquare - 1), colOfSquare, who);// mark Square
                    completesSquare += 1;
                }
                return completesSquare;

            case 2:
                if ((rowOfSquare + 1) < this.rowsSize
                        && thisBorderCompleteItsSquare((byte) (rowOfSquare + 1), colOfSquare, (byte) 1, who)) { // check right, down and left Border of down Square

                    markSquare((byte) (rowOfSquare + 1), colOfSquare, who);// mark Square
                    completesSquare += 1;
                }
                return completesSquare;

            case 3:
                if ((colOfSquare - 1) >= 0
                        && thisBorderCompleteItsSquare(rowOfSquare, (byte) (colOfSquare - 1), (byte) 4, who)) {// check left, up and down Border of up Square 

                    markSquare(rowOfSquare, (byte) (colOfSquare - 1), who);// mark Square
                    completesSquare += 1;
                }
                return completesSquare;

            case 4:
                if ((colOfSquare + 1) < this.colsSize
                        && thisBorderCompleteItsSquare(rowOfSquare, (byte) (colOfSquare + 1), (byte) 3, who)) {// check right, up and down Border of right Square

                    markSquare(rowOfSquare, (byte) (colOfSquare + 1), who);// mark Square
                    completesSquare += 1;
                }
                return completesSquare;
        }
        return completesSquare;
    }

    /**
     * Test if the spaces for print the values in the matrix
     * @return toPrintMatrix
     */
    public String printBoard() {
        String toPrintMatrix = "\nTablero: \n" + "      ";

        for (int i = 0; i < colsSize; i++) {
            toPrintMatrix += i + "   ";
        }
        toPrintMatrix += "\n\n";

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {

                if (board[row][col] != null) {
                    // horizontal border
                    if (row % 2 == 0 && col % 2 != 0) {
                        if (board[row][col].getIsMarked() == true) {
                            toPrintMatrix += " - ";
                        } else {
                            toPrintMatrix += "   ";
                        }
                        continue;
                    }
                    // vertical border
                    if (row % 2 != 0 && col % 2 == 0) {
                        if (col == 0 * 2)
                            toPrintMatrix += (row - 1) / 2;
                        if (board[row][col].getIsMarked() == true) {
                            if (col == 0 * 2) {
                                toPrintMatrix += "   |";
                            } else {
                                toPrintMatrix += "|";
                            }
                        } else {
                            if (col == 0 * 2) {
                                toPrintMatrix += "    ";
                            } else {
                                toPrintMatrix += " ";
                            }
                        }
                        continue;
                    }
                    // Squares
                    if (row % 2 != 0 && col % 2 != 0) {

                        if (board[row][col].getIsMarked()) {
                            toPrintMatrix += " " + ((Square) board[row][col]).getBy() + " ";
                        } else {
                            toPrintMatrix += "   ";
                        }
                    }
                } else {
                    if (col == 0) {
                        toPrintMatrix += "    o";
                    } else {
                        toPrintMatrix += "o";
                    }

                }
            }
            toPrintMatrix += "\n";
        }
        return toPrintMatrix;
    }

    /**
     * Count how many borders have marked the square
     * 
     * @param rowOfSquare
     * @param colOfSquare
     * @return countBordersMarked
     */
    public int howManyBordersHaveMarkedThisSquare(byte rowOfSquare, byte colOfSquare) {
        int countBordersMarked = 0;

        if (board[rowOfSquare * 2][colOfSquare * 2 + 1].getIsMarked())//  up
            countBordersMarked++;
        if (board[rowOfSquare * 2 + 2][colOfSquare * 2 + 1].getIsMarked())// down
            countBordersMarked++;
        if (board[rowOfSquare * 2 + 1][colOfSquare * 2].getIsMarked())// left
            countBordersMarked++;
        if (board[rowOfSquare * 2 + 1][colOfSquare * 2 + 2].getIsMarked())//right
            countBordersMarked++;

        return countBordersMarked;
    }

    /**
     * Test if the selected border could be marked
     * 
     * @param rowOfSquare
     * @param colOfSquare
     * @param whichBorder
     * @return true o false
     */
    public boolean isMarkableBorder(byte rowOfSquare, byte colOfSquare, byte whichBorder) {

        switch (whichBorder) {
            case 1:
                if (board[rowOfSquare * 2][colOfSquare * 2 + 1].getIsMarked()) {// up
                    return false; //False because the border is already marked
                }
                break;

            case 2:
                if (board[rowOfSquare * 2 + 2][colOfSquare * 2 + 1].getIsMarked()) { // down
                    return false;//False because the border is already marked
                }
                break;

            case 3:
                if (board[rowOfSquare * 2 + 1][colOfSquare * 2].getIsMarked()) {// left
                    return false;//False because the border is already marked
                }
                break;

            case 4:
                if (board[rowOfSquare * 2 + 1][colOfSquare * 2 + 2].getIsMarked()) { // right
                    return false;//False because the border is already marked
                }
                break;
        }
        return true;//The border could be marked
    }

    /**
     * Define which player win
     * 
     * @param who1
     * @param who2
     */
    public byte whichPlayerWin(char who1, char who2) {
        int squaresMarkedByPlayer1 = 0;
        int squaresMarkedByPlayer2 = 0;

        for (int i = 0; i < rowsSize; i++) {
            for (int j = 0; j < colsSize; j++) {
                if (((Square) board[i * 2 + 1][j * 2 + 1]).getBy() == who1)
                    squaresMarkedByPlayer1++;
                if (((Square) board[i * 2 + 1][j * 2 + 1]).getBy() == who2)
                    squaresMarkedByPlayer2++;
            }
        }

        if (squaresMarkedByPlayer1 == squaresMarkedByPlayer2) { // The game is tie
            return 0;
        } else if (squaresMarkedByPlayer1 > squaresMarkedByPlayer2) {// First player win
            return 1;
        } else {// Second player wins
            return 2;
        }

    }

}
