package Transceiver.Socket;

import Transceiver.Transceiver;

import java.io.IOException;

/**
 * Created by sven on 02.11.15.
 */
public class Receiver implements Runnable{

    /**
     * Kümmert sich um die verbindung
     */
    private UdpSocket socket;

    /**
     * Größe des  packets das empfangen werden darf
     */
    private static final int MAXBYTES=508;

    /**
     * Erstellt einen Receiver der empfangen kann
     * @param socket    Socket der verbinden soll
     */
    public Receiver(UdpSocket socket) {
        this.socket = socket;
    }

    /**
     * Empfängt eine packet
     * @return              Das Packet in String representation
     * @throws IOException
     * @see #socket
     */
    public String receive() throws IOException {
        return this.socket.receive(MAXBYTES);
    }

    public String getAdress(){
        String adress = this.socket.getHost().toString();
        return adress;
    }

    public int getPort(){
        return this.socket.getPort();
    }

    public void disconnect(){
        this.socket.disconnect();
    }


    @Override
    public void run() {

    }
}
