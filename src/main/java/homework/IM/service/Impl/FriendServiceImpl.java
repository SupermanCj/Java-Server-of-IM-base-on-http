package homework.IM.service.Impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import homework.IM.mapper.FriendMapper;
import homework.IM.mapper.UserMapper;
import homework.IM.model.db.Friend;
import homework.IM.model.db.User;
import homework.IM.model.returnModel.FriendAdapter;
import homework.IM.service.FriendService;

@Service
public class FriendServiceImpl implements FriendService {

	@Autowired
	private FriendMapper friendMapper;
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getFriendInfo(int friendId) {
		//暂不实现
		return null;
	}

	@Override
	@Transactional(rollbackFor= {Exception.class})
	public void deleteFriend(int userId, int friendId)  {
			friendMapper.deleteFriend(userId, friendId);
	}

	@Override
	public List<FriendAdapter> getFriendList(int userId) {
		/*List<Friend> friends = */return FriendAdapter.changeRemark(friendMapper.getFriendList(userId));
		/*Iterator<Friend> friendIterator = friends.iterator();
		List<FriendAdapter> friendAdapters = new ArrayList<>();
		while(friendIterator.hasNext()) {
			Friend temp = friendIterator.next();
			friendAdapters.add(new FriendAdapter(temp, userMapper.getUser(temp.getFriendId())));
		}
		return friendAdapters;*/
	}

	@Override
	@Transactional(rollbackFor= {Exception.class})
	public void modifyFriendRemark(int relationId, String newRemark) {
		friendMapper.updateRemark(relationId, newRemark);
	}

}
