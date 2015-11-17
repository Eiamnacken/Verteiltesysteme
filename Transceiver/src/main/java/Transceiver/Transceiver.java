package Transceiver;

import Transceiver.Socket.Receiver;
import Transceiver.Socket.Transmitter;
import Transceiver.Socket.UDPSocket;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by eiamnacken on 05.11.15.
 */
public class Transceiver implements Runnable {

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

    /**
     * Starte Transceiver im Clinet modus
     *
     * @param port Port den der Client sendet und horcht
     * @param host Host an den der Client sendet
     */
    public Transceiver(int port, String host) throws SocketException, UnknownHostException {
        this.receiver = new Receiver(new UDPSocket(port));
        this.transmitter = new Transmitter(new UDPSocket(port, host));
        this.server = false;
    }

    /**
     * Startet den Trasnceiver im Server modus
     *
     * @param port Port an dem der Server lauscht und sendet
     */
    public Transceiver(int port) throws SocketException {
        this.receiver = new Receiver(new UDPSocket(port));
        this.server = true;
    }

    /**
     * Startet den transceiver in den ServerModus
     */
    public void startSender() {
        Scanner in = new Scanner(System.in);
        Thread read = new Thread() {
            public void run() {
                while (!readExecute) {
                    StringBuilder builder = new StringBuilder();
                    while (in.hasNext()) {
                        builder.append(in.nextLine());
                        try {
                            transmitter.send(builder.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        builder.delete(0, builder.length());
                    }
                    try {
                        transmitter.send("\\u0004");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
    }

    /**
     * Starte den Sender um senden zu können
     */
    public void startServer() {
        Thread write = new Thread() {
            public void run() {
                while (!writeExecute) {
                    try {
                        String message = receiver.receive();
                        System.out.println(message);
                        if (message.equals("\\u0004")) {
                            writeExecute = true;
                        }
                        if (server) {
                            String host = receiver.getAdress();
                            int port = receiver.getPort();
                            transmitter = new Transmitter(new UDPSocket(port, host));
                            startSender();
                            server = false;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        write.start();
    }

    @Override
    public void run() {
        startServer();
        if (!this.server) {
            startSender();
        }
    }
}
