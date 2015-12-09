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


    private int port;

    private String adress;

    private boolean client=false;

    /**
     * Erstellt Transceiver im Client modus
     * @param port      Port an dem der Client lauscht bzw sendet
     * @param adress    Adresse an den der Client sendet
     */
    public Transceiver(int port,String adress) throws IOException {
        this.client=true;
        this.port=port;
        this.adress=adress;
    }

    /**
     * Erstellt Transceiver im server Modus
     * @param port  An dem der Server lauscht
     */
    public Transceiver(int port) throws IOException {
        this.port=port;
    }

    public void startMultiServer(){

        try (ServerSocket serverSocket = new ServerSocket(this.port)){
            Socket socket = serverSocket.accept();
            (new Transmitter(socket)).start();
            (new Receiver(socket)).start();
            while (true){
                socket = serverSocket.accept();
                (new Receiver(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        if (!client) {
            startMultiServer();
        }else {
            try {
                Socket socket = new Socket(this.adress,this.port);
                Transmitter transmitter = new Transmitter(socket);
                Receiver receiver = new Receiver(socket);
                transmitter.start();
                receiver.start();
                transmitter.join();
                receiver.join();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void close() throws IOException {

    }
}
