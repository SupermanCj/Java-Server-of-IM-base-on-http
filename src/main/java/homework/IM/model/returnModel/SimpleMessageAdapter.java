package homework.IM.model.returnModel;

import java.util.Date;

import homework.IM.model.db.Friend;
import homework.IM.model.db.FriendMessage;
import homework.IM.model.db.Group;
import homework.IM.model.db.GroupMessage;
import homework.IM.model.db.User;

public class SimpleMessageAdapter {
	public SimpleMessageAdapter() {
		super();
	}
	public SimpleMessageAdapter(GroupMessage groupMessage, int notReadNum, int userId) {
		
		this.id = groupMessage.getGroupId();
		isGroup = true;
		content = groupMessage.getMessage();
		date = groupMessage.getMessageTime();
		this.notReadNum = notReadNum;
		
	}
	public SimpleMessageAdapter(FriendMessage friendMessage, int notReadNum, int userId) {
		if(friendMessage.getRecipientId()==userId) {
			this.id = friendMessage.getSenderId();
		}else {
			this.id =friendMessage.getRecipientId();
		}
		
		isGroup = false;
		content = friendMessage.getMessage();
		date = friendMessage.getMessageTime();
		this.notReadNum = notReadNum;
		
	}
	private int id;
	private boolean isGroup;
	private String content;
	private Date date;
	private int notReadNum;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getNotReadNum() {
		return notReadNum;
	}
	public void setNotReadNum(int notReadNum) {
		this.notReadNum = notReadNum;
	}
	public boolean isGroup() {
		return isGroup;
	}
	public void setGroup(boolean isGroup) {
		this.isGroup = isGroup;
	}
}
