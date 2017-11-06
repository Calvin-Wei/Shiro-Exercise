package com.wxc.shiro.test.handlers;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wxc.shiro.test.service.ShiroService;

@Controller
@RequestMapping("/shiro")
public class ShiroHandler {
	@Autowired
	private ShiroService shiroService;

	@RequestMapping("/testShiroAnnotation")
	public String testShiroAnnotation(HttpSession session) {
		session.setAttribute("key", "value12345");
		shiroService.testMethod();
		return "redirect:/list.jsp";
	}
	@RequestMapping("/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
		Subject currentUser=SecurityUtils.getSubject();
		if(!currentUser.isAuthenticated()) {
			//把用户名和密码封装为UsernamePasswordToken对象
			UsernamePasswordToken token=new UsernamePasswordToken(username,password);
			//rememberme
			token.setRememberMe(true);
			try {
				System.out.println("1. "+token.hashCode());
				// 执行登录
				currentUser.login(token);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/list.jsp";
	}

}
