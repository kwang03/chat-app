package com.kwang03.chatappserver.messages;

import com.kwang03.chatappserver.messages.content.ConnectionMessageContent;
import com.kwang03.chatappserver.messages.content.MessageContent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
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
