package com.iteason.bos.web.action.action;

import java.io.IOException;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iteason.domain.Role;
import com.iteason.service.RoleService;
import com.iteason.utils.Java2Json;
import com.iteason.utils.PageBean;
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
	 * @date: 2018年7月20日 下午10:29:24
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException{
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(page);
		pageBean.setPageSize(rows);
		DetachedCriteria dc = DetachedCriteria.forClass(Role.class);
		pageBean.setDc(dc);
		roleService.pageQuery(pageBean);
		Java2Json.ObjectToJson(pageBean,new String[]{"functions","users"});
		return null;
	}
	
	/**
	 * 
	 * @author 阿荣
	 * @Description:复选框
	 * @date: 2018年7月20日 下午11:15:10
	 * @return
	 * @throws IOException 
	 */
	public String listajax() throws IOException{
		List<Role> list = roleService.findAll();
		Java2Json.ArrayToJson(list, new String[]{"functions","users"});
		return null;
	}
	
}
