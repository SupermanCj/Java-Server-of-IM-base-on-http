package homework.IM.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import homework.IM.common.JPush;
import homework.IM.mapper.FriendMapper;
import homework.IM.mapper.FriendRequestMessageMapper;
import homework.IM.mapper.UserMapper;
import homework.IM.model.db.Friend;
import homework.IM.model.db.FriendRequestMessage;
import homework.IM.model.returnModel.FriendAdapter;
import homework.IM.model.returnModel.FriendRequestMessageAdapter;
import homework.IM.service.FriendRequestMessageService;

@Service
public class FriendRequestMessageServiceImpl implements FriendRequestMessageService {

	@Autowired
	public FriendRequestMessageMapper friendRequestMessageMapper;
	@Autowired
	public FriendMapper friendMapper;
	@Autowired
	public UserMapper userMapper;
	
	@Override
	@Transactional(rollbackFor= {Exception.class})
	public boolean addRequestMessage(FriendRequestMessage friendRequestMessage){
			friendRequestMessageMapper.addFriendRequestMessage(friendRequestMessage);
			List<String> aliases = new ArrayList<>();
			aliases.add((new Integer(friendRequestMessage.getRecipientId())).toString());
			if(JPush.sendMessageToAlias(JPush.FRIEND_REQUEST_MESSAGE, aliases, "好友请求消息"))
				Logger.getLogger(getClass()).info("JPush���ͳɹ�");
			return true;
	}

	@Override
	public List<FriendRequestMessage> getRequestMessageBySenderId(int senderId) {

		return friendRequestMessageMapper.getFriendRequestMessageBySenderId(senderId);
	}

	@Override
	public List<FriendRequestMessageAdapter> getRequestMessageByRecipientId(int recipientId) {
			List<FriendRequestMessageAdapter> friendRequestMessagesList = friendRequestMessageMapper.getFriendRequestMessageByRecipientId(recipientId);
			friendRequestMessageMapper.updateIsRead(recipientId);
			return friendRequestMessagesList;		
	}

	@Override
	@Transactional(rollbackFor= {Exception.class})
	public FriendAdapter replyRequestMessage(int requsetId, int userId, int friendId, int reply) throws Exception {
		friendRequestMessageMapper.replyFriendRequestMessage(requsetId, reply);
		if(reply==1) {
			if(!friendMapper.addFriend(new Friend(friendId, userId))) {
				throw new Exception("���ɵ�һ�����ѹ�ϵʧ��");
			}
			Friend friend = new Friend(userId, friendId);
			if(!friendMapper.addFriend(friend)) {
				throw new Exception("���ɵڶ������ѹ�ϵʧ��");
			}
			List<String> aliases = new ArrayList<>();
			aliases.add((new Integer(friendId)).toString());
			JPush.sendMessageToAlias("friendRequestMessage", aliases, "2");
			return new FriendAdapter(friend, userMapper.getUser(friend.getFriendId()));
		}else if(reply==2){
			List<String> aliases = new ArrayList<>();
			aliases.add((new Integer(friendId)).toString());
			JPush.sendMessageToAlias("friendRequestMessage", aliases, "3");
			return null;
		}else {
			return null;
		}
	}

}
