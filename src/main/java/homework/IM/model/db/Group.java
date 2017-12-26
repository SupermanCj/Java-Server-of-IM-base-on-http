package homework.IM.model.db;

import java.util.Date;

public class Group {
	public Group(int groupId, int managerId, String groupName, Date creationTime) {
		super();
		this.groupId = groupId;
		this.managerId = managerId;
		this.groupName = groupName;
		this.creationTime = creationTime;
	}
	public Group(int managerId, String groupName) {
		this.managerId = managerId;
		this.groupName = groupName;
		this.creationTime = new Date();
	}
	public Group() {
		super();
		
	}
	
	private int groupId;
	private int managerId;
	private String groupName;
	private Date creationTime;
	
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
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
}
