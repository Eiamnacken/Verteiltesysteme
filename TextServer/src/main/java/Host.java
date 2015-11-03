import java.util.LinkedList;
import java.util.List;

/**
 * Created by sven on 03.11.15.
 */
public class Host  extends Thread{

    private List<User> viewer;


    public Host() {
        this.viewer = new LinkedList<>();
    }

    public boolean kickUser(String address){

    }
}
