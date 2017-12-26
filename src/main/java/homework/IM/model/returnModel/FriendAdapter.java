package homework.IM.model.returnModel;

import java.util.Iterator;
import java.util.List;

import homework.IM.model.db.Friend;
import homework.IM.model.db.User;

//好友信息
public class FriendAdapter {
	public FriendAdapter() {
		super();

	}
	public static List<FriendAdapter> changeRemark(List<FriendAdapter> list) {
		for(int i=0;i<list.size();i++) {
			FriendAdapter temp =list.get(i);
			if(temp.getRemark().equals(""))
				temp.setRemark(temp.getNickname());
		}
		return list;
	}
	public FriendAdapter(Friend friend,User user) {
		this.relationId=friend.getRelationId();
		this.friendId=friend.getFriendId();
		this.remark=friend.getRemark();
		if(this.remark.equals("")) {
			this.remark=user.getNickname();
		}
		this.nickname = user.getNickname();
		this.avatar = user.getAvatar();
	}
	int relationId;
	int friendId;
	String remark;
	String nickname;
	String avatar;
	public int getRelationId() {
		return relationId;
	}
	public void setRelationId(int relationId) {
		this.relationId = relationId;
	}
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
