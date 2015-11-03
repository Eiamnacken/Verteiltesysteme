import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by sven on 03.11.15.
 */
public class Main extends Application{

    public static void main(String args[]){
       launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        primaryStage.setTitle("TextServer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
