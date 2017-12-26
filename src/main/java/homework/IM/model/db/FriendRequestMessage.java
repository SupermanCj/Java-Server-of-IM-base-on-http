package homework.IM.model.db;

import java.util.Date;

public class FriendRequestMessage {
	public FriendRequestMessage(int id, int recipientId, int senderId, boolean isRead, int reply) {
		super();
		this.id = id;
		this.recipientId = recipientId;
		this.senderId = senderId;
		this.isRead = isRead;
		this.reply = reply;
		this.requestTime = new Date();
	}
	public FriendRequestMessage() {
		super();
		
	}
	public FriendRequestMessage(int recipientId,int senderId) {
		this.recipientId = recipientId;
		this.senderId = senderId;
		this.isRead=false;
		this.reply=0;
		this.requestTime=new Date();
	}
	private int id;
	private int recipientId;
	private int senderId;
	private boolean isRead;
	private int reply;
	private Date requestTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	
	public Date getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	public int getReply() {
		return reply;
	}
	public void setReply(int reply) {
		this.reply = reply;
	}
}
