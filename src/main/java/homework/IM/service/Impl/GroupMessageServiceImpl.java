package homework.IM.service.Impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import homework.IM.common.JPush;
import homework.IM.mapper.GroupMessageMapper;
import homework.IM.mapper.GroupUserMapper;
import homework.IM.model.db.GroupMessage;
import homework.IM.model.db.GroupUser;
import homework.IM.model.returnModel.GroupMessageAdapter;
import homework.IM.service.GroupMessageService;

@Service
public class GroupMessageServiceImpl implements GroupMessageService {

	@Autowired
	private GroupMessageMapper groupMessageMapper;
	@Autowired
	private GroupUserMapper groupUserMapper;
	@Override
	public void sendMessage(GroupMessage groupMessage) {
		//֪ͨ通知群组成员
		
		groupMessageMapper.sendMessage(groupMessage);
		List<Integer> groupUserIdList = groupUserMapper.getGroupUserIdList(groupMessage.getGroupId());
		groupUserIdList.remove(groupMessage.getSenderId());
		List<String> aliases = new ArrayList<>();
		Iterator<Integer> iterator = groupUserIdList.iterator();
		while(iterator.hasNext()) {
			aliases.add(String.valueOf(iterator.next()));
		}
		JPush.sendMessageToAlias(JPush.GROUP_MESSAGE, aliases, "群组消息", groupMessage.getGroupId());
	}

	@Override
	@Transactional
	public List<GroupMessageAdapter> getGroupHistoryMessage(int userId) {
		List<GroupUser> groupUsers = groupUserMapper.getGroupUserWithNewMessageByUserId(userId);
		Iterator<GroupUser> iterator = groupUsers.iterator();
		List<GroupMessageAdapter> groupMessageAdapters = new ArrayList<>();
		while(iterator.hasNext()) {
			GroupUser temp = iterator.next();
			List<GroupMessage> tempList = groupMessageMapper.getHistoryMessage(temp.getGroupId(),temp.getLastMessageId(), temp.getUserId());
			if(tempList!=null&&!tempList.isEmpty()) {
				groupMessageAdapters.add(new GroupMessageAdapter(temp.getGroupId(), tempList));
			}
			groupUserMapper.updateLastMessageId(userId,temp.getGroupId());
		}
		
		return groupMessageAdapters;
	}

	@Override
	public GroupMessageAdapter getGroupHistoryMessage(int userId, int groupId) {

		GroupMessageAdapter groupMessageAdapter = new GroupMessageAdapter(groupId, 
									groupMessageMapper.getGroupMessageByGroupId(userId,groupId));
		groupUserMapper.updateLastMessageId(userId, groupId);
		return groupMessageAdapter;
	}
	
}
