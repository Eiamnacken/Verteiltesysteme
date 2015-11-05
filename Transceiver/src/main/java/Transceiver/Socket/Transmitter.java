package Transceiver.Socket;

import Transceiver.Transceiver;

import java.io.IOException;

/**
 * Created by sven on 02.11.15.
 */
public class Transmitter implements Runnable{

    /**
     * Socket mit dem Verbunden wird
     */
    UdpSocket socket;

    /**
     * Erstellt einen Transmitter
     * @param socket
     * @see #socket
     */
    public Transmitter(UdpSocket socket) {
        this.socket=socket;
    }

    /**
     * Sendet eine Packet an einen Receiver
     * @param s
     * @throws IOException
     */
    public void send(String s) throws IOException {
        if (s.isEmpty()) return;
        else this.socket.send(s);
    }

    public void disconnect(){
        this.socket.disconnect();
    }

    @Override
    public void run() {
        

    }
}
