package com.kwang03.chatappserver.messages.content;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SimpleMessageContent extends MessageContent {
    private String message;

    public SimpleMessageContent(String userId, String message, String language) {
        super(userId, language);
        this.message = message;
    }
}
