package com.kwang03.chatappserver.messages;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleMessageContent extends MessageContent{
    private String message;
}
