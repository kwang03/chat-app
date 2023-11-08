package com.kwang03.chatappserver.messages;

import com.kwang03.chatappserver.messages.content.ImageMessageContent;
import com.kwang03.chatappserver.messages.content.MessageContent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ImageMessage extends SimpleMessage {
    public ImageMessage(MessageContent content) {
        super(content);
        if (!(content instanceof ImageMessageContent)) {
            throw new IllegalArgumentException("Image message content type required for ImageMessage");
        }
    }

    @Override
    public String getMessage() {
        ImageMessageContent imageMessageContent = (ImageMessageContent) content;
        return imageMessageContent.getImageUrl();
    }
}
