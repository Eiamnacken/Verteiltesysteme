import dialogs.Kick;
import dialogs.Login;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by sven on 02.11.15.
 */
public class Controller implements Initializable{
    @FXML
    private TextArea displayContent;
    @FXML
    private ListView userList;

    private Host host;

    @FXML
    private void saveFile(ActionEvent actionEvent) {

    }

    @FXML
    private void openConnection(ActionEvent actionEvent) {
        Login login = new Login();
        login.launch();


    }

    @FXML
    private void privateConnection(ActionEvent actionEvent) {
    }

    @FXML
    private void kickUser(ActionEvent actionEvent) {
        Kick kick = new Kick();
    }

    @FXML
    private void sendText(Event event) {

    }

    @Override @FXML
    public void initialize(URL location, ResourceBundle resources) {
        this.host = new Host();
    }
}
