package com.demo.chatapp;

public class ChatMessage {
	private String sender = null;
	private String message = null;

	public ChatMessage(String sender, String message) {
		super();
		this.sender = sender;
		this.message = message;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSender() {
		return sender;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
