package homework.IM.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import homework.IM.common.Output;
import homework.IM.model.db.User;
import homework.IM.model.returnModel.UserAdapter;
import homework.IM.service.UserService;


@Controller
@RequestMapping(value="/personalManagement")
public class PersonalManagementController {
	@Autowired
	private UserService userService;
	
	//��ȡ������Ϣ
	@RequestMapping(value="getPersonalInformation")
	public void getPersonalInformation(
			@RequestParam("userId")String userId,
			HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		User user = userService.getUser(Integer.parseInt(userId));
		if (user!=null) {
			data.put("user", new UserAdapter(user));
			Output.outJson(out, data);
		}else {
			Output.outJson(out, data, "�����ڸ��û�", 10005);
		}
		
	}
	
	//�޸�����
	@RequestMapping(value="changePassword")
	public void changePassword(
			@RequestParam("userId")String userId,
			@RequestParam("oldPassword")String oldPassword,
			@RequestParam("newPassword")String newPassword,
			HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			userService.modifyPassword(Integer.parseInt(userId), oldPassword, newPassword);
			Output.outJson(out, data);
		}catch (Exception e) {
			e.printStackTrace();
			Output.outJson(out, data, "�쳣", 10002);
		}
	}
	
	//�޸��ǳ�
	@RequestMapping(value="/changeNickname")
	public void changeNickname(
			@RequestParam("userId")String userId,
			@RequestParam("newNickname")String newNickname,
			HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			userService.modifyNickname(Integer.parseInt(userId), newNickname);
			Output.outJson(out, data);
		}catch (Exception e) {
			e.printStackTrace();
			Output.outJson(out, data,"�쳣",10002);
		}
	}
	
	@RequestMapping(value="changeAvatar")
	public void changeAvatar(
			@RequestParam("userId")String userId,
			@RequestParam("avatar")MultipartFile avatar,
			HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			
			String avatarPath = userService.modifyAvatar(Integer.parseInt(userId), avatar);
			data.put("avatarPath", avatarPath);
			Output.outJson(out, data);
		}catch (Exception e) {
			e.printStackTrace();
			Output.outJson(out, data,"�쳣",10002);
		}
	}
	
}
