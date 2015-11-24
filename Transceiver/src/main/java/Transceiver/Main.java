package Transceiver;

import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by sven on 17.11.15.
 */
public class Main {

    public static void main(String[] args){
        Transceiver transceiver;
        switch (args.length){
            case 1:
                try {
                    transceiver = new Transceiver(Integer.parseInt(args[0]));
                    transceiver.run();
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    transceiver = new Transceiver(Integer.parseInt(args[0]),args[1]);
                    transceiver.run();
                } catch (SocketException | UnknownHostException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Bitte geben sie Port oder Port und Host an");
                break;
        }
    }
}
