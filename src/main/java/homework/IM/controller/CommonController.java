package homework.IM.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;

import homework.IM.common.Output;
import homework.IM.service.CommonService;


@Controller
@RequestMapping(value="/Common")
public class CommonController {
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value="/getSecurityQuestionList",method=RequestMethod.POST)
	public void getSecurityQuestionList(
			@RequestParam("type")String type,
			HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		JSONObject data=new JSONObject();
		try {
			data.put("SecurityQuestionList", commonService.getSecurityQuestionList());
			Output.outJson(out, data);
		}catch (Exception e) {
			e.printStackTrace();
			Output.outJson(out, data, "异常", 10002);
		}
	}
}
