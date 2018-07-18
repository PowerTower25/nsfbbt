package com.bbt.hackathon.nsfweb.data;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;


@Component
public class ChatListContainer {
    private Map<String, String> chats = new HashMap<String, String>();

    public void registerChat(String sessionId, String name) {
        chats.put(sessionId, name);
    }    
    
    
    public Map<String, String> getChats() {
        return chats;
    }
    
    public void unregister(String sessionId) {
        try {
            chats.remove(sessionId);
        } catch (Exception e) {
            
        }
      }
}
