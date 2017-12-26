package homework.IM.model.db;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class FriendMessage {
	public FriendMessage(int messageId, int recipientId, int senderId, String message, boolean isRead) {
		super();
		this.messageId = messageId;
		this.recipientId = recipientId;
		this.senderId = senderId;
		this.message = message;
		this.isRead = isRead;
		this.messageTime = new Date();
	}
	public FriendMessage(int recipientId, int senderId, String message) {
		super();
		this.recipientId = recipientId;
		this.senderId = senderId;
		this.message = message;
		this.isRead = false;
		this.messageTime = new Date();
	}
	public FriendMessage() {
		super();
	}
	private int messageId;
	private int recipientId;
	private int senderId;
	private Date messageTime;
	private String message;
	private boolean isRead;
	@JSONField(serialize=false)
	private int relationId;
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public int getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public Date getMessageTime() {
		return messageTime;
	}
	public void setMessageTime(Date messageTime) {
		this.messageTime = messageTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public int getRelationId() {
		return relationId;
	}
	public void setRelationId(int relationId) {
		this.relationId = relationId;
	}
}
