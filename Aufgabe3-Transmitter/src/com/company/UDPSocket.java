package com.company;

import java.io.IOException;
import java.net.*;

/**
 * Created by eiamnacken on 29.10.15.
 */
public class UDPSocket {

    /**
     * Der Port auf dem de UDP socket lauschen soll bzw sendet
     */
    private int port;
    /**
     * Die adresse an den der socket sendet
     */
    private String host;

    /**
     * Socket zum überagen
     */
    private DatagramSocket socket;

    /**
     * Erstellt einen UDP socket mit der Port und den Host
     * @param port  Port auf dem er sendet/lauscht
     * @param host  Host an den er sendet
     */
    public UDPSocket(int port, String host) {
        this.port = port;
        this.host = host;
    }

    /**
     * Sendet einen String
     * Sendet einen String an die angegebenen Adessesn #UDPSocket
     * Der string wird dafür in einen Datagrampacket verpackt.
     * @param s Der String der zu senden ist
     */
    public void send(String s){
        try {
            socket= new DatagramSocket();
            InetAddress address = InetAddress.getByName(host);
            DatagramPacket packet = new DatagramPacket(s.getBytes(),s.length(),address,port);
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Empfängt ein datapacket und gibt dieses als String zurück
     * @param maxBytes  Die größe des bytes arrays
     * @return          String
     */
    public String received(int maxBytes){

    }




}
