package com.bbt.hackathon.nsfweb.websocket;

import org.nextrtc.signalingserver.api.NextRTCServer;
import org.nextrtc.signalingserver.domain.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.nextrtc.signalingserver.domain.Message;

import com.bbt.hackathon.nsfweb.data.ChatListContainer;

import java.io.IOException;


public class ChatEndpoint extends TextWebSocketHandler {
    private static class SessionWrapper implements Connection {

        private final WebSocketSession session;

        public SessionWrapper(WebSocketSession session) {
            this.session = session;
        }

        @Override
        public String getId() {
            return session.getId();
        }

        @Override
        public boolean isOpen() {
            return session.isOpen();
        }

        @Override
        public void sendObject(Object object) {
            try {
                session.sendMessage(new TextMessage(NextRTCServer.MessageEncoder.encode(object)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private final NextRTCServer server;
    
    @Autowired
    private ChatListContainer chatListContainer;

    @Autowired
    ChatEndpoint(NextRTCServer server) {
        this.server = server;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        server.register(new SessionWrapper(session));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Message msg = NextRTCServer.MessageDecoder.decode(message.getPayload());
        String content = msg.getContent();
        String signal = msg.getSignal();
        
        if (signal.equals("create")) {
            chatListContainer.registerChat(session.getId(), content);
        }
        
        server.handle(NextRTCServer.MessageDecoder.decode(message.getPayload()), new SessionWrapper(session));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        chatListContainer.unregister(session.getId());
        server.unregister(new SessionWrapper(session), status.getReason());
    }
}
