package homework.IM.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import homework.IM.model.db.GroupUser;

public interface GroupUserMapper {
	public void addGroupMember(List<GroupUser> groupUsers);

	public void deleteGroupUser(int groupId, int groupMemberId);

	public void deleteAllGroupUser(int groupId);

	public List<Integer> getGroupUserIdList(int groupId);

	public List<Integer> getGroupIdListByUserId(int userId);

	public List<GroupUser> getGroupUserListByGroupId(int groupId);

	public List<GroupUser> getGroupUserWithNewMessageByUserId(int userId);

	public void updateLastMessageId(@Param("userId")int userId, @Param("groupId")int groupId);
}
