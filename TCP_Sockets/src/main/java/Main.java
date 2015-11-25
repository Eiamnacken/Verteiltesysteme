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
            try (ServerSocket serverSocket = new ServerSocket(port)
            ) {
                System.out.println("Server gestartet");
                Receiver receiver = new Receiver(serverSocket);
                Thread write = new Thread(receiver);
                write.start();
                write.join();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try (Socket socket = new Socket(args[0], Integer.valueOf(args[1]))
            ) {
                System.out.println("Client gestartet");
                Transmitter transmitter = new Transmitter(socket);
                Thread send = new Thread(transmitter);
                send.start();
                send.join();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
