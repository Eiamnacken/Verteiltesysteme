import dialogs.Kick;
import dialogs.Login;
import dialogs.LoginOpen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by sven on 02.11.15.
 */
public class Controller implements Initializable {

    public TextArea displayContent;

    public ListView<String> userList;

    private Host host;

    private void updateText(String s) {
        displayContent.setText(s);
    }

    private void updateUserList() {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (User u : host.getViewer()) {
            list.add(u.toString());
        }
    }

    private int getUserListSize() {
        return userList.getItems().size();
    }


    public void saveFile(ActionEvent actionEvent) {
        //TODO file speicher mit filechooser
    }


    public void openConnection(ActionEvent actionEvent) {
        LoginOpen loginOpen = new LoginOpen();
        loginOpen.launch();
        try {
            host = new Host(Integer.parseInt(loginOpen.getPort()));
            Thread t = new Thread(){
                public void run(){
                    runLogic();
                }
            };
        } catch (SocketException e) {
            e.printStackTrace();
        }


    }

    private void runLogic() {
        while (true) {
            host.run();
            if (host.isReceived()) {
                updateText(host.getBuffer());
            }
            if (host.getViewer().size() != getUserListSize()) {
                updateUserList();
            }
        }

    }


    public void privateConnection(ActionEvent actionEvent) {
        //TODO Alle user rauschmeßen bis auf einen listView updaten
    }


    public void kickUser(ActionEvent actionEvent) {
        Kick kick = new Kick();
        String user = kick.getUser();
        host.kickUser(user);
    }


    /**
     * Sendet den Text an alle User
     * @param event
     */
    public void sendText(Event event) {
        host.update(displayContent.getText());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Eröffnet die Verbindung zu einem Host
     * @param actionEvent   Bei menü druck
     */
    public void openConenctionTo(ActionEvent actionEvent) {
        Login login = new Login();
        login.launch();
        try {
            int port = Integer.parseInt(login.getPort());
            host = new Host(port);
            host.addUser(new User("Hoster",port,login.getHost()));
            Thread t = new Thread(){
              public void run(){
                  runLogic();
              }
            };
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }
}
