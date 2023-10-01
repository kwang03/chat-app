package com.kwang03.chatappserver.messages;

public class HelloMessage {

	private String name;
	public String userId;

	public HelloMessage() {
	}

	public HelloMessage(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
