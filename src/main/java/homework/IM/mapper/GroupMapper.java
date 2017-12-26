package homework.IM.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import homework.IM.model.db.Group;

public interface GroupMapper {
	public void createGroup(Group group);
	public void modifyGroupName(@Param("groupId")int groupId, @Param("groupName")String groupName);
	public void deleteGroup(int groupId);
	public int getManagerId(int groupId);
	public List<Group> getMyGroupList(@Param("userId")int userId);
	public List<Group> getOtherGroupList(@Param("userId")int userId, @Param("groupIdList")List<Integer> groupIdList);
	public Group getGroupById(int groupId);
	
}
