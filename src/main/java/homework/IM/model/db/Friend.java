package homework.IM.model.db;

import java.util.Date;

public class Friend {
	public Friend(int relationId, int userId, int friendId) {
		super();
		this.relationId = relationId;
		this.userId = userId;
		this.friendId = friendId;

		this.date = new Date();
	}
	public Friend(int userId, int friendId) {
		super();
		this.remark = "";
		this.userId = userId;
		this.friendId = friendId;
		this.date = new Date();
	}
	public Friend() {
		super();

	}
	
	private int relationId;
	private int userId;
	private int friendId;
	private Date date;
	private String remark;
	public int getRelationId() {
		return relationId;
	}
	public void setRelationId(int relationId) {
		this.relationId = relationId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
