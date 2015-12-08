package com.TCPChat.model;

/**
 * Created by sven on 08.12.15.
 */
public class User {
    /**
     * Name des Users
     */
    private String userName;
    /**
     * Handelt es sich um den host ja oder nein
     */
    private boolean admin;

    /**
     * Für den Host
     * @param userName  Name des Hosts
     * @param admin     Zeigt das es sich um einen Host handelt
     */
    public User(String userName, boolean admin) {
        this.userName = userName;
        this.admin = admin;
    }

    /**
     * Neuer User
     * @param userName  Name des user
     */
    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isAdmin() {
        return admin;
    }


}
