import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by sven on 26.01.16.
 */
public class MainServer {


    public static void main(String []args){
        try {
            ReceiverImpl receiver = new ReceiverImpl();
            Receiver stub = (Receiver) UnicastRemoteObject.exportObject(receiver,0);
            Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            registry.rebind("FileServer",stub);
            System.out.println("Waiting");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
