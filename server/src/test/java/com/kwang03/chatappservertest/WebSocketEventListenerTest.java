package com.kwang03.chatappservertest;

import com.kwang03.chatappserver.ChatAppServer;
import com.kwang03.chatappserver.WebSocketMessageSender;
import com.kwang03.chatappserver.WebSocketSessionMapper;
import com.kwang03.chatappserver.messages.MessageFactory;
import com.kwang03.chatappserver.messages.content.ConnectionMessageContent;
import com.kwang03.chatappserver.messages.content.DisconnectionMessageContent;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChatAppServer.class)
public class WebSocketEventListenerTest {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private SimpMessagingTemplate template;

    @Mock
    private SessionSubscribeEvent subscribeEventMock;
    @Mock
    private SessionUnsubscribeEvent unsubscribeEventMock;

    @MockBean
    private WebSocketSessionMapper sessionMapperMock;

    static final String DESTINATION = "simpDestination";
    static final String USER_ID = "simpSubscriptionId";
    static final String SESSION_ID = "simpSessionId";
    static final String DESTINATION_EXAMPLE = "/test";
    static final String USER_EXAMPLE = "user1";
    static final String SESSION_EXAMPLE = "abc123";
    @BeforeEach
    public void createMocks() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @SuppressWarnings("unchecked")
    public void Test_HandleConnection_Calls_SessionMapper () {
        Message mockedMessage = mock(Message.class);
        MessageHeaders messageHeaders = new MessageHeaders(
                Map.of(DESTINATION, DESTINATION_EXAMPLE, USER_ID, USER_EXAMPLE, SESSION_ID, SESSION_EXAMPLE));
        when(subscribeEventMock.getMessage()).thenReturn(mockedMessage);
        when(mockedMessage.getHeaders()).thenReturn(messageHeaders);

        publisher.publishEvent(subscribeEventMock);

        verify(sessionMapperMock).subscribe(SESSION_EXAMPLE, USER_EXAMPLE);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Test_HandleConnection_Sends_Correct_Message () {
        Message mockedMessage = mock(Message.class);
        MessageHeaders messageHeaders = new MessageHeaders(
                Map.of(DESTINATION, DESTINATION_EXAMPLE, USER_ID, USER_EXAMPLE, SESSION_ID, SESSION_EXAMPLE));
        when(subscribeEventMock.getMessage()).thenReturn(mockedMessage);
        when(mockedMessage.getHeaders()).thenReturn(messageHeaders);

        try (MockedStatic<WebSocketMessageSender> senderMock = mockStatic(WebSocketMessageSender.class)) {
            publisher.publishEvent(subscribeEventMock);

            senderMock.verify(() -> WebSocketMessageSender.sendMessage(DESTINATION_EXAMPLE,
                    MessageFactory.MessageType.CONNECTION,
                    new ConnectionMessageContent(USER_EXAMPLE, "EN", DESTINATION_EXAMPLE), template));
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Test_HandleDisconnection_Calls_SessionMapper () {
        Message mockedMessage = mock(Message.class);
        MessageHeaders messageHeaders = new MessageHeaders(
                Map.of(USER_ID, DESTINATION_EXAMPLE, SESSION_ID, SESSION_EXAMPLE));
        when(unsubscribeEventMock.getMessage()).thenReturn(mockedMessage);
        when(mockedMessage.getHeaders()).thenReturn(messageHeaders);

        publisher.publishEvent(unsubscribeEventMock);

        verify(sessionMapperMock).unsubscribe(SESSION_EXAMPLE);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Test_HandleDisconnection_Sends_Correct_Message () {
        Message mockedMessage = mock(Message.class);
        MessageHeaders messageHeaders = new MessageHeaders(
                Map.of(USER_ID, DESTINATION_EXAMPLE, SESSION_ID, SESSION_EXAMPLE));
        when(unsubscribeEventMock.getMessage()).thenReturn(mockedMessage);
        when(mockedMessage.getHeaders()).thenReturn(messageHeaders);

        when(sessionMapperMock.unsubscribe(SESSION_EXAMPLE)).thenReturn(USER_EXAMPLE);

        try (MockedStatic<WebSocketMessageSender> senderMock = mockStatic(WebSocketMessageSender.class)) {
            publisher.publishEvent(unsubscribeEventMock);

            senderMock.verify(() -> WebSocketMessageSender.sendMessage(DESTINATION_EXAMPLE,
                    MessageFactory.MessageType.DISCONNECTION,
                    new DisconnectionMessageContent(USER_EXAMPLE, "EN", DESTINATION_EXAMPLE), template));
        }
    }

}
