
/**
 * Player class that saves all data about a player
 * 
 * @author Diego Alfaro
 * @author Nicole Garcia
 * @author Gabriel Guzman
 * @version 5 November 2020

 */
public class Player {

    // Define attributes
    private String id;
    private String name;
    private char gameIdentifier;
    private double points;

    /**
    * Constructor with one parameter
    * 
    */
    public Player() {
    }

    /**
     * Constructor with one parameter
     * 
     */
    public Player(String id, String name, char gameIdentifier) {
        this.name = name;
        this.id = id;
        this.gameIdentifier = gameIdentifier;
    }

    /**
     * Set the player id
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Set the player name
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param gameIdentifier the gameIdentifier to set
     */
    public void setGameIdentifier(char gameIdentifier) {
        this.gameIdentifier = gameIdentifier;
    }

    /**
     * Get the player id
     * 
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Get the player name
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the player points
     * 
     * @return the points
     */
    public double getPoints() {
        return points;
    }

    /**
     * @return the gameIdentifier
     */
    public char getGameIdentifier() {
        return gameIdentifier;
    }

    /**
     * Add points to the player
     */
    public void addPoints(double toaddpoints) {
        this.points += toaddpoints;
    }

    /**
     * @return all attributes
     */
    public String toString() {
        return "Jugador" + "\nId: " + id + "\nNombre: " + name + "\nPuntos del jugador: " + points;
    }

    /**
    * @return all attributes
    */
    public String toFileString() {
        return id + "_" + name + "_" + gameIdentifier + "_" + points;
    }

}