package com.TCPChat.view;

import com.TCPChat.chat.ChatEvent;
import com.TCPChat.chat.IView;
import com.TCPChat.chat.cs.IChat;
import com.TCPChat.chat.cs.sockets.ServerProxy;
import com.TCPChat.model.Raum;
import com.TCPChat.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.HiddenSidesPane;
import org.controlsfx.control.PopOver;

import java.net.Socket;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sven on 07.12.15.
 */
public class ChatController implements Initializable, IView{
    @FXML
    private TabPane roomGUI;
    @FXML
    private HiddenSidesPane hiddenPane;
    @FXML
    private Tab mainTab;
    @FXML
    private TextField commentField;
    @FXML
    private Button send;
    @FXML
    private ListView<Label> userList;

    private Map<String,Raum> rooms;
    private PopOver popOver = new PopOver();
    private AnchorPane anchorPane;
    private Optional<IChat>serverProxy;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO entferne standard user einträge
        rooms = new HashMap<>();
        rooms.put("Hauptraum",new Raum("Hauptraum"));
        mainTab.setText(rooms.get("Hauptraum").toString());
        anchorPane = new AnchorPane();
        anchorPane.setPrefSize(40,50);
        final Button setPrivate = new Button("Privates Gespräch");
        anchorPane.getChildren().addAll(setPrivate);
        setPrivate.setOnAction(this::setPrivateConnection);

    }

    private void setPrivateConnection(ActionEvent event){
        //TODO Neuen Raum mit einem Chat partner öffnen
        popOver.hide();
    }


    public void sendComment(ActionEvent actionEvent) {

    }

    public void newRoom(ActionEvent actionEvent) {
        //TODO neuen raum anlegen
    }

    public void settings(ActionEvent actionEvent) {
        Settings settings = new Settings();
        settings.showDialog();
        if (settings.localPort.isPresent()){
            //TODO setze verbindung
        }
    }

    public void settingsRoom(ActionEvent actionEvent) {
        //TODO passwort festlegen oder blacklist
    }

    public void selectedUser(Event event) {
        popOver.hide();
        popOver.setContentNode(anchorPane);
        popOver.setAutoFix(false);
        popOver.show(userList.getSelectionModel().getSelectedItem());
    }

    public void login(ActionEvent actionEvent) {
        Login login = new Login();
        login.showDialog();
        //TODO User anlegen als Host name in der Liste eintragen
        if (login.userName.isPresent()){
            User host = new User(login.userName.get());
            rooms.get("Hauptraum").addUser(host);
            try {
                Socket socket = new Socket(login.adress.get(),Integer.valueOf(login.port.get()));
                serverProxy = Optional.of(new ServerProxy(socket));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Update die Liste der user
     */
    private void updateUserlist(){
        List<User> list= rooms.get(roomGUI.getSelectionModel().getSelectedItem().getText()).getUser();
        ObservableList<Label> listUpdate = FXCollections.observableList(list.
                stream().
                map(u-> new Label(u.getUserName())).
                collect(Collectors.toList()));
        userList.setItems(listUpdate);

    }

    @Override
    public void update(ChatEvent evt) throws Exception {
        switch (evt.getEventType()) {
            case ChatEvent.LIST_UPDATE:
                //TODO Userlist updaten
                rooms.get()
                updateUserlist();
                break;
            case ChatEvent.COMMENT:
                //TODO abhängig vom raum machen
                rooms.get(roomGUI.getSelectionModel().
                        getSelectedItem().getText()).getRoomChat().setText(evt.getComment()+"\n");
                break;
        }
    }

    public void connect(ActionEvent actionEvent) {

    }

    public void changeRoom(Event event) {
    }

}
