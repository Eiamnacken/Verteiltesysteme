package com.TCPChat.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sven on 08.12.15.
 */
public class Raum {
    private List<User> users;

    public Raum() {
        this.users = new LinkedList<>();
    }
}
