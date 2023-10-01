package com.kwang03.chatappserver.messages.content;

import lombok.Data;

@Data
public class ConnectionMessageContent extends MessageContent {
    private String endpoint;

    public ConnectionMessageContent(String userId, String endpoint) {
        this.userId = userId;
        this.endpoint = endpoint;
    }
}
