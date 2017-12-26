package homework.IM.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;

import homework.IM.common.Output;
import homework.IM.model.db.FriendRequestMessage;
import homework.IM.model.returnModel.FriendAdapter;
import homework.IM.model.returnModel.FriendRequestMessageAdapter;
import homework.IM.service.FriendRequestMessageService;
import homework.IM.service.FriendService;


@Controller
@RequestMapping(value="/friendManagement")
public class FriendManagementController {
	
	@Autowired
	private FriendService friendService;
	@Autowired
	private FriendRequestMessageService friendRequestMessageService;
	
	
	@RequestMapping(value="/modifyFriendRemark")
	public void modifyFriendRemark(
			@RequestParam("relationId")int relationId,
			@RequestParam("remark")String remark,
			HttpServletResponse response
			)throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			friendService.modifyFriendRemark(relationId,remark);
			Output.outJson(out, data);
		}catch(Exception e){
			e.printStackTrace();
			Output.outJson(out, data, "异常", 10002);
		}
	}
	
	@RequestMapping(value="/addFriend")
	public void addFriend(
			@RequestParam("userId")int userId,
			@RequestParam("friendId")int friendId,
			HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			FriendRequestMessage friendRequestMessage = new FriendRequestMessage(friendId, userId);
			if(friendRequestMessageService.addRequestMessage(friendRequestMessage)){
				data.put("friendRequestMessage", friendRequestMessage);
				Output.outJson(out, data);
			}else {
				Output.outJson(out, data, "异常2", 10002);
			}
		}catch(Exception e){
			e.printStackTrace();
			Output.outJson(out, data, "异常", 10002);
		}
	}
	@RequestMapping(value="/replyFriendRequest")
	public void replyFriendRequest(
			@RequestParam("userId")int userId,
			@RequestParam("requestId")int requestId,
			@RequestParam("friendId") int friendId,
			@RequestParam("reply")int reply,
			HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			FriendAdapter friend = friendRequestMessageService.replyRequestMessage(requestId, userId, friendId, reply);
			if(friend==null) {
				Output.outJson(out, data);
			}else {
				data.put("friend", friend);
				Output.outJson(out, data);
			}
		}catch (Exception e) {
			e.printStackTrace();
			Output.outJson(out, data, "异常", 10002);
		}
	}
	
	@RequestMapping(value="/getFriendList")
	public void getFriendList(
			@RequestParam("userId")int userId,
			HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			data.put("friendList", friendService.getFriendList(userId));
			Output.outJson(out, data);
		}catch (Exception e) {
			e.printStackTrace();
			Output.outJson(out, data, "异常", 10002);
		}
	}
	
	@RequestMapping(value="/deleteFriend")
	public void deleteFriend(
			@RequestParam("userId")int userId,
			@RequestParam("friendId")int friendId,
			HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			friendService.deleteFriend(userId, friendId);
			Output.outJson(out, data);
		}catch(Exception e){
			e.printStackTrace();
			Output.outJson(out, data, "异常", 10002);
		}
	}

	@RequestMapping(value="/getFriendRequestMessage")
	public void getFriendRequestMessage(
			@RequestParam("userId")int userId,
			HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			List<FriendRequestMessageAdapter> requestMessageList = friendRequestMessageService.getRequestMessageByRecipientId(userId);
			data.put("requestMessageList", requestMessageList);
			Output.outJson(out, data);
		}catch(Exception e){
			e.printStackTrace();
			Output.outJson(out, data, "异常", 10002);
		}
	}

	@RequestMapping(value="/getMyRequestMessage")
	public void getMyRequestMessage(
			@RequestParam("userId")int userId,
			HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			List<FriendRequestMessage> requestMessageList = friendRequestMessageService.getRequestMessageBySenderId(userId);
			data.put("requestMessageList", requestMessageList);
			Output.outJson(out, data);
		}catch(Exception e){
			e.printStackTrace();
			Output.outJson(out, data, "异常", 10002);
		}
	}
}
