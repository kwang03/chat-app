package com.kwang03.chatappserver.messages;

import com.kwang03.chatappserver.messages.content.MessageContent;

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
