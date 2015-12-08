package com.TCPChat.chat.cs.sockets;

import com.TCPChat.chat.ChatEvent;
import com.TCPChat.chat.cs.IChat;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ServerProxy implements IChat {
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private boolean loggedIn = false;

	public ServerProxy(Socket socket) throws Exception {
		this.socket = socket;
		out = new ObjectOutputStream(socket.getOutputStream());
		in  = new ObjectInputStream(socket.getInputStream());
	} 

	private synchronized Object communicate(Message msg) {  
		Object answer = null;

		try {
			out.writeObject(msg);
			answer = in.readObject();
		} catch (Exception e) {System.err.println(e);}

		return answer;
	} 

	public void login(String name) {
		communicate(new Message(Message.LOGIN, name)); 
		loggedIn = true;
	} 

	public void logout(String name) {
		loggedIn = false;
		communicate(new Message(Message.LOGOUT, name));
		try {
			in.close(); out.close(); socket.close();
		} catch (Exception e) {System.err.println(e);}
	} 

	public void comment(String name, String comment) {
		communicate(new Message(Message.COMMENT, name, comment));
	} 

	public ChatEvent poll(String name) {
		if (loggedIn)
			return (ChatEvent) communicate(new Message(Message.POLLING, name));
		else return null;
	} 
} 
