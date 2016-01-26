/**
 * Created by sven on 26.01.16.
 */
public class Main {
    public static void main(String[] args) {
        Transmitter transmitter = new Transmitter();
        Thread t = new Thread(transmitter);
        transmitter.run();

    }
}
