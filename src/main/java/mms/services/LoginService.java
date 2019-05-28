package mms.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mms.mapper.UserMapper;
import mms.pojo.User;

@Service
public class LoginService {
	// 娉ㄥ叆userMapper
	@Autowired
	//注入一个uesrMapper为验证做准备
	private UserMapper userMapper;
	//定义login函数，用于登录的验证。返回值是一个字符串
	public String login(String username, String password, HttpSession session) {
		// TODO Auto-generated method stub

		User user = userMapper.queryUserByName(username);
		if (user == null) {//判断用户是否存在
			return "用户名不存在";
		} else if (user.getuPassword().equals(password) == false) {
			return "密码错误";//判断密码是否正确
		} else {
			// 登录成功并且设置session
			session.setAttribute("user", user);
			session.setAttribute("password", password);
			return "";
		}
	}

}
