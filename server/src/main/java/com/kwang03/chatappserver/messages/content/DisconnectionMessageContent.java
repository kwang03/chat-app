package com.kwang03.chatappserver.messages.content;

import lombok.Data;

@Data
public class DisconnectionMessageContent extends MessageContent {
    private String endpoint;

    public DisconnectionMessageContent(String userId, String endpoint) {
        this.userId = userId;
        this.endpoint = endpoint;
    }
}
