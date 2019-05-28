package mms.interceptors;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyHandlerInterceptor implements HandlerInterceptor {
	//������������ϻص�������������ͼ��Ⱦ���ʱ�ص�
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}
	//����ص�������ʵ�ִ������ĺ���������Ⱦ��ͼ֮ǰ������ʱ����ͨ��modelAndView��ģ�����ݽ��д�������ͼ���д���
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}


    /**
     *	 Ԥ����ص�����
     * true��ʾ��������, false��ʾ�����ж�
     */
	//��¼��⣬���봦����������Ƿ��¼�����û��ֱ�ӷ��ص���¼ҳ��
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
