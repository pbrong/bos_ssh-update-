package com.iteason.bos.web.action.action;

import java.io.IOException;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iteason.domain.Staff;
import com.iteason.service.StaffService;
import com.iteason.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
@Controller
@Scope("prototype")
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
		//只转换指定项为json
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"decidedzones"});
		//将PageBean转化为json格式
		String json = JSONObject.fromObject(pageBean,config).toString();
		//向客户端写出json
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
		return NONE;
	}
	
	
	private String ids;
	public void setIds(String ids) {
		this.ids = ids;
	}
	/**
	 * 
	 * @author 阿荣
	 * @Description: 取派员的作废
	 * @date: 2018年7月10日 下午3:54:01
	 * @return
	 */
	@RequiresPermissions("staff-delete")
	public String deleteStaffs(){
		//System.out.println(ids);
		String[] idArray = ids.split(",");
		for(String id:idArray){
			//遍历每个id并且修改这个id的deltag参数为0
			staffService.deleteStaffs(id);
		}
		return "toStaff";
	}
	
	/**
	 * 
	 * @author 阿荣
	 * @Description: 修改取派员信息
	 * @date: 2018年7月12日 上午10:10:56
	 * @return
	 * @throws Exception
	 */
	public String editStaff() throws Exception {
		//查询数据库，根据id查询原始数据
		Staff getStaff = staffService.findById(staff.getId());
		//使用页面提交的数据进行覆盖
		getStaff.setName(staff.getName());
		getStaff.setTelephone(staff.getTelephone());
		getStaff.setStation(staff.getStation());
		getStaff.setHaspda(staff.getHaspda());
		getStaff.setStandard(staff.getStandard());
		//修改取派员
		staffService.update(getStaff);
		return "toStaff";
	}
	
	/**
	 * 
	 * @author 阿荣
	 * @Description: 查询所有的取派员信息并返回json
	 * @date: 2018年7月14日 下午2:46:55
	 * @return
	 * @throws IOException 
	 */
	public String listajax() throws IOException{
		List<Staff> list = staffService.findListNotDelete();
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"decidedzones"});
		String json = JSONArray.fromObject(list,config).toString();
		//json数据回显
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
		return NONE;
	}
}
