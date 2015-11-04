import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by sven on 03.11.15.
 */
public class Main extends Application{

    Stage primary;
    Parent root;

    public static void main(String args[]){
       launch(args);
    }

    @Override
    public void start(Stage prim) throws Exception {
        root = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        prim.setTitle("TextServer");
        prim.setScene(new Scene(root));
        primary=prim;
        primary.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
