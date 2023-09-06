package com.kwang03.chatappserver.endpoints;

import com.kwang03.chatappserver.MessageFactory;
import com.kwang03.chatappserver.messages.HelloMessage;
import com.kwang03.chatappserver.messages.SimpleMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

	@MessageMapping("/one")
	@SendTo("/topic/one")
	public SimpleMessage topicOneHandler(HelloMessage message) {
		return MessageFactory.getMessage("SimpleMessage", HtmlUtils.htmlEscape(message.getName()));
	}

	@MessageMapping("/two")
	@SendTo("/topic/two")
	public SimpleMessage topicTwoHandler(HelloMessage message) {
		return MessageFactory.getMessage("SimpleMessage", HtmlUtils.htmlEscape(message.getName()));
	}
}
