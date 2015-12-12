package com.TCPChat.chat.cs.sockets;


import com.TCPChat.chat.cs.ChatEventManager;

import java.net.ServerSocket;
import java.net.Socket;

public class Server  {

  public static void main (String args[]) {

    ChatEventManager manager = new ChatEventManager();

    try (ServerSocket serverSocket = new ServerSocket(8205)){
      
      System.out.println("Server waiting for clients...");
      
      while (true) {
        Socket incoming = serverSocket.accept();
        ServerCommunicator communicator = new ServerCommunicator(incoming, manager);
        communicator.start();
      }
    } catch ( Exception e) { System.err.println(e); }
  } 
}
