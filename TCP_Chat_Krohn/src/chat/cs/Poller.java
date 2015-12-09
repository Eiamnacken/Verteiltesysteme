package chat.cs;

import chat.ChatEvent;
import chat.IView;

public class Poller extends Thread {
  private String ownName; 
  private IChat serverProxy;
  private IView view;

  
  public Poller(IChat server, IView view) {
    this.serverProxy = server;
    this.view  = view;      
  } 
  
  public void setOwnName(String name) {
    this.ownName = name;
  }
  
  public void run() { 
    ChatEvent evt = null;

    while (true) {
      try {
        evt = serverProxy.poll(ownName);  
        if ( evt != null ) {       
          view.update(evt);  
        } 
        Thread.sleep(500);
      } catch (Exception e) { System.err.println(e); break; }
    }
  }
} 