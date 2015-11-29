import Socket.Receiver;
import Socket.Transmitter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by sven on 18.11.15.
 */
class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Bitte gebe den Port und den Host an");
            System.exit(-1);
        }
        if (args[0].equals("-l")) {
            int port = Integer.parseInt(args[1]);
            try (Transceiver transceiver = new Transceiver(port)
            ) {
                System.out.println("Server gestartet");

                Thread write = new Thread(transceiver);
                write.start();
                write.join();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try (Transceiver transceiver = new Transceiver(Integer.parseInt(args[1]),args[0])
            ) {
                System.out.println("Client gestartet");
                Thread send = new Thread(transceiver);
                send.start();
                send.join();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
