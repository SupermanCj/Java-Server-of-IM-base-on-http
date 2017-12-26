package homework.IM.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;

import homework.IM.common.Output;
import homework.IM.model.db.FriendMessage;
import homework.IM.model.db.GroupMessage;
import homework.IM.model.returnModel.FriendMessageAdapter;
import homework.IM.model.returnModel.GroupMessageAdapter;
import homework.IM.model.returnModel.SimpleMessageAdapter;
import homework.IM.service.FriendMessageService;
import homework.IM.service.GroupMessageService;


@Controller
@RequestMapping("/communication")
public class CommunicationController {
	@Autowired
	private GroupMessageService groupMessageService;
	@Autowired 
	private FriendMessageService friendMessageService;
	
	@RequestMapping("sendMessageToFriend")
	public void sendMessageToFriend(
			@RequestParam("userId")int userId,
			@RequestParam("friendId")int friendId,
			@RequestParam("message")String message,
			HttpServletResponse response)throws IOException{  
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			FriendMessage friendMessage = new FriendMessage(friendId, userId, message);
			friendMessageService.sendMessage(friendMessage);
			data.put("messageInfo", friendMessage);
			Output.outJson(out, data);
		}catch (Exception e) {
			e.printStackTrace();
			Output.outJson(out, data, "异常", 10002);
		}
	}
	
	@RequestMapping("/sendMessageToGroup")
	public void sendMessageToGroup(
			@RequestParam("userId")int userId,
			@RequestParam("groupId")int groupId,
			@RequestParam("message")String message,
			HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			GroupMessage groupMessage = new GroupMessage(userId, groupId, message);
			groupMessageService.sendMessage(groupMessage);
			data.put("messageInfo", groupMessage);
			Output.outJson(out, data);
		} catch (Exception e) {
			e.printStackTrace();
			Output.outJson(out, data, "异常", 10002);
		}
	}
	
	@RequestMapping("/getHistoryMessage")
	public void getHistoryMessage(
			@RequestParam("userId")int userId,
			HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			List<GroupMessageAdapter> groupMessages = groupMessageService.getGroupHistoryMessage(userId);
			List<FriendMessageAdapter> friendMessages = friendMessageService.getFriendHistoryMessage(userId);
			data.put("groupMessageList", groupMessages);
			data.put("friendMessageList", friendMessages);
			Output.outJson(out, data);
		} catch (Exception e) {
			e.printStackTrace();
			Output.outJson(out, data, "异常", 10002);
		}
	}
	@RequestMapping("/getSimpleMessage")
	public void getSimpleMessage(
			@RequestParam("userId")int userId,
			HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			List<SimpleMessageAdapter> simpleMessageAdapters = friendMessageService.getSimpleMessageList(userId);
			data.put("simpleMessageList", simpleMessageAdapters);
			Output.outJson(out, data);
		} catch (Exception e) {
			e.printStackTrace();
			Output.outJson(out, data, "异常", 10002);
		
		}
	}
	@RequestMapping("getFriendMessageById")
	public void getFriendMessageByFriendId(
			@RequestParam("userId")int userId,
			@RequestParam("friendId")int friendId,
			HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			FriendMessageAdapter friendMessageAdapter = friendMessageService.getFriendMessageByFriendId(userId,friendId);
			data.put("friendMessage", friendMessageAdapter);
			Output.outJson(out, data);
		} catch (Exception e) {
			e.printStackTrace();
			Output.outJson(out, data, "异常", 10002);
		}
	}
	@RequestMapping("getGroupMessageById")
	public void getGroupMessageById(
			@RequestParam("userId")int userId,
			@RequestParam("groupId")int groupId,
			HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			GroupMessageAdapter groupMessageAdapter = groupMessageService.getGroupHistoryMessage(userId,groupId);
			data.put("groupMessage", groupMessageAdapter);
			Output.outJson(out, data);
		} catch (Exception e) {
			e.printStackTrace();
			Output.outJson(out, data, "异常", 10002);
		}
	}
}
