package com.kwang03.chatappserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

@Component
public class WebSocketEventListener {

    @Autowired
    SimpMessagingTemplate template;

    @EventListener
    public void handleWebSocketConnection(SessionSubscribeEvent event) {
        String dest = (String) event.getMessage().getHeaders().get("simpDestination");

        WebSocketMessageSender.sendMessage(dest, "Connection", dest, template);
    }

    @EventListener
    public void handleWebSocketDisconnection(SessionUnsubscribeEvent event) {
        String topic = (String) event.getMessage().getHeaders().get("simpSubscriptionId");

        WebSocketMessageSender.sendMessage(topic, "Disconnection", topic, template);
    }
}
