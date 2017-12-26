package homework.IM.model.returnModel;

import homework.IM.model.db.GroupUser;
import homework.IM.model.db.User;

//群组成员信息
public class GroupUserAdapter {
	public GroupUserAdapter(GroupUser groupUser, User user) {
		this.groupUserId = groupUser.getGroupUserId();
		this.userId = user.getUserId();
		this.nickName = user.getNickname();
		this.avatar = user.getAvatar();
	}
	private int groupUserId;
	private int userId;
	private String nickName;
	private String avatar;
	public int getGroupUserId() {
		return groupUserId;
	}
	public void setGroupUserId(int groupUserId) {
		this.groupUserId = groupUserId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
}
