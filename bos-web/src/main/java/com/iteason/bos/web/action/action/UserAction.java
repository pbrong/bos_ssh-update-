package com.iteason.bos.web.action.action;

import java.io.IOException;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iteason.bos.web.action.base.BaseAction;
import com.iteason.domain.User;
import com.iteason.service.UserService;
import com.iteason.utils.Java2Json;
import com.iteason.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import freemarker.template.utility.SecurityUtilities;


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
			System.out.println(user.getPassword()+user.getUsername());
			//输入的验证码正确
			//使用shiro框架提供的方式进行认证授权
			Subject subject = SecurityUtils.getSubject();//获得当前用户,状态为未认证
			AuthenticationToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());//创建用户名密码令牌对象
			try{
				subject.login(token);//调用login
			}catch(Exception e){
				this.addActionError("输入的账号或密码错误！");
				e.printStackTrace();
				return LOGIN;
			}
			//没有异常，验证正确，将查询到的user存入session
			//通过subject对象获得绑定在线程上的user
			User getUser = (User) subject.getPrincipal();
			ServletActionContext.getRequest().getSession().setAttribute("loginUser", getUser);
			//获得application域
			Map<String, Object> application = ActionContext.getContext().getApplication();
			//将登陆人数加一存入application域中
			int online_number;
			if(application.get("online_number") == null || (int)application.get("online_number") == 0){
				online_number = 1;
				application.put("online_number",online_number);
			}
			
			User getU = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
			
			if(getU == null && application.get("online_number") != null){
				//没有登陆，可以计数加一
				online_number = (int)application.get("online_number") + 1;
				application.put("online_number",online_number);
				
			}
			
			return "home";
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
		//获得application域
		Map<String, Object> application = ActionContext.getContext().getApplication();
		//将在线人数减一存入application域中
		int online_number;
		if(application.get("online_number") != null){
			online_number = (int)application.get("online_number") - 1;
			application.put("online_number",online_number);
		}
		return LOGIN;
	}

	@Override
	public User getModel() {
		return user;
	}
	
	private int page;//当前页
	private int rows;//页容量
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * 
	 * @author 阿荣
	 * @Description:角色的分页查询
	 * @date: 2018年7月21日 下午12:34:54
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException{
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(page);
		pageBean.setPageSize(rows);
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		pageBean.setDc(dc);
		
		userService.pageQuery(pageBean);
		
		Java2Json.ObjectToJson(pageBean,new String[]{"noticebills","roles"});
		return null;
	}
}
