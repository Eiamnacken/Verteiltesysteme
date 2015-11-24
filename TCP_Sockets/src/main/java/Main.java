import Socket.Receiver;
import Socket.Transmitter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by sven on 18.11.15.
 */
class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Bitte gebe den Port und den Host an");
        }
        if (args[0].equals("-l")) {
            try (ServerSocket serverSocket = new ServerSocket(Integer.valueOf(args[1]));
                 Socket socket = serverSocket.accept()
            ) {
                System.out.println("Server gestartet");
                Receiver receiver = new Receiver(socket);
                Thread write = new Thread(receiver);
                write.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (Socket socket = new Socket(args[1], Integer.valueOf(args[0]))
            ) {
                System.out.println("Client gestartet");
                Transmitter transmitter = new Transmitter(socket);
                Thread send = new Thread(transmitter);
                send.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
