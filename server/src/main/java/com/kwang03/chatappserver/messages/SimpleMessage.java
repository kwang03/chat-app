package com.kwang03.chatappserver.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SimpleMessage {

    protected MessageContent content;

    public SimpleMessage(MessageContent content) {
        this.content = content;
    }

    public String getMessage() {
        SimpleMessageContent simpleMessageContent = (SimpleMessageContent) content;
        return simpleMessageContent.getMessage();
    }
}
