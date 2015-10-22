package com.company;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sven on 21.10.15.
 */
public class Autor {
    private List<String> zitate;
    private String autor;

    public Autor(String autor) {
        zitate = new LinkedList<>();
        this.autor = autor;
    }

    public void addZitat(String zitat){
        this.zitate.add(zitat.trim());
    }

    public String getAutor(){
        return this.autor;
    }

    public List<String> getZitate(){
        return this.zitate;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.autor);
        builder.append("["+"Zitate"+"=");
        builder.append(zitate);
        builder.append("]");
        return builder.toString();
    }
}
