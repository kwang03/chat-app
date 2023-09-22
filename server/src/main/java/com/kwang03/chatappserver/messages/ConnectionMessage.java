package com.kwang03.chatappserver.messages;

import lombok.Data;

@Data
public class ConnectionMessage extends SimpleMessage {

    public ConnectionMessage(MessageContent content) {
        super(content);
        if (!(content instanceof ConnectionMessageContent)) {
            throw new IllegalArgumentException("Connection message content type required for ConnectionMessage");
        }
    }
    @Override
    public String getMessage() {
        ConnectionMessageContent connectionMessageContent = (ConnectionMessageContent) content;
        return connectionMessageContent.getUserId() + " connected to: " + connectionMessageContent.getEndpoint();
    }
}
