
/**
 * Shape class
 * 
 * @author Diego Alfaro
 * @author Nicole Garcia
 * @author Gabriel Guzman
  * @version 5 November 2020
 */
public class Shape {

    //Define attributes
    private boolean isMarked;

    /**
     * Constructor with the one param
     *@param isMarked 
     */
    public Shape(boolean isMarked) {
        this.isMarked = isMarked;
    }

    /**
     *Get the Marked
     *@return isMarked
     */
    public boolean getIsMarked() {
        return this.isMarked;
    }

    /**
     * Set the Marked
     * @param isMarked
     */
    public void setIsMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }

}
