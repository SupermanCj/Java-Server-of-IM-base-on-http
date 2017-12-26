package homework.IM.mapper;

import org.apache.ibatis.annotations.Param;

import homework.IM.model.db.User;

public interface UserMapper {
	public User getUser(int userId);
	public boolean addUser(User user);
	/*
	public boolean updatePassword(User user);	*/
	public String getPassword(int userId);
	public void updatePassword(@Param("userId")int userId,@Param("newPassword")String newPassword);
	public boolean updateNickname(@Param("userId")int userId,@Param("nickname")String nickname);
	public boolean updateAvatar(@Param("userId")int userId, @Param("avatar")String avatar);
}
