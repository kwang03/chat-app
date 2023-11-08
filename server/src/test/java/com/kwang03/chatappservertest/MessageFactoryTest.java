package com.kwang03.chatappservertest;

import com.kwang03.chatappserver.messages.*;
import com.kwang03.chatappserver.messages.content.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class MessageFactoryTest {
    @Test
    public void Test_DisconnectionMessage_Return_DisconnectionMessage () {
        MessageContent messageContentMock = mock(DisconnectionMessageContent.class);

        SimpleMessage returnMessage = MessageFactory.getMessage(MessageFactory.MessageType.DISCONNECTION, messageContentMock);
        assertEquals(new DisconnectionMessage(messageContentMock), returnMessage);
    }

    @Test
    public void Test_ConnectionMessage_Return_ConnectionMessage () {
        MessageContent messageContentMock = mock(ConnectionMessageContent.class);

        SimpleMessage returnMessage = MessageFactory.getMessage(MessageFactory.MessageType.CONNECTION, messageContentMock);
        assertEquals(new ConnectionMessage(messageContentMock), returnMessage);
    }

    @Test
    public void Test_ImageMessage_Return_ImageMessage () {
        MessageContent messageContentMock = mock(ImageMessageContent.class);

        SimpleMessage returnMessage = MessageFactory.getMessage(MessageFactory.MessageType.IMAGE, messageContentMock);
        assertEquals(new ImageMessage(messageContentMock), returnMessage);
    }

    @Test
    public void Test_SimpleMessage_Return_SimpleMessage () {
        MessageContent messageContentMock = mock(SimpleMessageContent.class);

        SimpleMessage returnMessage = MessageFactory.getMessage(MessageFactory.MessageType.SIMPLE, messageContentMock);
        assertEquals(new SimpleMessage( messageContentMock), returnMessage);
    }
}
