import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by sven on 26.01.16.
 */
public interface ReceiverCallback extends Remote {
    void callback(String data) throws RemoteException;
}
