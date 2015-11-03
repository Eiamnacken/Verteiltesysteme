import java.io.IOException;

/**
 * Created by sven on 02.11.15.
 */
public class Transmitter {

    /**
     * Socket mit dem Verbunden wird
     */
    UdpSocket socket;

    /**
     * Erstellt einen Transmitter
     * @param socket
     * @see #socket
     */
    public Transmitter(UdpSocket socket) {
        this.socket=socket;
    }

    /**
     * Sendet eine Packet an einen Receiver
     * @param s
     * @throws IOException
     */
    public void send(String s) throws IOException {
        if (s.isEmpty()) return;
        if (s.equals("")) return;
        else this.socket.send(s);
    }
}
