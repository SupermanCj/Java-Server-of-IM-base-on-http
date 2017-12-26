package homework.IM.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;

import homework.IM.common.Output;
import homework.IM.model.db.User;
import homework.IM.model.returnModel.FriendMessageAdapter;
import homework.IM.model.returnModel.GroupAdapter;
import homework.IM.model.returnModel.GroupMessageAdapter;
import homework.IM.model.returnModel.UserAdapter;
import homework.IM.service.FriendMessageService;
import homework.IM.service.FriendRequestMessageService;
import homework.IM.service.FriendService;
import homework.IM.service.GroupMessageService;
import homework.IM.service.GroupService;
import homework.IM.service.UserService;


@Controller
@RequestMapping(value="/loginAndcert")
public class LoginAndcertController {
	@Autowired
	private UserService userService;
	@Autowired
	private FriendService friendService;
	@Autowired
	private FriendRequestMessageService friendRequestMessageService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private GroupMessageService groupMessageService;
	@Autowired
	private FriendMessageService friendMessageService;
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public void register(
			@RequestParam("type")String type,
			@RequestParam("nickname")String nickname,
			@RequestParam("password")String password,
			@RequestParam("securityQuestionId")int securityQuetionId,
			@RequestParam("questionAnswer")String questionAnswer,
			HttpServletResponse response)throws Exception {
		PrintWriter out = response.getWriter();
		JSONObject data=new JSONObject();
		User newUser=new User(nickname,password,securityQuetionId,questionAnswer);
		try {
			if(userService.addUser(newUser)) {
				data.put("user",new UserAdapter(newUser));
				Output.outJson(out, data);
			}else {
				throw new Exception("数据库插入失败");
			}		
		}catch (Exception e) {
			e.printStackTrace();
			Output.outJson(out, data, "�쳣", 10002);
		}
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public void login(
			@RequestParam("type")String type,
			@RequestParam("userId")int userId,
			@RequestParam("password")String password,
			HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data=new JSONObject();
		try {
			User user = userService.checkUser(userId, password);
			if(user!=null){
	
				List<GroupAdapter> myGroupList = groupService.getMyGroupList(userId);
				List<GroupAdapter> otherGroupList = groupService.getOtherGroupList(userId);
				data.put("myGroupList", myGroupList);
				data.put("otherGroupList", otherGroupList);
				data.put("user", new UserAdapter(user));
				data.put("friendList", friendService.getFriendList(userId));
				data.put("friendRequestMessage", friendRequestMessageService.getRequestMessageByRecipientId(userId));
				data.put("simpleMessageList", friendMessageService.getSimpleMessageList(userId));
				/*List<GroupMessageAdapter> groupMessages = groupMessageService.getGroupHistoryMessage(userId);
				List<FriendMessageAdapter> friendMessages = friendMessageService.getFriendHistoryMessage(userId);
				data.put("groupMessageList", groupMessages);
				data.put("friendMessageList", friendMessages);*/
				
				Output.outJson(out, data);
			}else {
				Output.outJson(out, data, "密码错误或者没有该用户", 10003);
			}
		}catch(Exception e) {
			e.printStackTrace();
			Output.outJson(out, data, "没有该用户", 10005);
		}
		
	}
	
	@RequestMapping(value="findPassword",method=RequestMethod.POST)
	public void checkUserSecurityQuestion(
			@RequestParam("type")String type,
			@RequestParam("userId")String userId,
			@RequestParam("securityQuestionId")String securityQuestionId,
			@RequestParam("securityQuestionAnswer")String securityQuestionAnswer,
			@RequestParam("newPassword")String newPassword,
			HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data=new JSONObject();
		if(userService.findPassword(Integer.parseInt(userId), Integer.parseInt(securityQuestionId), securityQuestionAnswer, newPassword)) {
			Output.outJson(out, data);
		}else {
			Output.outJson(out, data, "答案错误"	, 10003);
		}
	}
	
	@RequestMapping(value="modifyPassword",method=RequestMethod.POST)
	public void modifyPassword(
			@RequestParam("type")String type,
			@RequestParam("userId")String userId,
			@RequestParam("oldPassword")String oldPassword,
			@RequestParam("newPassword")String newPassword,
			HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();	
		if(userService.modifyPassword(Integer.parseInt(userId), oldPassword, newPassword)) {
			Output.outJson(out, data);
		}else {
			Output.outJson(out, data, "原密码错误", 10003);
		}
	}
	@RequestMapping(value="getUserSecurityQuestion")
	public void getUserSecurityQuestion(
			@RequestParam("userId")int userId,
			HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();	
		try {
			data.put("securityQuestion",userService.getUserSecurityQuestion(userId));
			Output.outJson(out, data);
		}catch (Exception e) {
			e.printStackTrace();
			Output.outJson(out, data, "这尼玛也能出错", 10002);
		}
	}
	@RequestMapping(value="logout",method=RequestMethod.POST)
	public void logout(
			@RequestParam("type")String type,
			@RequestParam("userId")String userId,
			HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();	
		Output.outJson(out, data);
	}
}
