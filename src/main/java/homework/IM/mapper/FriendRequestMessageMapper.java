package homework.IM.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import homework.IM.model.db.FriendRequestMessage;
import homework.IM.model.returnModel.FriendRequestMessageAdapter;

public interface FriendRequestMessageMapper {
	public void addFriendRequestMessage(FriendRequestMessage friendRequestMessage);
	public void replyFriendRequestMessage(@Param("requestId")int requestId, @Param("reply")int reply);
	public List<FriendRequestMessage> getFriendRequestMessageBySenderId(int senderId);
	public List<FriendRequestMessageAdapter> getFriendRequestMessageByRecipientId(int recipientId);
	public void updateIsRead(int recipientId);
}
