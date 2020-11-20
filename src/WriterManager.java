
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class WriterManager {

    private BufferedWriter writer;

    public void open(String fileName) throws IOException {
        boolean succes = new File(fileName).delete();
        System.out.println(succes);
        writer = new BufferedWriter(new FileWriter(fileName));
    }

    public void write(PlayerList playerList) throws IOException {
        writer.write(playerList.toFileString() + "\n");
    }

    public void close() throws IOException {
        writer.close();
    }
}
