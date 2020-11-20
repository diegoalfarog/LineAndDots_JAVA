
/**
 * Border class
 * 
 * @author Diego Alfaro
 * @author Nicole Garcia
 * @author Gabriel Guzman
 * @version 5 November 2020
 */

public class Border extends Shape {

    /**
     * Define Marked border
     * @param isMarked
     */
    public Border(Boolean isMarked) {
        super(isMarked);
    }

    /**
     *@Override
     */
    public String toString() {
        return "Borde: esta marcado: " + super.getIsMarked();
    }

}
