package com.TCPChat.controller;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

/**
 * Created by sven on 08.12.15.
 */
public class Login {
    protected Optional<String> userName;
    protected  Optional<String> adress;
    protected  Optional<String> port;
    private Dialog<String> dialog;

    public Login(){

    }

    public void showDialog(){
        dialog = new Dialog<>();
        dialog.setTitle("Login");
        dialog.setHeaderText("Bitte geben sie ihren namen ein und den Server zu dem sie verbinden wollen");
        ButtonType buttonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(buttonType,ButtonType.CANCEL);

        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(20,150,10,10));

        TextField name = new TextField();
        name.setPromptText("Username");
        TextField adressField = new TextField();
        TextField portField = new TextField();

        pane.add(new Label("Username:"),0,0);
        pane.add(new Label("Adresse:"),0,1);
        pane.add(new Label("Port:"),0,2);
        pane.add(name,1,0);
        pane.add(adressField,1,1);
        pane.add(portField,1,2);

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
                this.userName = Optional.of(name.getText());
                this.adress = Optional.of(adressField.getText());
                this.port = Optional.of(portField.getText());
            }
            return null;
        });


        dialog.showAndWait();
    }



}
