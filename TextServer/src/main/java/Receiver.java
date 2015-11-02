/**
 * Created by sven on 02.11.15.
 */
public class Receiver {

    /**
     * Kümmert sich um die verbindung
     */
    private UdpSocket socket;

    public Receiver(UdpSocket socket) {
        this.socket = socket;
    }
}
