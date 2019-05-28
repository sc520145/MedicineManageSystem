package mms.controller;

import java.util.Enumeration;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mms.pojo.User;
import mms.services.LoginService;
//澶勭悊鐧婚檰閫昏緫

@RequestMapping("/Login")
@Controller
public class LoginController {
	@Autowired//自动注入一个全局的loginService
	private LoginService loginService;

	/*
	 * 鐢ㄦ埛鐧婚檰 鍒ゆ柇鏄惁瀛樺湪鐢ㄦ埛 瀛樺湪淇濆瓨session
	 */
	@RequestMapping(value = "loginUser", produces = "text/html;charset=UTF-8")
	@ResponseBody//参数由之前的js代码传递过来，参数中还有一个HttpSession现在去具体看这个函数
	public String login(String username, String password, HttpSession session) {
		return loginService.login(username, password, session);//调用loginService的login方法
		//这边返回的是一个字符串，这个字符串共有三种情况，用户不存在和密码错误和空格字符串，此字符串返回给前端的Data变量以执行对应的操作
	}

	// 鍙栧嚭session鐨勭敤鎴峰悕
	@RequestMapping("GetLoginName")
	@ResponseBody
	public Object GetLoginName(HttpSession session) {
		System.out.println("GetLoginName");
		User username = (User)session.getAttribute("user");
		return username;
	}

	// 娓呴櫎session
	@RequestMapping("LogOff")
	@ResponseBody
	public void logOff(HttpSession session) {
		Enumeration em = session.getAttributeNames();
		while (em.hasMoreElements()) {
			session.removeAttribute(em.nextElement().toString());
		}
	}
}
