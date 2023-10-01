package com.kwang03.chatappserver.messages;

import com.kwang03.chatappserver.messages.content.DisconnectionMessageContent;
import com.kwang03.chatappserver.messages.content.MessageContent;
import lombok.Data;

@Data
public class DisconnectionMessage extends SimpleMessage {
    public DisconnectionMessage(MessageContent content) {
        super(content);
        if (!(content instanceof DisconnectionMessageContent)) {
            throw new IllegalArgumentException("Disconnection message content type required for DisconnectionMessage");
        }
    }
    @Override
    public String getMessage() {
        DisconnectionMessageContent disconnectionMessageContent = (DisconnectionMessageContent) content;
        return disconnectionMessageContent.getUserId() + " disconnected from: " + disconnectionMessageContent.getEndpoint();
    }
}
