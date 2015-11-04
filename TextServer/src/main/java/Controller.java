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
import java.net.UnknownHostException;
import java.util.ResourceBundle;

/**
 * Created by sven on 02.11.15.
 */
public class Controller implements Initializable {

    public TextArea displayContent;

    public ListView<String> userList;

    private Host host;

    private boolean threadsStop=false;

    /**
     * Update der GUI um den Tex darzustellen
     * @param s Text der dargestellt werden soll
     */
    private void updateText(String s) {
        displayContent.setText(s);
    }

    /**
     * Update der GUI um alle verbundenen User anzuzeigen
     */
    private void updateUserList() {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (User u : host.getViewer()) {
            list.add(u.toString());
        }
        userList = new ListView<>(list);
    }

    /**
     * Anzahl der User in der User liste
     * @return  Größe der Liste
     */
    private int getUserListSize() {
        return userList.getItems().size();
    }


    /**
     * Speicherst das gerade geschriebene
     * @param actionEvent
     */
    public void saveFile(ActionEvent actionEvent) {
        //TODO file speicher mit filechooser
    }


    /**
     * Öffnet einen TextServer auf diesem Rechner
     * @param actionEvent
     */
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
            t.start();
        } catch (SocketException e) {
            e.printStackTrace();
        }


    }

    /**
     * Stop alle Threads
     * @param threadsStop   True für stoppen
     */
    public void setThreadsStop(boolean threadsStop) {
        this.threadsStop = threadsStop;
    }

    /**
     * fragt die eingehenden Packete ab und updatet die GUI entsprechend
     * Fügt neue User hinzu
     */
    private void runLogic() {
        while (!threadsStop) {
            host.run();
            if (host.isReceived()) {
                updateText(host.getBuffer());
            }
            if (host.getViewer().size() != getUserListSize()) {
                updateUserList();
            }
        }

    }


    /**
     * öffnet eine private verbindung mit nur einem teilnehmer
     * @param actionEvent
     */
    public void privateConnection(ActionEvent actionEvent) {
        //TODO für zusatzaufgabe
        //TODO Alle user rauschmeßen bis auf einen listView updaten
    }


    /**
     * Nimt einen User aus der Liste nimmt ihn ofline
     * @param actionEvent
     */
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
        Thread t = new Thread(){
          public void run(){
              host.update(displayContent.getText());
          }
        };
        t.start();

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
            try {
                host.addUser(new User("Hoster", port, login.getHost()));
                Thread t = new Thread(){
                    public void run(){
                        runLogic();
                    }
                };
                t.start();
            } catch (SocketException e) {
                e.printStackTrace();
            }
            } catch (UnknownHostException | SocketException e) {
                e.printStackTrace();
            }


    }

}
