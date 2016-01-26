package com.TCPChat.model;

import com.TCPChat.chat.ChatEvent;
import com.TCPChat.chat.IView;
import com.TCPChat.chat.cs.IChat;
import javafx.scene.control.TextArea;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Created by sven on 08.12.15.
 */
public class Raum implements IView{
    private String name;
    private List<User> users;
    private TextArea roomChat;
    private Optional<IChat> serverProxy;

    public Raum(String name) {
        this.name=name;
        this.users = new LinkedList<>();
    }

    public void send(){
        for(User u : this.users){

        }
    }

    public void comment(){

    }

    public List<User> getUser(){
        return this.users;
    }

    public TextArea getRoomChat() {
        return this.roomChat;
    }

    public void addUser(User user){
        this.users.add(user);
    }

    @Override
    public String toString() {
        return this.name;
    }

    private void setUsers(){

    }

    @Override
    public void update(ChatEvent evt) throws Exception {
        switch (evt.getEventType()) {
            case ChatEvent.LIST_UPDATE:
                //TODO Userlist updaten

                break;
            case ChatEvent.COMMENT:
                //TODO abhängig vom raum machen
                rooms.get(roomGUI.getSelectionModel().
                        getSelectedItem().getText()).getRoomChat().setText(evt.getComment()+"\n");
                break;
        }
    }
}
