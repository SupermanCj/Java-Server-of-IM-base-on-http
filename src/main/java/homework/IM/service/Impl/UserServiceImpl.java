package homework.IM.service.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import homework.IM.common.CosTool;
import homework.IM.mapper.SecurityQuestionMapper;
import homework.IM.mapper.UserMapper;
import homework.IM.model.db.SecurityQuestion;
import homework.IM.model.db.User;
import homework.IM.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private SecurityQuestionMapper securityQuestionMapper;
	
	@Override
	@Transactional
	public boolean addUser(User user) {
		return userMapper.addUser(user);	
	}

	@Override
	public User getUser(int userId) {
		return userMapper.getUser(userId);
	}

	@Override
	public User checkUser(int userId, String password) {
		if(userMapper.getPassword(userId).equals(password)) {
			return userMapper.getUser(userId);
		}
		return null;
	}

	@Override
	public boolean findPassword(int userId, int securityQuestionId, String securityQuestionAnswer,String newPassword) {
		if(userMapper.getUser(userId).getSecurityQuestionAnswer().equals(securityQuestionAnswer)) {
			userMapper.updatePassword(userId, newPassword);
			return true;
		}else {
			return false;
		}
	}

	@Override
	@Transactional
	public boolean modifyPassword(int userId, String oldPassword, String newPassword) {
		if(userMapper.getPassword(userId).equals(oldPassword)) {
			userMapper.updatePassword(userId, newPassword);		
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean modifyNickname(int userId, String nickName) {
		return userMapper.updateNickname(userId, nickName);
	}

	@Override
	@Transactional
	public String modifyAvatar(int userId, MultipartFile avatar) throws Exception {
		String oldAvatarName = avatar.getOriginalFilename();
		String[] subString = oldAvatarName.split("\\.");
		String suffix;
		if(subString.length==0) {
			suffix = "file";
		}else {
			suffix = subString[subString.length-1];
		}
		String newAvatarName = "/avatar/"+String.valueOf(userId)+"_"+ String.valueOf((new Date()).getTime()) +"."+suffix;
		if(CosTool.uploadAvatarPicture(avatar.getBytes(), newAvatarName)) {
			userMapper.updateAvatar(userId, "http://im-1253647932.cosgz.myqcloud.com"+newAvatarName);
			return "http://im-1253647932.cosgz.myqcloud.com"+newAvatarName;
		}else {
			throw new Exception("图片上传失败");
		}
	}

	@Override
	public SecurityQuestion getUserSecurityQuestion(int userId) {
		User user = userMapper.getUser(userId);
		return securityQuestionMapper.getQuestionById(user.getSecurityQuestionId());
	}
	
}
