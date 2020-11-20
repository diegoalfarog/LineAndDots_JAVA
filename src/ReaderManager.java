import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReaderManager {

    private BufferedReader reader;

    public void open(String fileName) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(fileName));
    }

    public Player[] read() throws IOException {

        String line = reader.readLine(); //retorna null cuando no hay más registros
        Player playerList[];
        String data[];
        String playerData[];
        if (line != null) {
            data = line.split("-");//VER EL ARCHIVO.TXT PARA ENTENDER EL GUION "-"
            playerList = new Player[data.length];
            for (int i = 0; i < data.length; i++) {
                playerData = data[i].split("_");
                playerList[i] = new Player();
                playerList[i].setId(playerData[0]);
                playerList[i].setName(playerData[1]);
                playerList[i].setGameIdentifier(playerData[2].charAt(0));
                playerList[i].addPoints(Double.parseDouble(playerData[3]));
            }
        } else {
            return null;
        }
        return playerList;
    }

    public void close() throws IOException {
        reader.close();
    }

}
