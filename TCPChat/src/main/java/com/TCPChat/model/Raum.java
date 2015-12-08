package com.TCPChat.model;

import javafx.scene.control.TextArea;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sven on 08.12.15.
 */
public class Raum {
    private String name;
    private List<User> users;
    private TextArea roomChat;

    public Raum(String name) {
        this.name=name;
        this.users = new LinkedList<>();
    }

    public void send(){
        for(User u : this.users){

        }
    }

    public List<User> getUser(){
        return this.users;
    }

    public void addUser(User user){
        this.users.add(user);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
