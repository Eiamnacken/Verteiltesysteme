package Socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by sven on 18.11.15.
 */
public class Transmitter implements Runnable {


    /**
     * Zum senden von Daten
     */
    private final Socket socket;

    private boolean execute = false;

    /**
     * Erstellt einen Transmitter zum senden von Daten
     *
     * @param socket Socket mit dem wir senden
     */
    public Transmitter(Socket socket) {
        this.socket = socket;
    }

    /**
     * Sendet die eingabe der Kommandozeile an einen TCP socket bis <code>EOF</code>
     */
    public void send() {
        try (PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
             Scanner in = new Scanner(System.in)
        ) {
            while (!execute) {
                String buffer;
                buffer = in.nextLine();
                writer.println(buffer);
                if (buffer.contains("\u0004")){
                    this.execute=true;
                }
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        send();
    }
}
