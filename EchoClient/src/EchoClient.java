import java.io.IOException;
import java.net.*;

/**
 * Created by sven on 12.10.15.
 */
public class EchoClient {
    private static final int BUFSIZE = 508;

    public static void main(String[] args) {
        if (args.length!=2){
            System.err.println("Usage: java DayTimeClient <host> <port>");
            return;
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);
        try (DatagramSocket socket = new DatagramSocket()){

            InetAddress address = InetAddress.getByName(host);
            DatagramPacket packetOut = new DatagramPacket(new byte[1],1,address,port);
            socket.send(packetOut);
            DatagramPacket packetIn = new DatagramPacket(new byte[BUFSIZE],BUFSIZE);
            socket.receive(packetIn);
            String received = new String(packetIn.getData(),0,packetIn.getLength());
            System.out.println(received);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
