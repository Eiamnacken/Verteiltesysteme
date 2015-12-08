package com.TCPChat.view;

import com.TCPChat.chat.ChatEvent;
import com.TCPChat.chat.IView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.HiddenSidesPane;
import org.controlsfx.control.PopOver;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by sven on 07.12.15.
 */
public class ChatController implements Initializable, IView{
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
    @FXML
    private TextArea mainRoomText;

    private PopOver popOver = new PopOver();
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO entferne standard user einträge
        Label [] names = new Label[3];
        names[1]=new Label("Hanna");
        names[2]=new Label("Stefan");
        names[0]=new Label("Markus");
        ObservableList<Label> items = FXCollections.observableArrayList(names);
        userList.setItems(items);
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
        //TODO Senden des textes
    }

    public void neuerRaum(ActionEvent actionEvent) {
        //TODO neuen raum anlegen
    }

    public void settings(ActionEvent actionEvent) {
        //TODO eintellungen erlauben
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
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Login");
        dialog.setHeaderText("Bitte geben sie ihren namen ein");
        ButtonType buttonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(buttonType,ButtonType.CANCEL);

        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(20,150,10,10));

        TextField username = new TextField();
        username.setPromptText("Username");

        pane.add(new Label("Username:"),0,0);
        pane.add(username,1,0);

        Node loginButton = dialog.getDialogPane().lookupButton(buttonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(pane);

        javafx.application.Platform.runLater(username::requestFocus);

        dialog.setResultConverter(dialogButton->{
            if(dialogButton==buttonType){
                return username.getText();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();
        //TODO User anlegen als Host name in der Liste eintragen
    }

    private void updateUserlist(List users){
        //TODO die liste an usern updaten
    }

    @Override
    public void update(ChatEvent evt) throws Exception {
        switch (evt.getEventType()) {
            case ChatEvent.LIST_UPDATE:
                //TODO Userlist updaten
                updateUserlist(new LinkedList<>());
                break;
            case ChatEvent.COMMENT:
                //TODO abhängig vom raum machen
                mainRoomText.setText(evt.getComment()+"\n");
                break;
        }
    }
}
