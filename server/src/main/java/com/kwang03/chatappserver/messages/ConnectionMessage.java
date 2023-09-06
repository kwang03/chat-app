package com.kwang03.chatappserver.messages;

public class ConnectionMessage extends SimpleMessage{

    public ConnectionMessage(String content) {
        super(content);
    }
    @Override
    public String getContent() {
        return "User connected to: " + this.content;
    }
}
