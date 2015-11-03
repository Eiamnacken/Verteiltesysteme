import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sven on 03.11.15.
 */
public class Host  extends Thread{

    /**
     * Liste der Verbundenen User
     */
    private List<User> viewer;
    /**
     *Erhält die Daten
     */
    Receiver receiver;


    public Host(int port) throws SocketException {
        this.viewer = new LinkedList<>();
        receiver = new Receiver(new UdpSocket(port));
    }

    /**
     * Beendet die verbindung mit einem User und schmeißt diesen aus der Liste
     * @param address   Adresse des Users
     * @return          True wenn der User gelöscht werden konnte false wenn er nicht gefunden werden konnte
     */
    public boolean kickUser(String address){
        for (User u : viewer){
            if (u.getAddress().equals(address)){
                u.disconnect();
                viewer.remove(viewer.indexOf(u));
                return true;
            }
        }
        return false;
    }

    /**
     * Gibt alle User aus die mit dem Host verbunden sind
     * @return  Eine Liste der User
     */
    public List<User> getViewer(){
        return viewer;
    }

    /**
     * Fügt einen User dem Host hinzu
     * @param u     User der hinzugefügt wird
     */
    private void addUser(User u){
        viewer.add(u);
    }
}
