import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Transmitter implements Runnable {


    public Transmitter() {

    }

    @Override
    public void run() {
        try {
            ReceiverCallback receiver = new ReceiverCallbackImpl();
            ReceiverCallback stub = (ReceiverCallback) UnicastRemoteObject.exportObject(receiver, 0);
            Receiver receiver1 = (Receiver) Naming.lookup("FileServer");
            receiver1.register(stub);
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){
                receiver1.send(scanner.nextLine());
            }
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

}