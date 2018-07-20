package com.iteason.bos.web.action.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iteason.domain.Role;
import com.iteason.service.RoleService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller
@Scope("prototype")
public class RoleAction extends ActionSupport implements ModelDriven<Role> {
	private Role role = new Role();
	@Override
	public Role getModel() {
		return role;
	}

	@Autowired
	private RoleService roleService;
	
	private String functionIds;//接受前端传来的功能id
	public String getFunctionIds() {
		return functionIds;
	}
	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}

	/**
	 * 
	 * @author 阿荣
	 * @Description:保存角色Role
	 * @date: 2018年7月20日 下午4:06:10
	 * @return
	 */
	public String save(){
		roleService.save(role,functionIds);
		return "toRole";
	}
	
}
