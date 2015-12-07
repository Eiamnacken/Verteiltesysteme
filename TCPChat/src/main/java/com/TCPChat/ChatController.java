package com.TCPChat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.HiddenSidesPane;
import org.controlsfx.control.PopOver;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by sven on 07.12.15.
 */
public class ChatController implements Initializable{
    public HiddenSidesPane hiddenPane;
    public Tab mainTab;
    public TextField hauptChat;
    public Button send;
    public ListView<Label> userList;
    private PopOver popOver = new PopOver();
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

    }


    public void sendComment(ActionEvent actionEvent) {
    }

    public void neuerRaum(ActionEvent actionEvent) {
    }

    public void settings(ActionEvent actionEvent) {
    }

    public void settingsRoom(ActionEvent actionEvent) {
    }

    public void selectedUser(Event event) {
        popOver.hide();
        popOver.setContentNode(anchorPane);
        popOver.show(userList.getSelectionModel().getSelectedItem());
    }
}
