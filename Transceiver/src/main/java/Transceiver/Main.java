package Transceiver;

import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by sven on 17.11.15.
 */
public class Main {

    public static void main(String[] args){
        try {
            Transceiver transceiver = new Transceiver(5000,"localhost");
            transceiver.run();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
