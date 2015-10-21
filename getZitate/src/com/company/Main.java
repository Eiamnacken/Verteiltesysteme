package com.company;

import com.google.gson.Gson;
import org.apache.commons.lang.StringEscapeUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * Die liste der Autoren
     */
    private static List<Autor> autoren;
    /**
     * Gibt an wie viele Zitate am ende Gespeichert werden sollen
     */
    private static int anzahlZitate;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Bitte geben sie an wie viele Zitate sie haben wollen");
        anzahlZitate = in.nextInt();
        autoren = new LinkedList<>();
        addAuthors();
        autoren.forEach(r -> System.out.println(r));
        Gson gson = new Gson();
        String json = gson.toJson(autoren);
        try {
            Path path = Paths.get("");
            String writeFile = path.toAbsolutePath().toString()+ File.separator+"zitate.json";
            FileWriter writer = new FileWriter(writeFile);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Fügt Zitate hinzu
     * Die funktion nimmt den Teilstring und fügt alle Zitate die bei diesem Autor hinterlegt sind dem Autor hinzu
     * Die funktion fügt so viele Zitate hinzu wie in {@link #anzahlZitate} angeben ist
     * @param url       URL des Autors
     * @param autor     Der Autor dem die Zitate hinzugefügt werden
     */
    static public void addZitate(URL url,Autor autor){
        try (BufferedReader reader = new BufferedReader( new InputStreamReader(url.openStream()));)
        {
            String input;
            String zitat="";
            while ((input=reader.readLine())!=null&&anzahlZitate!=0){
                if (input.contains("<blockquote>")){
                    while (!(input=reader.readLine()).contains("<small>")){
                        zitat += cutHtml(input);
                    }
                    autor.addZitat(zitat);
                    anzahlZitate--;
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * HTML encoding
     *
     * Die funktion nimmt einen String und schmeißt alle HTML encodings raus sprich aus &#039; wird ein '
     * Auch werden HTML elemente wie {@code <p>} rausgeschnitten
     * @param string    Der String der encoded werden soll
     * @return          Den Original String nur mit Unicode
     */
    static public String cutHtml(String string){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i)=='<'){
                while (string.charAt(i)!='>'){
                    i++;
                }
            }else if(string.charAt(i)=='&'&&string.charAt(i+1)=='#'){
                StringBuilder htmlEscape = new StringBuilder(7);
                for (int j = 0; j < 6; j++) {
                    htmlEscape.append(string.charAt(i+j));
                }
                i+=7;
                //Encode HTML Unicode in java Unicode
                builder.append(StringEscapeUtils.unescapeHtml(htmlEscape.toString()));

            }else builder.append(string.charAt(i));

        }
        return builder.toString();
    }

    /**
     * Fügt Autoren hinzu
     *
     * Diese funktion fügt alle Autoren hinzu die in der Liste der URL stehen hinzu zu der Liste {@link #autoren} hinzu
     * Funtkion hört auf sobald {@link #anzahlZitate} durch {@link #addZitate(URL, Autor)} auf 0 gesetzt wurde.
     */
    static public void addAuthors()  {

        try {

            URL url = new URL("http://www.zitate.de/autoren");
            String input;
            byte[] bytes;
            BufferedReader reader = new BufferedReader( new InputStreamReader(url.openStream()));
            while ((input= new String(reader.readLine().getBytes(),"UTF-8"))!=null){
                if(input.contains("/autor/")){
                    String autorUrl = input.substring(input.indexOf("/"),input.lastIndexOf("\""));
                    String autor = input.substring(input.lastIndexOf("\"")+2,input.lastIndexOf("<")-4);
                    autoren.add(new Autor(autor));
                    url = new URL("http://www.zitate.de"+autorUrl);
                    addZitate(url,autoren.get(autoren.size()-1));
                    if (anzahlZitate==0) return;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
