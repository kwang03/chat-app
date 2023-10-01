package com.kwang03.chatappserver.endpoints;

import com.kwang03.chatappserver.WebSocketSessionMapper;
import com.kwang03.chatappserver.messages.MessageFactory;
import com.kwang03.chatappserver.messages.HelloMessage;
import com.kwang03.chatappserver.messages.SimpleMessage;
import com.kwang03.chatappserver.messages.content.SimpleMessageContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class TopicController {

	@Autowired
	private WebSocketSessionMapper sessionMapper;

	@MessageMapping("/one")
	@SendTo("/topic/one")
	public SimpleMessage topicOneHandler(HelloMessage message) {
		return MessageFactory.getMessage(MessageFactory.MessageType.SIMPLE, new SimpleMessageContent("userid",HtmlUtils.htmlEscape(message.getName())));
	}

	// TODO: Need to include userid data in SimpleMessageContent. Must find out how to get sessionId from client when they publish message
	@MessageMapping("/two")
	@SendTo("/topic/two")
	public SimpleMessage topicTwoHandler(@Payload HelloMessage message, SimpMessageHeaderAccessor headerAccessor) {
		String sessionId = headerAccessor.getSessionId();
		String userId = sessionMapper.getUserIdFromSessionId(sessionId);
		return MessageFactory.getMessage(MessageFactory.MessageType.SIMPLE, new SimpleMessageContent(userId, HtmlUtils.htmlEscape(message.getName())));
	}
}
