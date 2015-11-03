package dialogs;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

/**
 * Created by sven on 03.11.15.
 */
public class LoginOpen {

    private String port;

    public void launch() {
        TextInputDialog dialog = new TextInputDialog("9000");
        dialog.setTitle("Port abfrage");
        dialog.setHeaderText("Bitte gib deinen Port an");
        dialog.setContentText("Hier den Port eintragen:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            this.port = dialog.getResult();
        }
    }

    public String getPort(){
        return this.port;
    }
}
