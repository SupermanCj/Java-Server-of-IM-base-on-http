package homework.IM.service.Impl;

import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jpush.api.push.model.Message;
import homework.IM.common.Constant;
import homework.IM.common.JPush;
import homework.IM.mapper.FriendMapper;
import homework.IM.mapper.FriendMessageMapper;
import homework.IM.mapper.GroupMapper;
import homework.IM.mapper.GroupMessageMapper;
import homework.IM.mapper.GroupUserMapper;
import homework.IM.mapper.UserMapper;
import homework.IM.model.db.FriendMessage;
import homework.IM.model.db.GroupMessage;
import homework.IM.model.returnModel.FriendMessageAdapter;
import homework.IM.model.returnModel.SimpleMessageAdapter;
import homework.IM.service.FriendMessageService;

@Service
public class FriendMessageServiceImpl implements FriendMessageService {
	@Autowired 
	private FriendMessageMapper friendMessageMapper;
	@Autowired
	private FriendMapper friendMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private GroupMessageMapper groupMessageMapper;
	@Autowired
	private GroupUserMapper groupUserMapper;
	@Autowired
	private GroupMapper groupMapper;
	@Override
	public void sendMessage(FriendMessage friendMessage) {

		List<String> alias = new ArrayList<String>();
		alias.add(String.valueOf(friendMessage.getRecipientId()));
		friendMessage.setRelationId(friendMapper.getAllRelationId(friendMessage.getRecipientId(),friendMessage.getSenderId()));
		friendMessageMapper.sendMessage(friendMessage);
		JPush.sendMessageToAlias(JPush.FRIEND_MESSAGE, alias, "好友消息", friendMessage.getSenderId());
		
		
	}

	@Override
	@Transactional
	public List<FriendMessageAdapter> getFriendHistoryMessage(int userId) {
		List<FriendMessage> friendMessages = friendMessageMapper.getFriendHistoryMessage(userId);
		List<FriendMessageAdapter> friendMessageAdapters = new ArrayList<>();
		Iterator<FriendMessage> iterator = friendMessages.iterator();
		Map<Integer, List<FriendMessage>> friendMessageMap = new ConcurrentSkipListMap<Integer, List<FriendMessage>>();
		while(iterator.hasNext()) {
			FriendMessage temp = iterator.next();
			List<FriendMessage> friendMessageList;
			if(friendMessageMap.containsKey(temp.getSenderId())) {
				friendMessageList=friendMessageMap.get(temp.getSenderId());
			}else {
				friendMessageList = new ArrayList<>();
			}
			friendMessageList.add(temp);
			friendMessageMap.put(temp.getSenderId(), friendMessageList);
		}
		
		
		Iterator<Entry<Integer, List<FriendMessage>>> keyIterator = friendMessageMap.entrySet().iterator();
		
		while(keyIterator.hasNext()) {
			Entry<Integer,List<FriendMessage>> temp = keyIterator.next();
			friendMessageAdapters.add(new FriendMessageAdapter(temp.getKey(), temp.getValue()));
			friendMessageMap.remove(temp.getKey());
		}
		friendMessageMapper.updateIsRead(userId);
		return friendMessageAdapters;
	}

	@Override
	public List<SimpleMessageAdapter> getSimpleMessageList(int userId) {
		List<SimpleMessageAdapter> simpleMessageAdapters = new ArrayList<>();
		//好友
		List<FriendMessage> friendMessages = friendMessageMapper.getLastMessage(userId);
		Iterator<FriendMessage> friendMessageIterator = friendMessages.iterator();
		while(friendMessageIterator.hasNext()) {
			FriendMessage temp = friendMessageIterator.next();
			int noReadNum = friendMessageMapper.getNoReadNum(temp.getRelationId(),userId);
			simpleMessageAdapters.add(new SimpleMessageAdapter(temp,noReadNum,userId));
		}
		
		List<GroupMessage> groupMessages = groupMessageMapper.getLastMessageList(userId);
		Iterator<GroupMessage> groupMessageIterator = groupMessages.iterator();
		while(groupMessageIterator.hasNext()) {
			GroupMessage temp = groupMessageIterator.next();
			int noReadNum = groupMessageMapper.getNoReadNum(temp.getGroupId(),userId);
			simpleMessageAdapters.add(new SimpleMessageAdapter(temp, noReadNum, userId));
		}
		return simpleMessageAdapters;
	}

	@Override
	public FriendMessageAdapter getFriendMessageByFriendId(int userId, int friendId) {
		FriendMessageAdapter temp = 
				new FriendMessageAdapter(friendId, friendMessageMapper.getFriendMessageByFriendId(userId, friendId));
		friendMessageMapper.updateIsReadByFriendId(userId, friendId);
		return temp;
	}
	
	
}
