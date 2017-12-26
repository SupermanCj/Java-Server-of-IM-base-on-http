package homework.IM.service;

import org.springframework.web.multipart.MultipartFile;

import homework.IM.model.db.SecurityQuestion;
import homework.IM.model.db.User;

public interface UserService {
	public boolean addUser(User user);
	public User getUser(int userId);
	public User checkUser(int userId,String password);
	public boolean findPassword(int userId,int securityQuestionId,String securityQuestionAnswer,String newPassword);
	public boolean modifyPassword(int userId,String oldPassword,String newPassword);
	public boolean modifyNickname(int userId,String nickName);
	public String modifyAvatar(int userId, MultipartFile avatar) throws Exception;
	public SecurityQuestion getUserSecurityQuestion(int userId);
}
