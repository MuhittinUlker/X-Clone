package com.clonex.classes;

import java.time.LocalDateTime;

public class Message {

	private String sender;
	private String message;
	private LocalDateTime sendTime;
	
	public Message(String sender, String message) {
		super();
		this.sender = sender;
		this.message = message;
		this.sendTime = LocalDateTime.now();
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTweetTime() {
		return sendTime;
	}

	@Override
	public String toString() {
		return "\u001B[33mGönderen=\u001B[0m" + sender + "\u001B[33m\nİçerik=\u001B[0m" + message +"\n"
	+ sendTime.getDayOfMonth()+"."+sendTime.getMonthValue()+"."+sendTime.getYear()+" "+
	sendTime.getHour()+":"+sendTime.getMinute()+ "\u001B[33m 'da gönderildi.\u001B[0m";
	}
	
	
	
}
