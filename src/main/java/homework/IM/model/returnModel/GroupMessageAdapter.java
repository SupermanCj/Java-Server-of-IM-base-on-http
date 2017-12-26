package homework.IM.model.returnModel;

import java.util.List;

import homework.IM.model.db.GroupMessage;

public class GroupMessageAdapter {
	public GroupMessageAdapter(int groupId, List<GroupMessage> groupMessage) {
		this.groupId = groupId;
		this.groupMessages = groupMessage;
	}
	//群组消息返回
	private int groupId;
	private List<GroupMessage> groupMessages;
	
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public List<GroupMessage> getGroupMessages() {
		return groupMessages;
	}
	public void setGroupMessages(List<GroupMessage> groupMessages) {
		this.groupMessages = groupMessages;
	}
}
