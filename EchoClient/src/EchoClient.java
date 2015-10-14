import java.io.IOException;
import java.net.*;

/**
 * Created by sven on 12.10.15.
 */
public class EchoClient {
    private static final int BUFSIZE = 1024;

    public static void main(String[] args) {
        if (args.length!=2){
            System.err.println("Usage: java DayTimeClient <host> <port>");
            return;
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);
        try (DatagramSocket socket = new DatagramSocket()){
            byte[] buffer = new byte[1];
            InetAddress address = InetAddress.getByName(host);
            DatagramPacket packetOut = new DatagramPacket(buffer,buffer.length,address,port);
            packetOut.setData("Hallo an server".getBytes());
            socket.send(packetOut);
            DatagramPacket packetIn = new DatagramPacket(new byte[BUFSIZE],BUFSIZE);
            socket.receive(packetIn);
            String received = new String(packetIn.getData());
            System.out.println(received);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
