package com.kwang03.chatappserver;

import com.kwang03.chatappserver.messages.content.ConnectionMessageContent;
import com.kwang03.chatappserver.messages.content.DisconnectionMessageContent;
import com.kwang03.chatappserver.messages.MessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

@Component
public class WebSocketEventListener {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private WebSocketSessionMapper sessionMapper;

    @EventListener
    public void handleWebSocketConnection(SessionSubscribeEvent event) {
        String dest = (String) event.getMessage().getHeaders().get("simpDestination");
        String userId = (String) event.getMessage().getHeaders().get("simpSubscriptionId");
        String sessionId = (String) event.getMessage().getHeaders().get("simpSessionId");

        sessionMapper.subscribe(sessionId, userId);

        //  TODO: How to get language from event
        ConnectionMessageContent content = new ConnectionMessageContent(userId, "EN", dest);

        WebSocketMessageSender.sendMessage(dest, MessageFactory.MessageType.CONNECTION, content, template);
    }

    @EventListener
    public void handleWebSocketDisconnection(SessionUnsubscribeEvent event) {
        String topic = (String) event.getMessage().getHeaders().get("simpSubscriptionId");
        String sessionId = (String) event.getMessage().getHeaders().get("simpSessionId");

        String userId = sessionMapper.unsubscribe(sessionId);

        DisconnectionMessageContent content = new DisconnectionMessageContent(userId, "EN", topic);

        WebSocketMessageSender.sendMessage(topic, MessageFactory.MessageType.DISCONNECTION, content, template);
    }
}
