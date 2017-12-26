package homework.IM.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

import homework.IM.common.Output;
import homework.IM.model.db.Group;
import homework.IM.model.db.GroupUser;
import homework.IM.model.returnModel.GroupAdapter;
import homework.IM.model.returnModel.GroupUserAdapter;
import homework.IM.service.GroupService;
import homework.IM.service.UserService;


@Controller
@RequestMapping(value="/group")
public class GroupManagementController {
	@Autowired
	private GroupService groupService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/createGroup")
	public void createGroup(
			@RequestParam("userId")int userId,
			@RequestParam("groupName")String groupName,
			@RequestParam("memberIdList")String memberIdListStr,
			HttpServletResponse response)throws Exception {
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();

		List<Integer> memberIdList = JSONArray.parseArray(memberIdListStr,Integer.class);
		
		try {
			Group newGroup = new Group(userId,groupName);
			groupService.createGroup(newGroup);
			memberIdList.add(newGroup.getManagerId());
			Iterator<Integer> iterator = memberIdList.iterator();
			List<GroupUser> groupUsers = new ArrayList<>();
			while(iterator.hasNext()) {
				groupUsers.add(new GroupUser(newGroup.getGroupId(),iterator.next()));
			}
			List<GroupUserAdapter> groupUserList = groupService.addInitGroupMember(groupUsers,newGroup.getManagerId());
			GroupAdapter groupAdapter = new GroupAdapter(newGroup, userService.getUser(newGroup.getManagerId()), groupUserList);
			data.put("groupInfo", groupAdapter);
			Output.outJson(out, data);
		}catch (Exception e) {
			e.printStackTrace();
			Output.outJson(out, data, "异常", 10002);
		}
	
	}
	
	@RequestMapping("/addMember")
	public void addMember(
			@RequestParam("groupId")int groupId,
			@RequestParam("memberIdList")String memberIdListStr,
			HttpServletResponse response )throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		List<Integer> memberIdList = JSONArray.parseArray(memberIdListStr,Integer.class);
		try {
			data.put("newGroupUser",groupService.addGroupMember(groupId, memberIdList));
			Output.outJson(out, data);
		}catch (Exception e) {
			e.printStackTrace();
			Output.outJson(out, data, "异常", 10002);
		}
	}
	
	@RequestMapping("/deleteMember")
	public void deleteMember(
			@RequestParam("groupId")int groupId,
			@RequestParam("groupMemberId")int groupMemberId,
			HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			groupService.deleteGroupUser(groupId, groupMemberId);
			Output.outJson(out, data);
		}catch (Exception e) {
			e.printStackTrace();
			Output.outJson(out, data, "异常", 10002);
		}
	}
	
	@RequestMapping("/modifyGroupName")
	public void modifyGroupName(
			@RequestParam("groupId")int groupId,
			@RequestParam("groupName")String groupName,
			HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			groupService.modifyGroupName(groupId, groupName);
			Output.outJson(out, data);
		}catch (Exception e) {
			e.printStackTrace();
			Output.outJson(out, data, "异常", 10002);
		}
	}
	
	@RequestMapping("/quitGroup")
	public void quitGroup(@RequestParam("groupId")int groupId,
			@RequestParam("userId")int userId,
			HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			groupService.quitGroup(userId, groupId);
			Output.outJson(out, data);
		}catch (Exception e) {
			e.printStackTrace();
			Output.outJson(out, data, "异常", 10002);
		}
	}
	@RequestMapping("/deleteGroup")
	public void deleteGroup(
			@RequestParam("groupId")int groupId,
			HttpServletResponse response)throws IOException{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			groupService.deleteGroup(groupId);
			Output.outJson(out, data);
		}catch (Exception e) {
			e.printStackTrace();
			Output.outJson(out, data, "异常", 10002);
		}
	}
	@RequestMapping("/getGroupList")
	public void getGroupList(
			@RequestParam("userId")int userId,
			HttpServletResponse response)throws IOException{
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			List<GroupAdapter> myGroupList = groupService.getMyGroupList(userId);
			List<GroupAdapter> otherGroupList = groupService.getOtherGroupList(userId);
			data.put("myGroupList", myGroupList);
			data.put("otherGroupList", otherGroupList);
			Output.outJson(out, data);
		}catch (Exception e) {
			e.printStackTrace();
			Output.outJson(out, data, "异常", 10002);
		}
	}
	
	@RequestMapping("getGroupMemberList")
	public void getGroupMemberList(
			@RequestParam("groupId")int groupId,
			HttpServletResponse response)throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			GroupAdapter group = groupService.getGroupMemberList(groupId);
			data.put("group", group);
			Output.outJson(out, data);
		}catch (Exception e) {
			e.printStackTrace();
			Output.outJson(out, data, "异常", 10002);
		}
	}
}
