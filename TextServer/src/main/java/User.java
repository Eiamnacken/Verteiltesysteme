import java.net.InetAddress;

/**
 * Created by sven on 03.11.15.
 */
public class User extends Thread{

    private InetAddress address;
    private int port;
    private Receiver receiver;
    private Transmitter transmitter;
    private String name;

    public User(int port, InetAddress address) {
        this.port = port;
        this.address = address;

    }

    public User(String name, int port, InetAddress address) {
        this.name = name;
        this.port = port;
        this.address = address;
    }

    public void disconnect(){

    }




}
