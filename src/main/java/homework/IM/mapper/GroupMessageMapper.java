package homework.IM.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import homework.IM.model.db.GroupMessage;

public interface GroupMessageMapper {
	public int getGroupLastMessageId(int groupId);

	public void deleteAllGroupMessage(int groupId);

	public void sendMessage(GroupMessage groupMessage);

	public List<GroupMessage> getHistoryMessage(@Param("groupId")int groupId, @Param("lastMessageId")int lastMessageId,@Param("userId")int userId);

	public List<GroupMessage> getLastMessageList(int userId);

	public int getNoReadNum(@Param("groupId")int groupId, @Param("userId")int userId);

	public List<GroupMessage> getGroupMessageByGroupId(@Param("userId")int userId, @Param("groupId")int groupId);

}
