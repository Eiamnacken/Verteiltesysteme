package com.TCPChat.chat.cs;


import com.TCPChat.chat.ChatEvent;

import java.util.*;

public class ChatEventManager implements IChat {
  private Map<String,Queue<ChatEvent>> eventsMap;

  public ChatEventManager() {
    eventsMap  = new HashMap<String,Queue<ChatEvent>>();  
  } 

  private void queueEventToAll(ChatEvent evt) {
    for (Queue<ChatEvent> q: eventsMap.values()) 
      q.offer(evt);
  } 
    
  public void login(String name) {
    if (!eventsMap.containsKey(name)) {  
      Queue<ChatEvent> q = new LinkedList<ChatEvent>();
      eventsMap.put(name, q);
      queueEventToAll(new ChatEvent(ChatEvent.LIST_UPDATE, getNames()));
    }
  } 

  public void comment(String name, String comment) {
    queueEventToAll(new ChatEvent(ChatEvent.COMMENT, name + ": " + comment));
  } 

  public ChatEvent poll(String name) {
    if (!eventsMap.containsKey(name)) return null;
    Queue<ChatEvent> q = eventsMap.get(name);
    return q.poll();
  } 

  public void logout(String name) {
    eventsMap.remove(name);
    queueEventToAll(new ChatEvent(ChatEvent.LIST_UPDATE, getNames()));
  } 
  
  private String[] getNames() {
    String[] array = new String[eventsMap.size()];
    return eventsMap.keySet().toArray(array);
  }
}

