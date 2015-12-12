package com.TCPChat.chat.cs.sockets;

/*public class Client {
	private IChat proxy;
	private String ownName;
	private Poller poller;

	public Client() throws Exception {
		Socket socket = new Socket("localhost", 8205);
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
			new Client().startClient();
		} catch (Exception e) {System.err.println(e);}
	} 
} */
