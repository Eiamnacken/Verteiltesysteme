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
        //TODO file speicher mit filechooser
    }

    @FXML
    private void openConnection(ActionEvent actionEvent) {
        Login login = new Login();
        login.launch();
        //TODO Neuen Host erstellen


    }

    @FXML
    private void privateConnection(ActionEvent actionEvent) {
        //TODO Alle user rauschmeßen bis auf einen listView updaten
    }

    @FXML
    private void kickUser(ActionEvent actionEvent) {
        Kick kick = new Kick();
        //TODO Einen User rausschmeßen listView Updten
    }

    @FXML
    private void sendText(Event event) {
        //TODO Den Text an alle User senden
    }

    @Override @FXML
    public void initialize(URL location, ResourceBundle resources) {
    }
}
