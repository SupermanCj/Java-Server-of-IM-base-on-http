package homework.IM.model.returnModel;

import java.util.List;

import homework.IM.model.db.FriendMessage;

public class FriendMessageAdapter {
	public FriendMessageAdapter() {
		super();
	}
	public FriendMessageAdapter(Integer friendId,List<FriendMessage> friendMessages) {
		this.friendId = friendId;
		this.friendMessages = friendMessages;
	}
	private int friendId;
	private List<FriendMessage> friendMessages;
	
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public List<FriendMessage> getFriendMessages() {
		return friendMessages;
	}
	public void setFriendMessages(List<FriendMessage> friendMessages) {
		this.friendMessages = friendMessages;
	}

}
