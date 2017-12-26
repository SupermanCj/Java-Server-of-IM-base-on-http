package homework.IM.mapper;

import org.apache.ibatis.annotations.Param;

import homework.IM.model.db.Friend;
import homework.IM.model.returnModel.FriendAdapter;

import java.util.List;

public interface FriendMapper {
	public boolean updateRemark(@Param("relationId")int relationId,@Param("remark")String remark);
	public boolean addFriend(Friend friend);
	public List<FriendAdapter> getFriendList(int userId);
	public void deleteFriend(@Param("userId")int userId, @Param("friendId")int friendId);
	public int getRelationId(@Param("userId")int userId, @Param("friendId")int friendId);
	public int getAllRelationId(@Param("userId")int recipientId, @Param("friendId")int senderId);
	
}
