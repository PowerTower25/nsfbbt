package com.bbt.hackathon.nsfweb.controller;
import java.util.List;

import com.bbt.hackathon.nsfweb.data.ChatSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bbt.hackathon.nsfweb.data.ChatListContainer;

@RestController
public class ChatController {
    
    @Autowired
    private ChatListContainer chatListContainer;
    
    @RequestMapping("/chatlist")
    public List<ChatSession> chatList() {
        return chatListContainer.getChats();
    }
}
