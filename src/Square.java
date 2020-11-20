
/**
 * Square class
 * 
 * @author Diego Alfaro
 * @author Nicole Garcia
 * @author Gabriel Guzman
 * @version 5 November 2020
 */
public class Square extends Shape {

    //Define attributes
    private char by;

    /**
     *@param isMarked 
     */
    public Square(boolean isMarked) {
        super(isMarked);
    }

    /**
     *@param isMarked
     *@param by 
     */
    public Square(boolean isMarked, char by) {
        super(isMarked);
        this.by = by;
    }

    /**
     * @return the by
     */
    public char getBy() {
        return by;
    }

    /**
     * @param by the by to set
     */
    public void setBy(char by) {
        this.by = by;
    }

    /**
     * @Override 
     */
    public String toString() {
        return "Figura :: esta marcado: " + super.getIsMarked();
    }
}
