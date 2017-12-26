package homework.IM.model.returnModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import homework.IM.model.db.Group;
import homework.IM.model.db.User;

//整个群的信息
public class GroupAdapter {
	public GroupAdapter(Group group, User user, List<GroupUserAdapter> groupUserListWithoutManager) {
		this.groupId = group.getGroupId();
		this.managerId = group.getManagerId();
		this.groupName = group.getGroupName();
		this.managerNickname = user.getNickname();
		this.managerAvatar = user.getAvatar();
		this.groupUserList = groupUserListWithoutManager;
	}
	public GroupAdapter(Group group) {
		this.groupId = group.getGroupId();
		this.managerId = group.getManagerId();
		this.groupName = group.getGroupName();
		this.managerNickname = null;
		this.managerAvatar = null;
		this.groupUserList = null;
	}
	static public List<GroupAdapter> groupToAdapter(List<Group> groupList) {
		Iterator<Group> iterator = groupList.iterator();
		List<GroupAdapter> groupAdapterList = new ArrayList<>();
		while(iterator.hasNext()) {
			groupAdapterList.add(new GroupAdapter(iterator.next()));
		}
		return groupAdapterList;
	}
	private int groupId;
	private int managerId;
	private String groupName;
	private String managerNickname;
	private String managerAvatar;
	private List<GroupUserAdapter> groupUserList;
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getManagerNickname() {
		return managerNickname;
	}
	public void setManagerNickname(String managerNickname) {
		this.managerNickname = managerNickname;
	}
	public String getManagerAvatar() {
		return managerAvatar;
	}
	public void setManagerAvatar(String managerAvatar) {
		this.managerAvatar = managerAvatar;
	}
	public List<GroupUserAdapter> getGroupUserList() {
		return groupUserList;
	}
	public void setGroupUserList(List<GroupUserAdapter> groupUserList) {
		this.groupUserList = groupUserList;
	}
}
