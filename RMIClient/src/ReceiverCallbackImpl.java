import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;

/**
 * Created by sven on 26.01.16.
 */
public class ReceiverCallbackImpl implements ReceiverCallback{

    private Path dir;

    public ReceiverCallbackImpl() throws RemoteException{
    }

    @Override
    public void callback(String data) throws RemoteException {
        if (data.trim().equals("look up")){
            if(dir==null){ dir= Paths.get("");}
            System.out.println(dir.toAbsolutePath().toString());
        }else if(data.trim().equals("show")){
            try {
                Files.walk(Paths.get(dir.toAbsolutePath().toString()))
                        .filter(Files::isRegularFile)
                        .forEach(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (data.contains("switch")){
            dir = Paths.get(dir.toAbsolutePath().toString()+ File.separator+ data.substring(data.indexOf(" "),data.length()));
            System.out.println(dir.toAbsolutePath().toString());
        }else if(data.contains("go")){
            dir = Paths.get(data.trim().substring(data.indexOf("/"),data.length()));
            System.out.println(dir.toAbsolutePath().toString());
        }
    }
}
