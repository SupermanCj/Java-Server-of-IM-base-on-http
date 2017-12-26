package homework.IM.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import homework.IM.model.db.FriendMessage;

public interface FriendMessageMapper {

	public void sendMessage(FriendMessage friendMessage);

	public List<FriendMessage> getFriendHistoryMessage(int userId);

	public void updateIsRead(int userId);


	public List<FriendMessage> getLastMessage(int userId);

	public int getNoReadNum(@Param("relationId")int relationId,@Param("userId")int userId);

	public List<FriendMessage> getFriendMessageByFriendId(@Param("userId")int userId, @Param("friendId")int friendId);
	
	public void updateIsReadByFriendId(@Param("userId")int userId, @Param("friendId")int friendId);
}
