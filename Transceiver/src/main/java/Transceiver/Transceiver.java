package Transceiver;

import Transceiver.Socket.Receiver;
import Transceiver.Socket.Transmitter;
import Transceiver.Socket.UDPSocket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by eiamnacken on 05.11.15.
 */
public class Transceiver {

    /**
     * Soll der #read beendet werden
     */
    private boolean readExecute = false;

    /**
     * Soll der #write beendet werden
     */
    private boolean writeExecute = false;

    /**
     * Um daten zu erhalten
     */
    private Receiver receiver;

    /**
     * Um daten zu senden
     */
    private Transmitter transmitter;

    private boolean server;

    private UDPSocket socket;

    /**
     * Starte Transceiver im Clinet modus
     *
     * @param port Port den der Client sendet und horcht
     * @param host Host an den der Client sendet
     */
    public Transceiver(int port, String host) throws SocketException, UnknownHostException {
        this.socket= new UDPSocket();
        this.socket.connect(InetAddress.getByName(host),port);
        this.receiver = new Receiver(this.socket);
        this.transmitter = new Transmitter(this.socket);
        this.server = false;
    }

    /**
     * Startet den Trasnceiver im Server modus
     *
     * @param port Port an dem der Server lauscht und sendet
     */
    public Transceiver(int port) throws SocketException {
        this.socket= new UDPSocket(port);
        this.receiver = new Receiver(this.socket);
        this.server = true;
    }

    /**
     * Startet den transceiver in den ServerModus
     */
    private void startSender() {
        Thread read = new Thread() {
            public void run() {
                while (!readExecute) {
                    Scanner in = new Scanner(System.in);
                    StringBuilder builder = new StringBuilder();
                    while (in.hasNextLine()){
                        String buffer = in.nextLine();
                        if (buffer.equals("\u0004")){
                            break;
                        }
                        try {
                            transmitter.send(buffer);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    transmitter.send("\u0004");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                transmitter.disconnect();


            }
        };
        read.start();
    }

    /**
     * Starte den Sender um senden zu können
     */
    private void startServer() {
        Thread write = new Thread() {
            public void run() {
                while (!writeExecute) {
                    try {
                        String message = receiver.receive();
                        System.out.println(message);
                        if (message.contains("\u0004")) {
                            writeExecute = true;
                        }
                        if (server) {
                            String host = receiver.getAdress();
                            int port = receiver.getPort();
                            socket.connect(InetAddress.getByName(host),port);
                            transmitter = new Transmitter(socket);
                            startSender();
                            server = false;
                        }
                    } catch (IOException ignored) {

                    }
                }
                receiver.disconnect();
            }
        };
        write.start();
    }


    public void run() {
        startServer();
        if (!this.server) {
            startSender();
        }
    }
}
