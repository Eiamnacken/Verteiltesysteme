package chat.cs.sockets;

import java.net.Socket;

import chat.cs.Poller;
import chat.cs.GUI;
import chat.cs.IChat;

public class Client {
	private IChat proxy;
	private GUI gui;
	private String ownName;
	private Poller poller;

	public Client(String adress,int port) throws Exception {
		Socket socket = new Socket(adress, port);
		proxy  = new ServerProxy(socket);
		gui    = new GUI(proxy);
		poller = new Poller(proxy, gui);
	}

	public void startClient() throws Exception {
		ownName = gui.showLoginFrame();
		gui.showChatFrame();
		proxy.login(ownName);
		poller.setOwnName(ownName);
		poller.start();
	}

	public static void main(String[] args) {
		try {
			new Client(args[0],Integer.valueOf(args[1])).startClient();
		} catch (Exception e) {System.err.println(e);}
	} 
} 
