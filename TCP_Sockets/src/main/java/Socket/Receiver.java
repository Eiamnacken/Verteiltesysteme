package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by sven on 18.11.15.
 * Ein Server der jede Verbindung akzeptiert
 */
public class Receiver implements Runnable {
    /**
     * Socket fü die übertragung
     */
    private ServerSocket socket;

    private boolean execute = false;

    /**
     * Erstellt den Receiver als Server
     *
     * @param socket
     */
    public Receiver(ServerSocket socket) throws IOException {
        this.socket = socket;
    }

    public void receive() {
        try (Socket socket1 = this.socket.accept();
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(socket1.getInputStream()))) {
            String buffer;
            //Lesen der eingegangenen daten
            while ((buffer = reader.readLine()) != null) {
                System.out.println(buffer);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        receive();
    }
}
