package homework.IM.service;

import java.util.List;

import homework.IM.model.db.FriendMessage;
import homework.IM.model.returnModel.FriendMessageAdapter;
import homework.IM.model.returnModel.SimpleMessageAdapter;

public interface FriendMessageService {

	void sendMessage(FriendMessage friendMessage);

	List<FriendMessageAdapter> getFriendHistoryMessage(int userId);

	List<SimpleMessageAdapter> getSimpleMessageList(int userId);

	FriendMessageAdapter getFriendMessageByFriendId(int userId, int friendId);
	
}
