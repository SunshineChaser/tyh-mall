package edu.learning.tyh.sso.controllers;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.learning.tyh.pojo.TyhUsers;
import edu.learning.tyh.sso.interfaces.TyhUserService;

@Controller
@RequestMapping("/user")
public class TyhUserController {

	@Autowired
	private TyhUserService service;
	
	@RequestMapping("/tologinOrRegister")
	public String tologinOrRegister(HttpServletRequest request) {
		//在session中保存进入登录之前的页面
		HttpSession session=request.getSession();
		//保存登录前的页面
		session.setAttribute("prePage", request.getHeader("Referer"));
		return "loginAndRegister/loginAndRegister";
	}
	
	/**
	 * 注册
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping("/register")
	public String reg(TyhUsers user,Model model) {
		user.setUserid(UUID.randomUUID().toString());
		user.setUserstate(1);
		int rtn=service.insertUser(user);
		if(rtn>0) {
			model.addAttribute("msg", "注册用户成功！");
		}else {
			model.addAttribute("msg", "注册用户失败！");
		}
		return "loginAndRegister/loginAndRegister";
	}
	
	/**
	 * 登录
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String login(TyhUsers user,HttpServletRequest request,Model model,HttpServletResponse response) {
		HttpSession session=request.getSession();
		user=service.login(user);
		if(user!=null) {
			model.addAttribute("msg", "登录成功！");
			
			//存cookie（存在跨域的问题，可通过访问同一域名解决，例如访问localhost）
			String token=user.getUserid();
			Cookie cookie=new Cookie("token", token);
			cookie.setPath("/");
			response.addCookie(cookie);
			
			return "redirect:"+session.getAttribute("prePage").toString();
		}else {
			model.addAttribute("msg", "登录失败！");
			return "loginAndRegister/loginAndRegister";
		}
	}
	
	/**
	 * 退出登录
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public void logout(HttpServletRequest request,HttpServletResponse response) {
		Cookie cookies[]=request.getCookies();
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("token")) {
					String userId=cookie.getValue();
					service.logout(userId);
					cookie.setValue(null);
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
					break;
				}
			}
		}
	}
	
	
}
