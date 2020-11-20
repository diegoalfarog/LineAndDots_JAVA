/**
 * SquareUbication
 */
public class SquareUbication {

    private byte row;
    private byte col;

    public SquareUbication(byte row, byte col) {
        this.row = row;
        this.col = col;
    }

    /**
     * @return the col
     */
    public byte getCol() {
        return col;
    }

    /**
     * @return the row
     */
    public byte getRow() {
        return row;
    }
}