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
    private Socket socket;

    private boolean execute = false;

    /**
     * Erstellt den Receiver als Server
     *
     * @param socket
     */
    public Receiver(Socket socket) throws IOException {
        this.socket = socket;
    }

    public void receive() {
        System.out.println(this.socket.getLocalPort());
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(this.socket.getInputStream()))) {
            String buffer;
            //Lesen der eingegangenen daten
            while ((buffer = reader.readLine()) != null) {
                System.out.println(buffer);
                if (buffer.contains("\u0004")){
                    break;
                }
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
