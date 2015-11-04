import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sven on 03.11.15.
 */
public class Host  {

    /**
     * Liste der Verbundenen User
     */
    private List<User> viewer;
    /**
     *Erhält die Daten
     */
    Receiver receiver;

    /**
     * Gibt an ob etwas empfangen wurde
     */
    private boolean received=false;

    /**
     * Zwischenspeicher für den neu enthaltenden String
     */
    private String buffer;


    public Host(int port) throws SocketException {
        this.viewer = new LinkedList<>();
        receiver = new Receiver(new UdpSocket(port));
    }
    /**
     * Beendet die verbindung mit einem User und schmeißt diesen aus der Liste
     * @param address   Adresse oder namen des Users
     * @return          True wenn der User gelöscht werden konnte false wenn er nicht gefunden werden konnte
     */
    public boolean kickUser(String address){
        for (User u : viewer){
            if (u.getAddress().equals(address)||u.getNameUser().equals(address)){
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
    public ObservableList<User> getViewer(){
        ObservableList<User> list = FXCollections.observableList(viewer);
        return list;
    }

    /**
     * Erhält einen String
     * @return  Einen String
     */
    public synchronized void receive(){
        try {
            buffer=receiver.receive();
            received=true;
        } catch (IOException e) {
            e.printStackTrace();
            received=false;
        }
    }

    /**
     * Schickt einen String an alle User
     * @param s     String der zu schicken ist
     */
    public void update(String s){
        for (User u : viewer){
            try {
                u.send(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Fügt einen User dem Host hinzu
     * @param u     User der hinzugefügt wird
     */
    public void addUser(User u){
        viewer.add(u);
    }


    public void run() {
        receive();
        boolean newUser=true;
        if (received){
            for (User u: viewer){
                if (u.getAddress().equals(receiver.getAdress())){
                    newUser=false;
                }
            }
            if (newUser){
                try {
                    addUser(new User(receiver.getPort(),receiver.getAdress()));
                } catch (SocketException e) {
                } catch (UnknownHostException e) {
                    System.out.println("Konnte user nicht hinzufügen");
                }
            }
        }

    }

    public String getBuffer() {
        return buffer;
    }

    public boolean isReceived() {
        return received;
    }
}
