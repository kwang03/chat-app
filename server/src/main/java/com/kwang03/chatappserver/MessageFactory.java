package com.kwang03.chatappserver;

import com.kwang03.chatappserver.messages.ConnectionMessage;
import com.kwang03.chatappserver.messages.DisconnectionMessage;
import com.kwang03.chatappserver.messages.SimpleMessage;

public class MessageFactory {
    public static SimpleMessage getMessage(String messageType, String content) {
        switch (messageType) {
            case "Connection":
                return new ConnectionMessage(content);
            case "Disconnection":
                return new DisconnectionMessage(content);
            default:
                return new SimpleMessage(content);
        }
    }
}
