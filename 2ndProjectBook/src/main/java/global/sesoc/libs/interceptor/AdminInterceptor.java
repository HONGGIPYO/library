package global.sesoc.libs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");
		if (loginId != null) {
			if (loginId.equals("admin")) {
				return super.preHandle(request, response, handler);
			} else {
				response.sendRedirect(request.getContextPath() + "/selectBook");
				return false;
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/selectBook");
			return false;
		}
		
	}
	
	


}
