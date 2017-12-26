package homework.IM.model.db;

import java.util.Date;

public class GroupMessage {
	public GroupMessage(int messageId, int groupId, int senderId, String message) {
		super();
		this.messageId = messageId;
		this.groupId = groupId;
		this.senderId = senderId;
		this.message = message;
		this.messageTime = new Date();
	}
	public GroupMessage() {
		super();
		
	}
	public GroupMessage(int userId, int groupId, String message) {
		this.groupId = groupId;
		this.senderId = userId;
		this.message = message;
		this.messageTime = new Date();
	}
	private int messageId;
	private int groupId;
	private int senderId;
	private String message;
	private Date messageTime;
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getMessageTime() {
		return messageTime;
	}
	public void setMessageTime(Date messageTime) {
		this.messageTime = messageTime;
	}
	
}
