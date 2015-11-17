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
public class Transceiver implements Runnable{

    /**
     * Soll der #read beendet werden
     */
    private boolean readExecute=false;

    /**
     * Soll der #write beendet werden
     */
    private boolean writeExecute=false;

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
    }

    /**
     * Startet den Trasnceiver im Server modus
     * @param port      Port an dem der Server lauscht und sendet
     */
    public Transceiver(int port) throws SocketException {
        this.receiver = new Receiver(new UDPSocket(port));
    }

    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        Thread read = new Thread(){
            public void run(){
                while (!readExecute){
                    StringBuilder builder = new StringBuilder();
                    while (in.hasNext()){
                        builder.append(in.next());
                    }
                    try {
                        transmitter.send(builder.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread write= new Thread(){
          public void run() {
              while (!writeExecute){
                  try {
                      String message = receiver.receive();
                      System.out.println(message);
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          }
        };
    }
}
