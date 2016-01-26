import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Receiver extends Remote {
    void register(ReceiverCallback callback) throws RemoteException;

    void send(String message) throws RemoteException;
}
