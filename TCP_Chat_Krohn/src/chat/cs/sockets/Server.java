package chat.cs.sockets;

import chat.cs.ChatEventManager;
import java.net.*;

public class Server  {

  public static void main (String args[]) {

    ChatEventManager manager = new ChatEventManager();

    try (ServerSocket serverSocket = new ServerSocket(9000)){
      
      System.out.println("Server waiting for clients...");
      
      while (true) {
        Socket incoming = serverSocket.accept();
        ServerCommunicator communicator = new ServerCommunicator(incoming, manager);
        communicator.start();
      }
    } catch ( Exception e) { System.err.println(e); } 
  } 
}
