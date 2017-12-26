package homework.IM.model.db;

import java.util.Date;

public class GroupUser {
	public GroupUser(int groupUserId, int groupId, int userId, int lastMessageId) {
		super();
		this.groupUserId = groupUserId;
		this.groupId = groupId;
		this.userId = userId;
		this.lastMessageId = lastMessageId;
		this.enteringTime = new Date();
	}
	public GroupUser(int groupId, int userId, int lastMessageId) {
		super();
		this.groupId = groupId;
		this.userId = userId;
		this.lastMessageId = lastMessageId;
		this.enteringTime = new Date();
	}
	public GroupUser(int groupId, int userId) {
		super();
		this.groupId = groupId;
		this.userId = userId;
		this.lastMessageId = 0;
		this.enteringTime = new Date();
	}
	public GroupUser() {
		super();
		
	}
	private int groupUserId;
	private int groupId;
	private int userId;
	private int lastMessageId;
	private Date enteringTime;
	public int getGroupUserId() {
		return groupUserId;
	}
	public void setGroupUserId(int groupUserId) {
		this.groupUserId = groupUserId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getLastMessageId() {
		return lastMessageId;
	}
	public void setLastMessageId(int lastMessageId) {
		this.lastMessageId = lastMessageId;
	}
	public Date getEnteringTime() {
		return enteringTime;
	}
	public void setEnteringTime(Date enteringTime) {
		this.enteringTime = enteringTime;
	}
	
}
