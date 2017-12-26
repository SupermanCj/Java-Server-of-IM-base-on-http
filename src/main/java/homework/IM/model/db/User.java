package homework.IM.model.db;

import java.util.Date;

public class User {
	
	public User(String nickname,String password,int securityQuestionId,String securityQuestionAnswer) {
		this.nickname=nickname;
		this.password=password;
		this.securityQuestionId=securityQuestionId;
		this.securityQuestionAnswer=securityQuestionAnswer;
		this.registrationTime=new Date();
		this.avatar="http://im-1253647932.cosgz.myqcloud.com/avatar/default.png";
	}
	private int userId;
	private String nickname;
	private String password;
	private String avatar;
	private int securityQuestionId;
	private String securityQuestionAnswer;
	private Date registrationTime;
	public User() {
		super();
		
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public void setSecurityQuestionId(int securityQusetionId) {
		this.securityQuestionId = securityQusetionId;
	}
	public String getSecurityQuestionAnswer() {
		return securityQuestionAnswer;
	}
	public void setSecurityQuestionAnswer(String securityQuestionAnswer) {
		this.securityQuestionAnswer = securityQuestionAnswer;
	}
	public Date getRegistrationTime() {
		return registrationTime;
	}
	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}
	
}
