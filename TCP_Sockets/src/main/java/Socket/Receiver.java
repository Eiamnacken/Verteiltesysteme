package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by sven on 18.11.15.
 * Ein Server der jede Verbindung akzeptiert
 */
public class Receiver implements Runnable{
    /**
     * Socket fü die übertragung
     */
    private final Socket socket;

    private boolean execute=false;

    /**
     * Erstellt den Receiver als Server
     *
     * @param socket
     */
    public Receiver(Socket socket) {
        this.socket = socket;
    }

    public void receive() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(this.socket.getInputStream()))) {
            //Starte thread
            while (!this.execute) {
                String buffer;
                //Lesen der eingegangenen daten
                while ((buffer = reader.readLine())!=null) {
                    System.out.println(buffer);
                    //Bei EOF ende thread
                    if (buffer.contains("\u0004")){
                        this.execute=true;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        receive();
    }
}
