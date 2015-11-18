import java.io.*;
import java.net.Socket;
import java.util.logging.SocketHandler;

/**
 * Created by sven on 18.11.15.
 */
public class EchoClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 7777)) {
            OutputStream out = socket.getOutputStream();
            //Senden
            PrintWriter writer = new PrintWriter(out, true);
            writer.print("Hallo vom Client\n");
            //Empfange
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            String input;
            while ((input=in.readLine())!=null){
                System.out.print(input);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
