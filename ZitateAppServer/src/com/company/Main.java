package com.company;

import org.apache.commons.lang.StringEscapeUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Main {

    private static final int BUFSIZE=508;

    public static void main(String[] args) {
        if (args.length!=1){
            System.err.println("Bitte geben sie eine Port an danke!");
        }

        int port = Integer.parseInt(args[0]);
        startServer(port);

    }

    /**
     * Startet den Server und holt sich die Zitate
     * Startet den Server und  holt sich die Zitate vom Author den angegeben ist.
     * Wenn kein autor mit geschickt wurde wird ein ranom autor gewählt.
     * @param port  int Die portnummer auf dem der Server lauscht.
     */
    public static void startServer(int port){
        try (final DatagramSocket socket = new DatagramSocket(port)){
            DatagramPacket packetIn = new DatagramPacket(new byte[BUFSIZE],BUFSIZE);
            final DatagramPacket packetOut = new DatagramPacket(new byte[BUFSIZE],BUFSIZE);
            List<String> zitate;

            while (true){
                //Hole eingabe
                socket.receive(packetIn);
                if(packetIn.getLength()!=0){
                    String search = new String(packetIn.getData());
                    zitate=getZitate(search,false);
                }else{
                    zitate=getZitate("",true);
                }
                zitate.forEach(r-> {
                    packetOut.setData(r.getBytes());
                    try {
                        socket.send(packetOut);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Holt Zitate
     * Holt sich die Zitate unter der angegebenen URL und fügt diese einer Liste hinzu
     * Diese wird dann zurückgegeben
     * @param url       Die URL die durchsucht wird
     * @param zitate    Liste in denen die Zitate gespeichert werden
     * @return          Eine Liste an Zitaten
     */
    public static void addZitate(URL url,List<String> zitate){
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))){
            String input;
            StringBuilder builder = new StringBuilder();
            while ((input=reader.readLine())!=null){
                if (input.contains("<blockqute>")){
                    while (!(input=reader.readLine()).contains("<small>")){
                        builder.append(cutHtml(input.replaceAll("\\t","")));
                    }
                    zitate.add(builder.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    /**
     * Gibt die Zitate aus
     * Holt sich die Zitate des Autor von der Seite Zitate.de
     * Wenn der Autor nicht gefunden wurde wird ein Error an der Ersten Liste geschrieben
     * @param search    Der autor der zu suchen ist
     * @return          Eine Liste an Zitaten von diesem Autor, wenn Autor nicht vorhanden ein Error an erster Stelle
     *                  der Liste
     */
    public static List<String> getZitate(String search,boolean random){
        List<String> zitate = new LinkedList<>();
        try {
            URL url = new URL("http://www.zitate.de/autoren");
            /**
             * Zwischen speicher für String operationen.
             */
            String string;
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            //Wenn random nicht gefordert mache normal weiter
            if (!random) {
                while (!(reader.readLine()).contains("fragment-"+search.charAt(0)));
                while ((string=reader.readLine())!=null){
                    if(string.contains(search)&&string.contains("/autor/")){
                        //TODO code verdoppelung unterbinden
                        String autorURL = string.substring(string.indexOf("/"),string.indexOf("\""));
                        String autor = string.substring(string.lastIndexOf("\"")+2,string.lastIndexOf("<")-4);
                        url = new URL("http://www.zitate.de"+autorURL);
                        addZitate(url,zitate);
                        zitate.add(autor);
                    }
                }
            }else{
                int randomNumber = new Random().nextInt(501);
                //Nummer zum hochzählen
                int i=0;
                while (((string=reader.readLine()) !=null)){
                    if(string.contains("/autor/")){
                        i++;
                    }
                    if(i==randomNumber){
                        //TODO ausgliedern dieses if abschnittes
                        String autorURL = string.substring(string.indexOf("/"),string.indexOf("\""));
                        String autor = string.substring(string.lastIndexOf("\"")+2,string.lastIndexOf("<")-4);
                        url = new URL("http://www.zitate.de"+autorURL);
                        addZitate(url,zitate);
                        zitate.add(autor);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(zitate.isEmpty()){
            zitate.add("Nicht gefunden");
            return zitate;
        }
        return zitate;
    }

    /**
     * HTML encoding
     *
     * Die funktion nimmt einen String und schmeißt alle HTML encodings raus sprich aus &#039; wird ein '
     * Auch werden HTML elemente wie {@code <p>} rausgeschnitten
     * @param string    Der String der encoded werden soll
     * @return          Den Original String nur mit Unicode
     */
     public static String cutHtml(String string){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i)=='<'){
                while (string.charAt(i)!='>'){
                    i++;
                }
            }else if(string.charAt(i)=='&'){
                StringBuilder htmlEscape = new StringBuilder();
                while (string.charAt(i-1)!=';'){
                    htmlEscape.append(string.charAt(i++));
                }
                i--;
                //Encode HTML Unicode in java Unicode
                builder.append(StringEscapeUtils.unescapeHtml(htmlEscape.toString()));

            }else builder.append(string.charAt(i));

        }
        return builder.toString();
    }

}
