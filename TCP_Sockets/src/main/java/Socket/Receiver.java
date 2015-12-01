package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        try  {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(this.socket.getInputStream()));
            String buffer;
            //Lesen der eingegangenen daten
            while (!this.socket.isInputShutdown()&&(buffer = reader.readLine()) != null) {
                System.out.println(buffer);

            }
            this.socket.shutdownInput();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void run() {
        receive();
    }
}
