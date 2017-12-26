package homework.IM.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AccessInterceptor implements HandlerInterceptor {
	
	private Logger logger = Logger.getLogger(getClass()) ;
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {


	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2) throws Exception {
		String addr = request.getRemoteAddr();
		String url = request.getRequestURI();
		String userId = request.getParameter("userId");
		if(userId!=null) {
			logger.info(userId+" access "+url+" at "+addr);
		}else {
			logger.info(" 000000 "+" access "+url+" at "+addr);
		}
		return true;
	}

}
