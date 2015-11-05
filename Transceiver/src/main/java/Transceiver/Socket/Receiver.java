package Transceiver.Socket;

import java.io.IOException;

/**
 * Created by sven on 02.11.15.
 */
public class Receiver implements Runnable{

    /**
     * Kümmert sich um die verbindung
     */
    private UDPSocket socket;

    /**
     * Größe des  packets das empfangen werden darf
     */
    private static final int MAXBYTES=508;

    /**
     * Erstellt einen Receiver der empfangen kann
     * @param socket    Socket der verbinden soll
     */
    public Receiver(UDPSocket socket) {
        this.socket = socket;
    }

    /**
     * Empfängt eine packet
     * @return              Das Packet in String representation
     * @throws IOException
     * @see #socket
     */
    public String receive() throws IOException {
        return this.socket.receive(MAXBYTES);
    }

    public String getAdress(){
        String adress = this.socket.getHost().toString();
        return adress;
    }

    public int getPort(){
        return this.socket.getPort();
    }

    public void disconnect(){
        this.socket.disconnect();
    }


    @Override
    public void run() {

      String rec = "";
		System.out.println("ready to receive");

		while(true){
            try {
                rec = socket.receive(MAXBYTES);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(rec != null && rec.length() > 0)	{
				if(rec.charAt(0) == (char) 4){
					try{
						System.out.println("End of Transmission");
						break;
					}
					catch (Exception e){
						e.printStackTrace();
					}
				}
				else{
					System.out.println(rec);
				}
			}
			else{
				if(rec == null){ }
				else{
					System.out.println(rec);
				}
			}
		}

    }
}
