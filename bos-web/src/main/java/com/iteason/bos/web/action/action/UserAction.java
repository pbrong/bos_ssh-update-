package com.iteason.bos.web.action.action;

import java.io.IOException;

import java.util.List;



import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iteason.bos.web.action.base.BaseAction;
import com.iteason.domain.User;
import com.iteason.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


@Controller
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user = new User();
	//属性驱动，接收页面输入的验证码
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	@Autowired
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
		
	}
	
	/**
	 * 修改当前用户密码
	 * @throws IOException 
	 */
	public String editPassword(){
		String flag = "1";
		//User getUser = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		
		
		User getUser = (User) ActionContext.getContext().getSession().get("loginUser");
		//根据user的id修改密码
		try{
		userService.editPassword(getUser.getId(),user.getPassword());
		}catch(Exception e){
			flag="0";
			e.printStackTrace();
		}
		try {
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	/**
	 * 用户登录
	 */
	public String login(){
		
		//从Session中获取生成的验证码
		String validatecode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		//校验验证码是否输入正确
		if(StringUtils.isNotBlank(checkcode) && checkcode.equals(validatecode)){
			//输入的验证码正确
			User checkUser = userService.login(user);
			if(checkUser != null){
				System.out.println(checkUser.getId()+"'............................id");
				//登录成功,将user对象放入session，跳转到首页
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", checkUser);
				return "home";
			}else{
				//登录失败，,设置提示信息，跳转到登录页面
				//输入的验证码错误,设置提示信息，跳转到登录页面
				this.addActionError("用户名或者密码输入错误！");
				return LOGIN;
			}
		}else{
			//输入的验证码错误,设置提示信息，跳转到登录页面
			this.addActionError("输入的验证码错误！");
			return LOGIN;
		}
	}
	
	/**
	 * 用户注销
	 */
	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();
		return LOGIN;
	}

	@Override
	public User getModel() {
		return user;
	}
}
