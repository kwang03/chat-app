package com.kwang03.chatappserver.messages;

import lombok.*;

@Data
@AllArgsConstructor
public class ConnectionMessageContent extends MessageContent{
    private String userId;
    private String endpoint;
}
