import dialogs.Kick;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by sven on 02.11.15.
 */
public class Controller implements Initializable{
    public TextArea displayContent;
    public ListView userList;

    public void saveFile(ActionEvent actionEvent) {

    }

    public void openConnection(ActionEvent actionEvent) {
    }

    public void privateConnection(ActionEvent actionEvent) {
    }

    public void kickUser(ActionEvent actionEvent) {
        Kick kick = new Kick();
    }

    public void sendText(Event event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
