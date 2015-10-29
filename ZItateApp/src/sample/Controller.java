package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    public TabPane tabsPane;
    private DatagramSocket socket;
    private static final int port = 9000;
    private static final String address = "Localhost";
    private static final int BUFSIZE=1024;
    public TextField searchField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void searchFieldPressed(KeyEvent event) {
        if (event.getCode()== KeyCode.ENTER) search();
    }



    public void searchZitate(ActionEvent actionEvent) {
        search();
        receive();
    }
    private void search(){
        try {
            InetAddress inet = InetAddress.getByName(address);
            String search = searchField.getText();
            DatagramPacket packetOut;
            if (!searchField.getText().isEmpty()) {
                packetOut = new DatagramPacket(search.getBytes(),search.length(),inet,port);

            }else packetOut= new DatagramPacket("0".getBytes(),1,inet,port);
            this.socket.send(packetOut);
            InetAddress inetAddress = InetAddress.getByName("10.0.1.29");
            packetOut = new DatagramPacket(new byte[0],0,inetAddress,port);
            socket.send(packetOut);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void receive(){
        List<String> zitate = new LinkedList<>();
        List<String> zitateAndere = new LinkedList<>();

            while (true){
                try {
                    DatagramPacket packetIn = new DatagramPacket(new byte[BUFSIZE],BUFSIZE);
                    this.socket.receive(packetIn);
                    this.socket.setSoTimeout(5000);
                    System.out.println(new String(packetIn.getData()));
                    if (packetIn.getLength()!=0) {
                        String s = new String(packetIn.getData());
                        zitate.add(s);
                    }else break;
                    DatagramSocket socket1 = new DatagramSocket();
                    InetAddress inetAddress = InetAddress.getByName("10.0.1.29");
                    packetIn = new DatagramPacket(new byte[BUFSIZE],BUFSIZE,inetAddress,port);
                    socket1.receive(packetIn);
                    if (packetIn.getLength()!=0){
                        zitateAndere.add(new String(packetIn.getData()));
                    }


                } catch (SocketException e) {
                    System.err.println(e.getMessage());
                } catch (IOException e) {
                    break;
                }
            }
            for (int j = 0; j < zitate.size()-1; j++) {
                Tab tab = new Tab();
                tab.setText("Zitat"+(j+1));
                tab.setContent(new TextArea(zitate.get(j)));
                tabsPane.getTabs().add(tab);
            }
            searchField.setText("");
            System.out.println(zitate.size());
            searchField.setText(zitate.get(zitate.size()-1));
        for (String s : zitateAndere){
            System.out.println(s);
        }
    }
}
