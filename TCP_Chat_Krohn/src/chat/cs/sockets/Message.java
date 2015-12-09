package chat.cs.sockets;

import java.io.Serializable;

public class Message implements Serializable {
 
  private static final long serialVersionUID = 1L;
  public final static int UNDEFINED = 0;
  public final static int LOGIN     = 1;
  public final static int COMMENT   = 2;
  public final static int LOGOUT    = 3;
  public final static int POLLING   = 4;
  public final static int CONFIRM   = 5;

  private int messageType;
  private String name;
  private String comment;

  public Message() {
    this.messageType = UNDEFINED;
  }
  
  public Message(int messageType) {
    this.messageType = messageType;
  }
  
  public Message(int messageType, String name) {
    this.messageType = messageType;
    this.name        = name;
  }

  public Message(int messageType, String name, String comment) {
    this.messageType = messageType;
    this.name        = name;
    this.comment     = comment;
  }

  public int getMessageType() {
    return messageType;
  }

  public String getName() {
    return name;
  }

  public String getComment() {
    return comment;
  }
}
