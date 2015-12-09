package chat.cs;
import chat.ChatEvent;

public interface IChat {
  public void login(String name) throws Exception;
  public void logout(String name) throws Exception;  
  public void comment(String name, String comment) throws Exception; 
  public ChatEvent poll(String name) throws Exception; 
}
