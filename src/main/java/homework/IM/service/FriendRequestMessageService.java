package homework.IM.service;

import java.util.List;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import homework.IM.model.db.FriendRequestMessage;
import homework.IM.model.returnModel.FriendAdapter;
import homework.IM.model.returnModel.FriendRequestMessageAdapter;

public interface FriendRequestMessageService {
	public boolean addRequestMessage(FriendRequestMessage friendRequestMessage) throws APIConnectionException, APIRequestException;
	public List<FriendRequestMessage> getRequestMessageBySenderId(int senderId);
	public List<FriendRequestMessageAdapter> getRequestMessageByRecipientId(int recipientId);
	public FriendAdapter replyRequestMessage(int messageId,int userId, int friendId,int reply) throws Exception;
}
