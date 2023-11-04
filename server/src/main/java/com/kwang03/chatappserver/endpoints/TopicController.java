package com.kwang03.chatappserver.endpoints;

import com.kwang03.chatappserver.WebSocketSessionMapper;
import com.kwang03.chatappserver.messages.MessageFactory;
import com.kwang03.chatappserver.messages.SimpleMessage;
import com.kwang03.chatappserver.messages.content.ImageMessageContent;
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

	@MessageMapping("/two")
	@SendTo("/topic/two")
	public SimpleMessage topicTwoHandler(@Payload SimpleMessage message, SimpMessageHeaderAccessor headerAccessor) {
		String sessionId = headerAccessor.getSessionId();
		String userId = sessionMapper.getUserIdFromSessionId(sessionId);

		if (message.getContent() instanceof ImageMessageContent) {
			return MessageFactory.getMessage(MessageFactory.MessageType.IMAGE,
					new ImageMessageContent(userId, message.getContent().getLanguage(),
							HtmlUtils.htmlEscape(((ImageMessageContent) message.getContent()).getImageUrl())));
		} else if (message.getContent() instanceof SimpleMessageContent) {
			return MessageFactory.getMessage(MessageFactory.MessageType.SIMPLE,
					new SimpleMessageContent(userId, HtmlUtils.htmlEscape(message.getMessage()),
					message.getContent().getLanguage()));
		} else {
			return null;
		}
	}
}
