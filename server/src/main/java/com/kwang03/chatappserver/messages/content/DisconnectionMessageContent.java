package com.kwang03.chatappserver.messages.content;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DisconnectionMessageContent extends MessageContent {
    private String endpoint;

    public DisconnectionMessageContent(String userId, String language, String endpoint) {
        super(userId, language);
        this.endpoint = endpoint;
    }
}
