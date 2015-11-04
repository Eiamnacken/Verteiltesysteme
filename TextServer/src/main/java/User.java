import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by sven on 03.11.15.
 */
public class User {

    private String address;
    private int port;
    private Transmitter transmitter;
    private String name;

    public User(int port, String address) throws SocketException, UnknownHostException {
        this.port = port;
        this.address = address;
        this.transmitter= new Transmitter(new UdpSocket(this.port,this.address));


    }

    public User(String name, int port, String address) throws SocketException, UnknownHostException {
        this(port,address);
        this.name = name;
    }

    public void disconnect(){
        this.transmitter.disconnect();
    }

    public void send(String s) throws IOException {
        if (s.isEmpty()) return;
        this.transmitter.send(s);
    }



    public String getNameUser() {
        if (this.name!=null){
            return this.name;
        }
        return "";
    }

    public String getAddress() {
        return address;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(this.address.length());
        if (this.name!=null){
            builder.append(this.name);
            return builder.toString();
        }else builder.append(this.address);
        return builder.toString();
    }
}
