package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Controller {
    public Button go;
    public TextField port;
    public TextField host;
    public TextArea ausgabe;
    private static final int BUFSIZE=508;


    public void verbinde(ActionEvent actionEvent) {
        int number = Integer.parseInt(port.getText());
        String ip = host.getText();
        try (DatagramSocket socket = new DatagramSocket()){
            byte[] buffer = new byte[1];
            InetAddress address = InetAddress.getByName(ip);
            DatagramPacket packetOut = new DatagramPacket(buffer,buffer.length,address,number);
            packetOut.setData("Hallo an server".getBytes());
            socket.send(packetOut);
            DatagramPacket packetIn = new DatagramPacket(new byte[BUFSIZE],BUFSIZE);
            socket.receive(packetIn);
            String received = new String(packetIn.getData());
            ausgabe.setText(received);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
