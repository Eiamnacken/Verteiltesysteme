package com.TCPChat.chat.cs.sockets;

import com.TCPChat.chat.cs.ChatEventManager;

import java.net.Socket;

/**
 * Created by sven on 08.12.15.
 */
public class ServerCommunicator extends Thread{
    public ServerCommunicator(Socket incoming, ChatEventManager manager) {

    }

    @Override
    public void run() {
        super.run();
    }
}
