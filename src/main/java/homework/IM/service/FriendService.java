package homework.IM.service;

import java.util.List;

import homework.IM.model.db.User;
import homework.IM.model.returnModel.FriendAdapter;

public interface FriendService {
	User getFriendInfo(int friendId);
	void deleteFriend(int userId,int friendId) throws Exception;
	List<FriendAdapter> getFriendList(int userId);
	void modifyFriendRemark(int relationId,String newRemark);
}
