package com.kwang03.chatappserver;


import com.kwang03.chatappserver.messages.MessageContent;
import com.kwang03.chatappserver.messages.MessageFactory;
import com.kwang03.chatappserver.messages.SimpleMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class WebSocketMessageSender {

    public static void sendMessage(String dest, MessageFactory.MessageType messageType, MessageContent content, SimpMessagingTemplate template) {
        SimpleMessage message = MessageFactory.getMessage(messageType, content);

        if (dest != null) {
            template.convertAndSend(dest, message);
        }
    }
}
