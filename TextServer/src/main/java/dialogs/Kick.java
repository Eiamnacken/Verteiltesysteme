package dialogs;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

/**
 * Created by sven on 02.11.15.
 */
public class Kick {
    private String user;

    public void launch() {
        TextInputDialog dialog = new TextInputDialog("horst");
        dialog.setTitle("User kicken");
        dialog.setHeaderText("Welcher User soll gekickt werden?");
        dialog.setContentText("User namen:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            this.user = dialog.getResult();
        }
    }

    public String getUser(){
        return this.user;
    }
}
