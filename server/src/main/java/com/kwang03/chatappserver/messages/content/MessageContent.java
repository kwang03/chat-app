package com.kwang03.chatappserver.messages.content;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(SimpleMessageContent.class),
        @JsonSubTypes.Type(ImageMessageContent.class) }
)
public abstract class MessageContent {
    protected String userId;
    protected String language;

    public MessageContent(String userId, String language) {
        this.userId = userId;
        this.language = language;
    }
}
