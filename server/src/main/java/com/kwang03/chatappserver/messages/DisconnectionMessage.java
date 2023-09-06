package com.kwang03.chatappserver.messages;

public class DisconnectionMessage extends SimpleMessage {
    public DisconnectionMessage(String content) {
        super(content);
    }
    @Override
    public String getContent() {
        return "User disconnected from: " + this.content;
    }
}
