import java.io.IOException;
import java.net.*;

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
     * Für eine direkte verbindung true wenn ja
     */
    private boolean connection=false;

    /**
     * Erstellt UDPSocket für eine direkte verbindung
     * @param port                  Port an den zu senden buw. zu lauschen ist
     * @param host                  Adresse an die wir direkt senden wollen
     * @throws SocketException
     */
    public UdpSocket(int port, String host) throws SocketException, UnknownHostException {
        this.port = port;
        this.host = InetAddress.getByName(host);
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
        if (!this.connection) {
            packet.setAddress(this.host);
            packet.setPort(this.port);
        }
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
        if (!this.connection) {
            packet.setAddress(this.host);
            packet.setPort(this.port);
        }
        this.socket.setSoTimeout(500);
        this.socket.receive(packet);
        this.host=packet.getAddress();
        return new String(packet.getData());
    }

    /**
     * Schließt die verbindung
     */
    public void disconnect(){
        this.socket.disconnect();
        this.socket.close();
    }

    /**
     * Stellt eine verbindung zu einem gewünschten host her und nur zu diesem
     * @param address   Addresse des hosts
     * @param port      Port des Hosts
     */
    public void connect(InetAddress address,int port){
        this.socket.connect(address,port);
        this.connection=true;
    }

}
