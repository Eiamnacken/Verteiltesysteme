import java.rmi.RemoteException;

public class ReceiverImpl  implements Receiver {


    private ReceiverCallback callback;

    public ReceiverImpl() {
    }



    public void register(ReceiverCallback callback) throws RemoteException {
        this.callback = callback;
    }

    @Override
    public void send(String message) throws RemoteException {
        callback.callback(message);
    }

}
