package com.kwang03.chatappserver.messages;

import com.kwang03.chatappserver.messages.ConnectionMessage;
import com.kwang03.chatappserver.messages.DisconnectionMessage;
import com.kwang03.chatappserver.messages.MessageContent;
import com.kwang03.chatappserver.messages.SimpleMessage;

public class MessageFactory {

    /**
     *  Enum for the type of message to be sent
     */
    public enum MessageType {
        CONNECTION,
        DISCONNECTION,
        SIMPLE
    }

    /**
     *
     * @param messageType
     * @param content
     * @return
     */
    public static SimpleMessage getMessage(MessageType messageType, MessageContent content) {
        switch (messageType) {
            case CONNECTION:
                return new ConnectionMessage(content);
            case DISCONNECTION:
                return new DisconnectionMessage(content);
            default:
                return new SimpleMessage(content);
        }
    }
}
