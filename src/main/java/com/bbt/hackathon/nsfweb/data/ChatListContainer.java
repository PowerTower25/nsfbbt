package com.bbt.hackathon.nsfweb.data;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class ChatListContainer {
    private static final Logger logger = LoggerFactory.getLogger(ChatListContainer.class);
    private List<ChatSession> chats = new ArrayList<ChatSession>();

    public void registerChat(String sessionId, String name) {
        //if there is any chats for that session, kill them
        unregister(sessionId);
        chats.add(new ChatSession(name, sessionId));
    }    
    
    
    public List<ChatSession> getChats() {
        return chats;
    }
    
    public void unregister(String sessionId) {
        try {
            //lazy just iterate and see if there is one there
            for (ChatSession c : chats) {
                if (c.getSessionId().equals(sessionId)) {
                    chats.remove(c);
                }
            }
        } catch (Exception e) {
            logger.error("could not unregister :" + sessionId, e);
        }
      }
}
