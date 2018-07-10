package com.iteason.bos.web.action.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.iteason.domain.Staff;
import com.iteason.service.StaffService;
import com.iteason.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Controller
public class StaffAction extends ActionSupport implements ModelDriven<Staff> {
	private Staff staff = new Staff();
	@Override
	public Staff getModel() {
		return staff;
	}
	
	@Autowired
	private StaffService staffService;
	/**
	 * 增加取派员staffService
	 * @return
	 * @throws Exception
	 */
	public String addStaff() throws Exception {
		//保存取派员
		staffService.addStaff(staff);
		return "toStaff";
	}

	
	
	private int page;//从前端传过来的当前页
	private int rows;//页面容量
	public int getPage() {
		return page;
	}
	public int getRows() {
		return rows;
	}
	/**
	 * 
	 * @author 阿荣
	 * @Description: 返回staff的分页数据
	 * @date: 2018年7月10日 下午2:22:13
	 * @return
	 * @throws IOException 
	 */
	public String queryPage() throws IOException{
		PageBean pageBean = new PageBean();
		//封装pageBean数据
		pageBean.setCurrentPage(page);
		pageBean.setPageSize(rows);
		//创建离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Staff.class);
		pageBean.setDc(dc);
		
		pageBean  = staffService.queryPage(pageBean);
		//将PageBean转化为json格式
		String json = JSONObject.fromObject(pageBean).toString();
		//向客户端写出json
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
		
		return NONE;
	}
	
}
