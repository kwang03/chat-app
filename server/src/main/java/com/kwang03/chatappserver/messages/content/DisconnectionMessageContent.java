package com.kwang03.chatappserver.messages;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DisconnectionMessageContent extends MessageContent{
    private String userId;
    private String endpoint;
}
