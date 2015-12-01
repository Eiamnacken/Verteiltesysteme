import Socket.Receiver;
import Socket.Transmitter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by sven on 27.11.15.
 * Transceiver kann im Server oder Client modus erstelt werden.
 * Empfängt und sendet Streams zu einem ausgewählten partner
 */
public class Transceiver implements Runnable,AutoCloseable{

    /**
     * Socket zum verbinden bzw empfange von TCP streams
     */
    private Socket socket;

    private Thread read;

    private Thread write;

    /**
     * Erstellt Transceiver im Client modus
     * @param port      Port an dem der Client lauscht bzw sendet
     * @param adress    Adresse an den der Client sendet
     */
    public Transceiver(int port,String adress) throws IOException {
        this.socket=new Socket(adress, port);
        this.read = new Thread(new Transmitter(this.socket));
        this.write = new Thread(new Receiver(this.socket));
    }

    /**
     * Erstellt Transceiver im server Modus
     * @param port  An dem der Server lauscht
     */
    public Transceiver(int port) throws IOException {
        this.socket = new ServerSocket(port).accept();
        this.read = new Thread(new Transmitter(this.socket));
        this.write = new Thread(new Receiver(this.socket));
    }

    @Override
    public void run() {
        this.read.start();
        this.write.start();
        try {
            this.read.join();
            this.write.join();
            this.socket.close();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {

    }
}
