package mms.interceptors;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyHandlerInterceptor implements HandlerInterceptor {
	//整个请求处理完毕回调方法，即在视图渲染完毕时回调
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}
	//后处理回调方法，实现处理器的后处理（但在渲染视图之前），此时可以通过modelAndView对模型数据进行处理或对视图进行处理
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}


    /**
     *	 预处理回调方法
     * true表示继续流程, false表示流程中断
     */
	//登录检测，进入处理器检测检测是否登录，如果没有直接返回到登录页面
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("preHandle run");
		HttpSession session = request.getSession();
		Object username = session.getAttribute("user");
		Object password = session.getAttribute("password");
		if (username == null || password == null) {
			System.out.println("logoff inter");
			response.sendRedirect("../login.html");
			System.out.println("##");
			return false;
		}
		return true;
	}

}
