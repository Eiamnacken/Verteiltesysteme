package com.TCPChat.chat.cs;


import com.TCPChat.chat.ChatEvent;
import com.TCPChat.chat.IView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements IView {
  private IChat serverProxy;
  private String ownName;
  private JFrame chatFrame;
  private JTextArea userList;
  private JTextArea chatArea;
  private JTextField chatField;
  private JButton submitButton, logoutButton;

  public GUI(IChat proxy) {
    this.serverProxy = proxy;
  }



  class ChatListener implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
      if (evt.getSource() == submitButton) {
        try {
          serverProxy.comment(ownName, chatField.getText());
        } catch (Exception e) { System.err.println(e); }
        chatField.setText(""); 
      } 
      else if (evt.getSource() == logoutButton) {
        try {
          serverProxy.logout(ownName);
        } catch (Exception e) { System.err.println(e); }
        chatFrame.dispose();
        System.exit(0);
      }
    }
  }
  
  public void run() {
    
  }
}
