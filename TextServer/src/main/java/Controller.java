import dialogs.Kick;
import dialogs.LoginOpen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by sven on 02.11.15.
 */
public class Controller implements Initializable{
    @FXML
    private TextArea displayContent;
    @FXML
    private ListView<String> userList;

    private Host host;

    private void updateText(String s){
        displayContent.setText(s);
    }

    private void updateUserList(){
        ObservableList<String> list = FXCollections.observableArrayList();
       for (User u : host.getViewer()){
           list.add(u.toString());
       }
    }

    private int getUserListSize(){
        return userList.getItems().size();
    }


    @FXML
    private void saveFile(ActionEvent actionEvent) {
        //TODO file speicher mit filechooser
    }

    @FXML
    private void openConnection(ActionEvent actionEvent) {
        LoginOpen loginOpen = new LoginOpen();
        loginOpen.launch();
        try {
            host = new Host(Integer.parseInt(loginOpen.getPort()));
        } catch (SocketException e) {
            e.printStackTrace();
        }
        Thread t = new Thread(){
            public void run(){
                while (true){
                    host.run();
                    if (host.isReceived()){
                        updateText(host.getBuffer());
                    }
                    if (host.getViewer().size()!= getUserListSize()){
                        updateUserList();
                    }
                }

            }
        };


    }

    @FXML
    private void privateConnection(ActionEvent actionEvent) {
        //TODO Alle user rauschmeßen bis auf einen listView updaten
    }

    @FXML
    private void kickUser(ActionEvent actionEvent) {
        Kick kick = new Kick();
        String user = kick.getUser();
        host.kickUser(user);
    }

    @FXML
    private void sendText(Event event) {
        host.update(displayContent.getText());

    }

    @Override @FXML
    public void initialize(URL location, ResourceBundle resources) {

    }
}
