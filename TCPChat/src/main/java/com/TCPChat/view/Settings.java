package com.TCPChat.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

/**
 * Created by sven on 08.12.15.
 */
public class Settings {
    protected Optional<String> localPort;

    public Settings() {

    }

    public void showDialog(){
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Einstellungen");
        ButtonType buttonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(buttonType,ButtonType.CANCEL);

        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(20,150,10,10));

        TextField name = new TextField();
        name.setPromptText("Port");

        pane.add(new Label("Port:"),0,0);
        pane.add(name,1,0);

        Node loginButton = dialog.getDialogPane().lookupButton(buttonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        name.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(pane);

        javafx.application.Platform.runLater(name::requestFocus);

        dialog.setResultConverter(dialogButton->{
            if(dialogButton==buttonType){
                return name.getText();
            }
            return null;
        });

        localPort= dialog.showAndWait();
    }
}
