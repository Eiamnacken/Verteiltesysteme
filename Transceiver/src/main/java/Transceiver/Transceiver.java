package Transceiver;

import Transceiver.Socket.Receiver;
import Transceiver.Socket.Transmitter;
import Transceiver.Socket.UDPSocket;

import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by eiamnacken on 05.11.15.
 */
public class Transceiver implements Runnable{


    /**
     * Thread um daten zu schreiben auf die Konsole
     */
    private Thread write;

    /**
     * Thread um daten von der Konsole zu lesen
     */
    private  Thread read;

    /**
     * Soll der #read beendet werden
     */
    private boolean readExecute;

    /**
     * Soll der #write beendet werden
     */
    private boolean writeExecute;

    /**
     * Um daten zu erhalten
     */
    private Receiver receiver;

    /**
     * Um daten zu senden
     */
    private Transmitter transmitter;

    /**
     * Starte Transceiver im Clinet modus
     * @param port      Port den der Client sendet und horcht
     * @param host      Host an den der Client sendet
     */
    public Transceiver(int port, String host) throws SocketException, UnknownHostException {
        this.receiver= new Receiver(new UDPSocket(port));
        this.transmitter = new Transmitter(new UDPSocket(port,host));
        this.read = new Thread();
        this.write = new Thread();
    }

    /**
     * Startet den Trasnceiver im Server modus
     * @param port      Port an dem der Server lauscht und sendet
     */
    public Transceiver(int port) throws SocketException {
        this.receiver = new Receiver(new UDPSocket(port));
        this.write = new Thread();
    }

    @Override
    public void run() {
        //TODO Logik implementieren beide threads zum lesen um empfangen

    }
}
