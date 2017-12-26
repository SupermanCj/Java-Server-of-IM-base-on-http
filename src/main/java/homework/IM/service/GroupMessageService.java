package homework.IM.service;

import java.util.List;

import homework.IM.model.db.GroupMessage;
import homework.IM.model.returnModel.GroupMessageAdapter;

public interface GroupMessageService {

	void sendMessage(GroupMessage groupMessage);

	List<GroupMessageAdapter> getGroupHistoryMessage(int userId);

	GroupMessageAdapter getGroupHistoryMessage(int userId, int groupId);

}
