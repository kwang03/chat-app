package com.kwang03.chatappserver.messages;

import com.kwang03.chatappserver.messages.content.MessageContent;
import com.kwang03.chatappserver.messages.content.SimpleMessageContent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class SimpleMessage {

    protected MessageContent content;
    protected String time;

    public SimpleMessage(MessageContent content) {
        this.content = content;
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
        time = formatter.format(new Date());
    }

    public String getMessage() {
        SimpleMessageContent simpleMessageContent = (SimpleMessageContent) content;
        return simpleMessageContent.getMessage();
    }
}
