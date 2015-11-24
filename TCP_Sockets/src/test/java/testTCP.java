import org.junit.Test;

import Socket.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by sven on 24.11.15.
 */
public class testTCP {

    @Test
    public void testConnection(){
        try (
             ServerSocket serverSocket = new ServerSocket(5000);
             Socket socket = new Socket("localhost",5000);
             Socket socket1  = serverSocket.accept()
        ){
            Transmitter transmitter = new Transmitter(socket);
            Receiver receiver = new Receiver(socket1);
            new Thread(transmitter).start();
            new Thread(receiver).start();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
