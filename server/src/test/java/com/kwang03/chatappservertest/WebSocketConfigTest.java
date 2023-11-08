package com.kwang03.chatappservertest;

import com.kwang03.chatappserver.WebSocketConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.StompWebSocketEndpointRegistration;

import static org.mockito.Mockito.*;

public class WebSocketConfigTest {

    private WebSocketConfig testWebSocketConfig;

    @Mock
    private MessageBrokerRegistry messageBrokerRegistryMock;

    @Mock
    private StompEndpointRegistry stompEndpointRegistryMock;

    static final String SUBSCRIBE_TOPIC_PREFIX = "/topic";
    static final String SEND_TOPIC_PREFIX = "/app";
    static final String URL_ENDPOINT = "/chat-app";
    static final String ALLOWED_ORIGINS = "*";

    @BeforeEach
    public void createMocks() {
        MockitoAnnotations.openMocks(this);
        testWebSocketConfig = new WebSocketConfig();
    }

    @Test
    public void Test_Configure_Message_Broker() {
        testWebSocketConfig.configureMessageBroker(messageBrokerRegistryMock);
        verify(messageBrokerRegistryMock, times(1)).enableSimpleBroker(SUBSCRIBE_TOPIC_PREFIX);
        verify(messageBrokerRegistryMock, times(1)).setApplicationDestinationPrefixes(SEND_TOPIC_PREFIX);
    }

    @Test
    public void Test_Register_Stomp_Endpoints() {
        StompWebSocketEndpointRegistration registrationMock = mock(StompWebSocketEndpointRegistration.class);
        when(stompEndpointRegistryMock.addEndpoint(any(String.class))).thenReturn(registrationMock);
        testWebSocketConfig.registerStompEndpoints(stompEndpointRegistryMock);
        verify(stompEndpointRegistryMock, times(1)).addEndpoint(URL_ENDPOINT);
        verify(stompEndpointRegistryMock.addEndpoint(URL_ENDPOINT), times(1)).setAllowedOrigins(ALLOWED_ORIGINS);
    }
}
