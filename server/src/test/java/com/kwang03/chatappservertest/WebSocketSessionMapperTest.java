package com.kwang03.chatappservertest;

import com.kwang03.chatappserver.WebSocketSessionMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

public class WebSocketSessionMapperTest {
    private WebSocketSessionMapper testMapper;
    @Mock
    private Map<String, String> mockedSessions;
    static final String SESSION_ID_1 = "sessionId1";
    static final String USER_ID_1 = "userId1";

    @BeforeEach
    public void createMocks() {
        MockitoAnnotations.openMocks(this);
        testMapper = new WebSocketSessionMapper(mockedSessions);
    }

    @AfterEach
    public void teardown() {
        testMapper.clear();
    }

    @Test
    public void Test_GetUserIdFromSessionId_Throws_IllegalArgumentException () {
        Mockito.when(mockedSessions.containsKey(SESSION_ID_1)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> testMapper.getUserIdFromSessionId(SESSION_ID_1));
    }

    @Test
    public void Test_GetUserIdFromSessionId_DoesNotThrow_IllegalArgumentException () {
        Mockito.when(mockedSessions.containsKey(SESSION_ID_1)).thenReturn(true);
        assertDoesNotThrow(() -> testMapper.getUserIdFromSessionId(SESSION_ID_1));
    }

    @Test
    public void Test_GetUserIdFromSessionId_Returns_UserId () {
        Mockito.when(mockedSessions.containsKey(SESSION_ID_1)).thenReturn(true);
        Mockito.when(mockedSessions.get(SESSION_ID_1)).thenReturn(USER_ID_1);
        assertEquals(USER_ID_1, testMapper.getUserIdFromSessionId(SESSION_ID_1));
    }

    @Test
    public void Test_Subscribe_Throws_IllegalArgumentException () {
        Mockito.when(mockedSessions.containsKey(SESSION_ID_1)).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> testMapper.subscribe(SESSION_ID_1, USER_ID_1));
    }

    @Test
    public void Test_Subscribe_DoesNotThrow_IllegalArgumentException () {
        Mockito.when(mockedSessions.containsKey(SESSION_ID_1)).thenReturn(false);
        assertDoesNotThrow(() -> testMapper.subscribe(SESSION_ID_1, USER_ID_1));
    }

    @Test
    public void Test_Subscribe_Puts_KVPair () {
        Mockito.when(mockedSessions.containsKey(SESSION_ID_1)).thenReturn(false);
        testMapper.subscribe(SESSION_ID_1, USER_ID_1);
        verify(mockedSessions).put(SESSION_ID_1, USER_ID_1);
    }

    @Test
    public void Test_Unsubscribe_Throws_IllegalArgumentException () {
        Mockito.when(mockedSessions.containsKey(SESSION_ID_1)).thenReturn(false);
        assertThrows(IllegalStateException.class, () -> testMapper.unsubscribe(SESSION_ID_1));
    }

    @Test
    public void Test_Unsubscribe_DoesNotThrow_IllegalArgumentException () {
        Mockito.when(mockedSessions.containsKey(SESSION_ID_1)).thenReturn(true);
        assertDoesNotThrow(() -> testMapper.unsubscribe(SESSION_ID_1));
    }

    @Test
    public void Test_Unsubscribe_Removes_KVPair () {
        Mockito.when(mockedSessions.containsKey(SESSION_ID_1)).thenReturn(true);
        testMapper.unsubscribe(SESSION_ID_1);
        verify(mockedSessions).remove(SESSION_ID_1);
    }

}
