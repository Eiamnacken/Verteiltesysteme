package chat;

import java.io.Serializable;

public class ChatEvent implements Serializable {
     

  private static final long serialVersionUID = 1L;
  public final static int UNDEFINED   = 0;
  public final static int LIST_UPDATE = 1;
  public final static int COMMENT     = 2;

  private int eventType;
  private String comment;
  private String[] list;

  public ChatEvent() {
    this.eventType = UNDEFINED;   
  }

  public ChatEvent(int eventType, String comment) {
    this.eventType = eventType;
    this.comment   = comment;
  }
  
  public ChatEvent(int eventType, String[] list) {
    this.eventType = eventType;
    this.list      = list;
  }

  public int getEventType() {
    return eventType;
  }

  public String getComment() {
    return comment;
  }
  
  public String[] getList() {
    return list;
  }
} 
