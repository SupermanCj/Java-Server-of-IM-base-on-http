package homework.IM.model.returnModel;

import homework.IM.model.db.User;

//用户信息
public class UserAdapter {
	public UserAdapter(User user) {
		this.userId=user.getUserId();
		this.nickname=user.getNickname();
		this.avatar = user.getAvatar();
		this.securityQuestionId=user.getSecurityQuestionId();
	}
	public UserAdapter() {
		super();
		
	}
	private int userId;
	private String nickname;
	private String avatar;
	private int securityQuestionId;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public int getSecurityQuestionId() {
		return securityQuestionId;
	}
	public void setSecurityQuestionId(int securityQuestionId) {
		this.securityQuestionId = securityQuestionId;
	}
}
