package mms.controller;

import java.util.Enumeration;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mms.pojo.User;
import mms.services.LoginService;
//处理登陆逻辑

@RequestMapping("/Login")
@Controller
public class LoginController {
	@Autowired//�Զ�ע��һ��ȫ�ֵ�loginService
	private LoginService loginService;

	/*
	 * 用户登陆 判断是否存在用户 存在保存session
	 */
	@RequestMapping(value = "loginUser", produces = "text/html;charset=UTF-8")
	@ResponseBody//������֮ǰ��js���봫�ݹ����������л���һ��HttpSession����ȥ���忴�������
	public String login(String username, String password, HttpSession session) {
		return loginService.login(username, password, session);//����loginService��login����
		//��߷��ص���һ���ַ���������ַ�����������������û������ں��������Ϳո��ַ��������ַ������ظ�ǰ�˵�Data������ִ�ж�Ӧ�Ĳ���
	}

	// 取出session的用户名
	@RequestMapping("GetLoginName")
	@ResponseBody
	public Object GetLoginName(HttpSession session) {
		System.out.println("GetLoginName");
		User username = (User)session.getAttribute("user");
		return username;
	}

	// 清除session
	@RequestMapping("LogOff")
	@ResponseBody
	public void logOff(HttpSession session) {
		Enumeration em = session.getAttributeNames();
		while (em.hasMoreElements()) {
			session.removeAttribute(em.nextElement().toString());
		}
	}
}
