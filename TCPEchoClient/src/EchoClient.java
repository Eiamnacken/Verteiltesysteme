import java.io.*;
import java.net.Socket;
import java.util.logging.SocketHandler;

/**
 * Created by sven on 18.11.15.
 */
public class EchoClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 7777)) {
            //Senden
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            //Empfange
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            String input;
            writer.println("Test");
            while ((input=in.readLine())!=null){
                System.out.print(input);
                if (input.equals("Test")) break;
            }
            socket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
