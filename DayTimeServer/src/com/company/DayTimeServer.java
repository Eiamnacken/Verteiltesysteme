package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class DayTimeServer {

    private static final int BUFSIZE = 508;

    public static void main(String[] args) {
        if (args.length!=1){
            System.err.println("Usage: Java EchoServer Port");
            return;
        }

        int port = Integer.parseInt(args[0]);

        try (DatagramSocket socket = new DatagramSocket(port)){
            byte[] sendData = "Hallo vom Server".getBytes();
            DatagramPacket packetIn = new DatagramPacket(new byte[BUFSIZE],BUFSIZE);
            DatagramPacket packetOut = new DatagramPacket(sendData,sendData.length);
            while (true){

                socket.receive(packetIn);
                String received = new String(packetIn.getData());
                System.out.println(received);
                packetOut.setData(sendData);
                packetOut.setLength(sendData.length);
                packetOut.setAddress(packetIn.getAddress());
                packetOut.setPort(packetIn.getPort());
                socket.send(packetOut);
            }


        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
