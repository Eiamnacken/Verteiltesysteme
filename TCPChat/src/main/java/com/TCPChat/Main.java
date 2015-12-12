package com.TCPChat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage)  {
        Pane root;
        try {
            root = FXMLLoader.load(getClass().getResource("/Chat.fxml"));
            stage.setTitle("Chat");
            stage.setScene(new Scene(root,600 ,400));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
