package com.kwang03.chatappserver.messages.content;

import lombok.Data;

@Data
public class SimpleMessageContent extends MessageContent {
    private String message;

    public SimpleMessageContent(String userId, String message) {
        this.userId = userId;
        this.message = message;
    }
}
