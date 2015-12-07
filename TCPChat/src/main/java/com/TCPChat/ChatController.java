package com.TCPChat;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import org.controlsfx.control.HiddenSidesPane;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void sendComment(ActionEvent actionEvent) {

    }

    public void neuerRaum(ActionEvent actionEvent) {
    }

    public void settings(ActionEvent actionEvent) {
    }

    public void settingsRoom(ActionEvent actionEvent) {
    }
}
