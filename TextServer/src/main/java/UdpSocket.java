import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by sven on 02.11.15.
 * Erstellt verbindungen mit dem UDP protokoll
 */
public class UdpSocket {

    /**
     * Port der freigegeben wird
     */
    private int port;

    /**
     * Host mit dem verbunden wird
     */
    private InetAddress host;
    /**
     * Socket für die verbindung
     */
    private DatagramSocket socket;

    /**
     * Erstellt UDPSocket für eine direkte verbindung
     * @param port                  Port an den zu senden buw. zu lauschen ist
     * @param host                  Adresse an die wir direkt senden wollen
     * @throws SocketException
     */
    public UdpSocket(int port, InetAddress host) throws SocketException {
        this.port = port;
        this.host = host;
        this.socket= new DatagramSocket(this.port);
    }

    /**
     * Erstellt einen UDPSocket für eine nicht direkte verbindung
     * @param port              Port an dem wir lauschen wollen.
     * @throws SocketException
     */
    public UdpSocket(int port) throws SocketException {
        this.port = port;
        this.socket= new DatagramSocket();
    }

    /**
     * Sendet einen String
     *
     * Sendet einen String an die vorher angegebene Adresse und Port
     * @param s                 String der zu senden ist
     * @throws IOException
     */
    public void send(String s) throws IOException {
        DatagramPacket packet = new DatagramPacket(s.getBytes(),s.length());
        packet.setAddress(this.host);
        packet.setPort(this.port);
        this.socket.send(packet);
    }

    /**
     * Erhält packete und gibt diese als String zurück
     * @param maxBytes      Die größe des zu empfangenen Packets
     * @return              Eine String representation des Packetsinhalt
     * @throws IOException
     */
    public String receive(int maxBytes) throws IOException {
        DatagramPacket packet = new DatagramPacket(new byte[maxBytes],maxBytes);
        packet.setAddress(this.host);
        packet.setPort(this.port);
        this.socket.setSoTimeout(500);
        this.socket.receive(packet);
        this.host=packet.getAddress();
        return new String(packet.getData());
    }
}
