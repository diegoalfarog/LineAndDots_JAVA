
/**
 * PlayerList class that saves a list of players
 * 
 * @author Diego Alfaro
 * @author Nicole Garcia
 * @author Gabriel Guzman
 * @version 26 de november 2020
 */
public class PlayerList {

    // Define attributes
    private Player playerList[];
    private int index;

    //Constructor
    public PlayerList() {
        this.playerList = new Player[0];
        index = 0;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param playerList the playerList to set
     */
    public void setPlayerList(Player[] playerList) {
        this.index = playerList.length;
        this.playerList = playerList;
    }

    /**
     * Insert the player in the array playerList
     * 
     * @param player
     */
    public void insertion(Player player) {

        if (index < playerList.length) {
            this.playerList[index] = player;
            index = index + 1;
        } else {
            this.extendList();
            this.playerList[index] = player;
            index = index + 1;
        }

    }

    /**
     * Extend list limit
     */
    private void extendList() {
        Player[] newVector = new Player[playerList.length];

        //slip the list
        for (int i = 0; i < playerList.length; i++) {
            newVector[i] = playerList[i];
        }

        //clone the list to extend the list
        playerList = new Player[playerList.length + 1];
        for (int i = 0; i < newVector.length; i++) {
            playerList[i] = newVector[i];
        }
    }

    /**
     * Search the player
     * 
     * @return player or null
     */
    public int searchPlayer(String id) {

        //slip the list 
        for (int i = 0; i < index; i++) {
            if (playerList[i] != null && id.equals(playerList[i].getId())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Search the player
     * 
     * @return player or null
     */
    public boolean isValidGameIdentifier(char gId) {

        if (isEmpty()) {
            return true;
        }
        for (int i = 0; i < index; i++) {
            if (playerList[i] != null && playerList[i].getGameIdentifier() == gId) {
                return false;
            }
        }
        return true;
    }

    /**
     * Delete player
     * 
     * @param id
     * @return tempPlayer
     */
    public Player deletePlayer(String id) {

        Player tempPlayer;
        int foundedIndex = searchPlayer(id);
        tempPlayer = playerList[foundedIndex];

        //Search player for id to delete
        if (foundedIndex == 0) {
            playerList[foundedIndex] = null;
            index -= 1;
        } else {

            for (int i = foundedIndex; i < index - 1; i++) {
                playerList[i] = playerList[i + 1];
            }
            playerList[index] = null;

            index -= 1;
        }
        return tempPlayer;
    }

    /**
     * Test if the length is empty
     * 
     * @return true if the playerlist is empty
     */
    public boolean isEmpty() {
        return playerList.length == 0 || playerList[0] == null;
    }

    /**
     * Order the list in a ascendent way
     */
    private void bubbleSort_Player() {
        Player temp;
        boolean swap = true;
        while (swap) {
            swap = false;
            for (int j = 0; j < playerList.length - 1; j++) {
                if (playerList[j + 1] != null && playerList[j].getPoints() < playerList[j + 1].getPoints()) {
                    temp = playerList[j];
                    playerList[j] = playerList[j + 1];
                    playerList[j + 1] = temp;
                    swap = true;
                    continue;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * Print the player list
     * @return text
     */
    public String printPlayerList() {

        // Call the bubble sort
        this.bubbleSort_Player();

        // Create a String to return a row
        String text = "";

        // Perform a slipping of the list
        for (int i = 0; i < playerList.length; i++) {
            if (playerList[i] != null) {
                text += "\n" + playerList[i].toString() + "\n";
            }
        }
        return text;
    }

    public String toFileString() {
        String dataInString = "";
        String separatorOfPlayers = "-";
        if (!isEmpty()) {
            for (int i = 0; i < index; i++) {
                if (i + 1 == index) {
                    separatorOfPlayers = "";
                }
                dataInString += playerList[i].toFileString() + separatorOfPlayers;
            }
        }
        return dataInString;

    }

}