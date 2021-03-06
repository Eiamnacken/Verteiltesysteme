package Transceiver.Socket;

import java.io.IOException;

/**
 * Created by sven on 02.11.15.
 */
public class Transmitter implements Runnable{

    /**
     * Socket mit dem Verbunden wird
     */
    UDPSocket socket;

    /**
     * Erstellt einen Transmitter
     * @param socket
     * @see #socket
     */
    public Transmitter(UDPSocket socket) {
        this.socket=socket;
    }

    /**
     * Sendet eine Packet an einen Receiver
     * @param s
     * @throws IOException
     */
    public void send(String s) throws IOException {
        if (s.isEmpty()) return;
        else this.socket.send(s);
    }

    public void disconnect(){
        this.socket.disconnect();
    }

    @Override
    public void run() {

      System.out.println("ready to send");

		while(input.hasNextLine()){
			mySock.send(input.nextLine());
		}
		mySock.send(""+(char) 4);

		try{
			mySock.close();
			System.out.println("End of Transmission");
		}
		catch (Exception e){
			e.printStackTrace();
		}


    }
}
