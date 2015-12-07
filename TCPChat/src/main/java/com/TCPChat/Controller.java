package com.TCPChat;

import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.HiddenSidesPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by sven on 07.12.15.
 */
public class Controller implements Initializable{
    public HiddenSidesPane hiddenPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VBox vBox = new VBox();
        AnchorPane pane = new AnchorPane();
        TabPane tabPane = new TabPane();
        tabPane.setPrefWidth(489);
        tabPane.setPrefHeight(365);
        pane.setPrefWidth(200);
        pane.setPrefHeight(400);
        vBox.setPrefHeight(400);
        vBox.setPrefWidth(200);
        pane.getChildren().addAll(vBox);
        this.hiddenPane.setLeft(pane);
        this.hiddenPane.setContent(tabPane);
        this.hiddenPane.setPinnedSide(Side.LEFT);
        this.hiddenPane.setVisible(true);

    }
}
