package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javax.swing.*;

public class Controller {
    public TextField searchBar;
    public TextArea zitatText;

    public void searchFieldPressed(KeyEvent event) {
        if (event.getCode()== KeyCode.ENTER) search();
    }

    public void searchZitate(ActionEvent actionEvent) {
        search();
    }

    private void search(){


    }
}
