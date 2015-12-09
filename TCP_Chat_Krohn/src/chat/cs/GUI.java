package chat.cs;

import chat.ChatEvent;
import chat.IView;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;

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

  public String showLoginFrame() {    
    ownName = JOptionPane.showInputDialog(null, "Name", "Login", JOptionPane.PLAIN_MESSAGE);
    if (ownName == null) {
      JOptionPane.showMessageDialog(null, "Client needs a name, bye.");
      System.exit(0);
    }
    return ownName;
  } 

  public void showChatFrame() {
    JPanel usersPanel = new JPanel();
    usersPanel.setLayout(new BorderLayout());
    usersPanel.add(new JLabel("Current users:"), BorderLayout.NORTH);
    userList = new JTextArea(10, 20);
    userList.setEditable(false);
    usersPanel.add(userList, BorderLayout.SOUTH);

    JPanel chatPanel = new JPanel();
    chatPanel.setLayout(new BorderLayout());
    chatPanel.add(new JLabel("Chat:"), BorderLayout.NORTH);
    chatArea = new JTextArea(10, 20);
    chatPanel.add(chatArea, BorderLayout.SOUTH);

    JPanel ownPanel = new JPanel();
    ownPanel.setLayout(new BorderLayout());
    ownPanel.add(new JLabel("Your comment:"), BorderLayout.NORTH);
    chatField = new JTextField(20);
    ownPanel.add(chatField, BorderLayout.CENTER);
    
    JPanel buttonsPanel = new JPanel();
    submitButton = new JButton("submit");
    submitButton.addActionListener(new ChatListener());
    buttonsPanel.add(submitButton);
    logoutButton = new JButton("logout");
    logoutButton.addActionListener(new ChatListener());
    buttonsPanel.add(logoutButton);
    ownPanel.add(buttonsPanel, BorderLayout.SOUTH);

    chatFrame = new JFrame(); 
    chatFrame.setLayout(new BorderLayout());
    chatFrame.add(usersPanel, BorderLayout.NORTH);
    chatFrame.add(chatPanel, BorderLayout.CENTER);
    chatFrame.add(ownPanel, BorderLayout.SOUTH);
    chatFrame.pack();
    chatFrame.getRootPane().setDefaultButton(submitButton);
    chatFrame.setVisible(true);
  } 
  
  private void fillUserList(String[] names) {
    userList.setText("");
    for ( String name: names ) 
      if (name.equals(ownName) )
        userList.append("<" + name + ">\n");
      else
        userList.append(name + "\n");
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
 
  public void update(ChatEvent evt) { 
    switch (evt.getEventType()) {
      case ChatEvent.LIST_UPDATE:
        fillUserList(evt.getList());          
        break;
      case ChatEvent.COMMENT:
        chatArea.append(evt.getComment() + "\n");
        break;
    }  
  }
  
  public void run() {
    
  }
}
