package homework.IM.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import homework.IM.common.JPush;
import homework.IM.mapper.GroupMapper;
import homework.IM.mapper.GroupMessageMapper;
import homework.IM.mapper.GroupUserMapper;
import homework.IM.mapper.UserMapper;
import homework.IM.model.db.Group;
import homework.IM.model.db.GroupUser;
import homework.IM.model.returnModel.GroupAdapter;
import homework.IM.model.returnModel.GroupUserAdapter;
import homework.IM.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupMapper groupMapper;
	@Autowired
	private GroupUserMapper groupUserMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private GroupMessageMapper groupMessageMapper;
	
	@Override
	@Transactional
	public void createGroup(Group group) {
		groupMapper.createGroup(group);
	}
	
	@Override
	@Transactional
	public List<GroupUserAdapter> addInitGroupMember(List<GroupUser> groupUsers, int managerId) {
		//֪ͨ通知群组成员
		groupUserMapper.addGroupMember(groupUsers);
		Iterator<GroupUser> iterator = groupUsers.iterator();
		List<GroupUserAdapter> groupUserList = new ArrayList<>();
		List<String>aliases = new ArrayList<>();
		while(iterator.hasNext()) {
			GroupUser temp = iterator.next();
			if(temp.getUserId() != managerId)
			groupUserList.add(new GroupUserAdapter(temp, userMapper.getUser(temp.getUserId())));
			aliases.add(String.valueOf(temp.getUserId()));
		}
		JPush.sendMessageToAlias(JPush.GROUP_CHANGE, aliases, "新群");
		return groupUserList;
	}

	@Override
	@Transactional
	public List<GroupUserAdapter> addGroupMember(int groupId, List<Integer> memberIdList) {
		//֪ͨ通知群组成员
		int lastMessageId = groupMessageMapper.getGroupLastMessageId(groupId);
		Iterator<Integer> idIterator = memberIdList.iterator();
		
		List<String>aliases = new ArrayList<>();
		
		List<GroupUser> groupUsers = new ArrayList<>();
		while(idIterator.hasNext()) {
			groupUsers.add(new GroupUser(groupId,idIterator.next(),lastMessageId));
			
		}
		groupUserMapper.addGroupMember(groupUsers);
		Iterator<GroupUser> userIterator = groupUsers.iterator();
		List<GroupUserAdapter> groupUserAdapters = new ArrayList<>();
		while(userIterator.hasNext()) {
			GroupUser temp = userIterator.next();
			groupUserAdapters.add(new GroupUserAdapter(temp, userMapper.getUser(temp.getUserId())));
			
			aliases.add(String.valueOf(temp.getUserId()));
			
		}
		JPush.sendMessageToAlias(JPush.GROUP_CHANGE, aliases, "新群");
		return groupUserAdapters;
	}

	@Override
	public void deleteGroupUser(int groupId, int groupMemberId) {
		//֪ͨ通知被删好友
		List<String> aliases = new ArrayList<>();
		aliases.add(String.valueOf(groupMemberId));
		JPush.sendMessageToAlias(JPush.GROUP_CHANGE, aliases, "被移出群");
		groupUserMapper.deleteGroupUser(groupId, groupMemberId);
		
	}

	@Override
	public void modifyGroupName(int groupId, String groupName) {
		//֪ͨ通知群组成员
		List<Integer> groupUserIdList = groupUserMapper.getGroupUserIdList(groupId);
		groupUserIdList.remove(groupMapper.getManagerId(groupId));
		List<String> aliases = new ArrayList<>();
		Iterator<Integer> iterator = groupUserIdList.iterator();
		while(iterator.hasNext()) {
			aliases.add(String.valueOf(iterator.next()));
		}
		groupMapper.modifyGroupName(groupId, groupName);
		JPush.sendMessageToAlias(JPush.GROUP_CHANGE, aliases, "群名更改");
	}

	@Override
	public void quitGroup(int userId, int groupId) {
		//ͨ通知管理员
		groupUserMapper.deleteGroupUser(groupId, userId);
		List<String> aliases = new ArrayList<>();
		aliases.add(String.valueOf(groupMapper.getManagerId(groupId)));
		JPush.sendMessageToAlias(JPush.GROUP_USER_CHANGE, aliases, "群成员退出");
	}

	@Override
	@Transactional
	public void deleteGroup(int groupId) {
		
		List<Integer> groupUserIdList = groupUserMapper.getGroupUserIdList(groupId);
		groupUserIdList.remove(groupMapper.getManagerId(groupId));
		List<String> aliases = new ArrayList<>();
		Iterator<Integer> iterator = groupUserIdList.iterator();
		while(iterator.hasNext()) {
			aliases.add(String.valueOf(iterator.next()));
		}
		groupUserMapper.deleteAllGroupUser(groupId);
		groupMessageMapper.deleteAllGroupMessage(groupId);
		groupMapper.deleteGroup(groupId);
		//通知群组成员
		JPush.sendMessageToAlias(JPush.GROUP_CHANGE, aliases, "群删除");
	}

	@Override
	public List<GroupAdapter> getMyGroupList(int userId) {
			
		List<GroupAdapter> groupAdapters = GroupAdapter.groupToAdapter(groupMapper.getMyGroupList(userId));
		return groupAdapters;
	}

	@Override
	public List<GroupAdapter> getOtherGroupList(int userId) {
		List<Integer> groupIdList = groupUserMapper.getGroupIdListByUserId(userId);
		if(groupIdList.isEmpty()) {
			return new ArrayList<GroupAdapter>();
		}
		return GroupAdapter.groupToAdapter(groupMapper.getOtherGroupList(userId, groupIdList));
	}

	@Override
	public GroupAdapter getGroupMemberList(int groupId) {
		Group group = groupMapper.getGroupById(groupId);
		List<GroupUser> groupUsers = groupUserMapper.getGroupUserListByGroupId(groupId);
		Iterator<GroupUser> iterator = groupUsers.iterator();
		List<GroupUserAdapter> groupUserAdapters = new ArrayList<>();
		while(iterator.hasNext()) {
			GroupUser temp = iterator.next();
			if(temp.getUserId() == group.getManagerId())continue;
			groupUserAdapters.add(new GroupUserAdapter(temp, userMapper.getUser(temp.getUserId())));
		}
		return new GroupAdapter(group, userMapper.getUser(group.getManagerId()), groupUserAdapters);
	}
}
