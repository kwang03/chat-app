package com.kwang03.chatappserver.messages.content;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ImageMessageContent extends MessageContent{
    private String imageUrl;

    public ImageMessageContent(String userId, String language, String url) {
        super(userId, language);
        imageUrl = url;
    }


}
