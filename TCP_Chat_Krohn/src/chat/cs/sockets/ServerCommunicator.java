package chat.cs.sockets;

import chat.ChatEvent;
import chat.cs.ChatEventManager;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ServerCommunicator extends Thread {
    private ChatEventManager manager;
    private Socket socket;

    public ServerCommunicator(Socket socket, ChatEventManager manager) {
        this.socket = socket;
        this.manager = manager;
    }

    public void run() {
        boolean inactive = false;

        try {
            ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());

            while(!inactive) {
                Message message = (Message) ois.readObject();

                switch(message.getMessageType()) {
                    case Message.LOGIN:
                        this.manager.login(message.getName());
                        oos.writeObject(new Message(Message.CONFIRM));
                        break;
                    case Message.COMMENT:
                        this.manager.comment(message.getName(), message.getComment());
                        oos.writeObject(new Message(Message.CONFIRM));
                        break;
                    case Message.LOGOUT:
                        this.manager.logout(message.getName());
                        inactive = true;
                        oos.writeObject(new Message(Message.CONFIRM));
                        break;
                    case Message.POLLING:
                        ChatEvent event = this.manager.poll(message.getName());
                        oos.writeObject(event);
                        break;
                }
                oos.flush();
            }
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }
}