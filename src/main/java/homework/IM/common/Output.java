package homework.IM.common;

import java.io.PrintWriter;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;




public class Output {
	public static void outJson(PrintWriter out,JSONObject data,String msg,int status) {
		JSONObject res = new JSONObject();
		res.put("data", data);
		res.put("msg", msg);
		res.put("status", status);
		res.put("time", new Date());
		out.println(res);
		out.flush();
		out.close();
	}
	public static void outJson(PrintWriter out,JSONObject data) {
		JSONObject res = new JSONObject();
		res.put("data", data);
		res.put("msg", "成功");
		res.put("status", 10001);
		res.put("time", new Date());
		out.println(res);
		out.flush();
		out.close();
	}
}
