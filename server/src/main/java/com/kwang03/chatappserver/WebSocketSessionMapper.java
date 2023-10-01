package com.kwang03.chatappserver;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("singleton")
public class WebSocketSessionMapper {
    private final Map<String, String> sessions;

    public WebSocketSessionMapper() {
        this.sessions = new HashMap<>();
    }


    public String getUserIdFromSessionId(String sessionId) {
        if (!sessions.containsKey(sessionId)) {
            throw new IllegalArgumentException("Session Id: " + sessionId + " does not exist");
        }
        return sessions.get(sessionId);
    }

    public void subscribe(String sessionId, String username) {
        if (sessions.containsKey(sessionId)) {
            throw new IllegalArgumentException("Session Id: "+ sessionId + " already exists");
        }
        sessions.put(sessionId, username);
    }

    public String unsubscribe(String sessionId) {
        if (!sessions.containsKey(sessionId)) {
            throw new IllegalStateException("Session Id: " + sessionId + " does not exist");
        }
        return sessions.remove(sessionId);
    }
}
