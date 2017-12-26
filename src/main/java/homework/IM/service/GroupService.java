package homework.IM.service;

import java.util.List;

import homework.IM.model.db.Group;
import homework.IM.model.db.GroupUser;
import homework.IM.model.returnModel.GroupAdapter;
import homework.IM.model.returnModel.GroupUserAdapter;

public interface GroupService {

	public void createGroup(Group group);

	public List<GroupUserAdapter> addInitGroupMember(List<GroupUser> groupUsers, int managerId);
	
	public List<GroupUserAdapter> addGroupMember(int groupId, List<Integer> memberIdList);

	public void deleteGroupUser(int groupId, int groupMemberId);

	public void modifyGroupName(int groupId, String groupName);

	public void quitGroup(int userId, int groupId);

	public void deleteGroup(int groupId);

	public List<GroupAdapter> getMyGroupList(int userId);

	public List<GroupAdapter> getOtherGroupList(int userId);

	public GroupAdapter getGroupMemberList(int groupId);

}
