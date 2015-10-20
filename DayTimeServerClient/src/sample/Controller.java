package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDateTime;

public class Controller {
    public Button go;
    public TextField port;
    public TextField host;
    public TextArea ausgabe;
    private static final int BUFSIZE=508;
    private int portNumber;


    public void verbinde(ActionEvent actionEvent) {
        try{
            if(port.getText().contains("[a-zA-Z]+")) throw new Exception();
            portNumber=Integer.parseInt(port.getText());

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Eingabe Fehler");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        String ip = host.getText();
        try (DatagramSocket socket = new DatagramSocket()){
            byte[] buffer = new byte[1];
            InetAddress address = InetAddress.getByName(ip);
            DatagramPacket packetOut = new DatagramPacket(buffer,buffer.length,address,portNumber);
            packetOut.setData("Hallo an server".getBytes());
            LocalDateTime time = LocalDateTime.now();
            socket.send(packetOut);
            DatagramPacket packetIn = new DatagramPacket(new byte[BUFSIZE],BUFSIZE);
            socket.receive(packetIn);
            String received = new String(packetIn.getData());
            ausgabe.appendText(time.toString()+"\n");
            ausgabe.appendText(received + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
