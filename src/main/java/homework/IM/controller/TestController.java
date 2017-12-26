package homework.IM.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	@RequestMapping("/test")
	public void login(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter printWriter = response.getWriter();
		printWriter.print("success");
		printWriter.flush();
		printWriter.close();
	}
}
