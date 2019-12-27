package com.jswl.portal.controller;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jswl.portal.common.util.ResultUtil;
import com.jswl.portal.dto.MenuTreeNodeDto;
import com.jswl.portal.dto.ResultDto;
import com.jswl.portal.entity.User;
import com.jswl.portal.service.MenuService;
import com.jswl.portal.service.UserService;
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
	
	//跳转登录页面
	@RequestMapping("/login")
	public String login() {
		return "admin/login";
	}
	
	//跳转主页登录页面
	@RequestMapping("/index")
	public String index() {
		return "admin/index";
	}
	//跳转登录页面
	@RequestMapping("/header")
	public String header() {
		return "admin/header";
	}
	//跳转登录页面
	@RequestMapping("/left")
	public String left() {
		return "admin/left";
	}
	//跳转登录页面
	@RequestMapping("/main")
	public String main() {
		return "admin/main";
	}
	
	//验证登录信息
	@RequestMapping("/doLogin")
	@ResponseBody
	public ResultDto<String> checkLoginMsg(String userCode,String password,HttpSession session){
		//验证账号密码
		User user = userService.checkUserMsg(userCode, password);
		if (user != null) {
			//说明验证成功
			session.setAttribute("user",user);
			return ResultUtil.success("登录成功");
		}
		//说明验证失败
		return ResultUtil.fail("登录失败");
	}
	
	//主页左侧功能实现
	@RequestMapping("/getMenuByUserId")
	@ResponseBody
	public ResultDto<MenuTreeNodeDto> leftMenu(HttpSession session){
		User user = (User)session.getAttribute("user");
		if (user != null) {
			MenuTreeNodeDto leftMenu = menuService.findMenuInfo(user.getId());
			return ResultUtil.success(leftMenu);
		}
		return ResultUtil.fail();
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/admin/login";
	}
	
	
	
	
	
	
	
	
	
	
	
}
