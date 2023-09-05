package tw.com.cha102.support.model.dto;

public class ChatMessage {
	private String type;
	private String sender;
	private String receiver;
	private String message;
	private String messageTime;

	public ChatMessage(String type, String sender, String receiver, String message, String messageTime) {
		this.type = type;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.messageTime = messageTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(String messageTime) {
		this.messageTime = messageTime;
	}
}