package com.iteason.bos.web.action.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iteason.crm.service.Customer;
import com.iteason.crm.service.CustomerService;
import com.iteason.domain.Decidedzone;
import com.iteason.service.DecidedzoneService;
import com.iteason.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
@Controller
@Scope("prototype")
public class DecidedzoneAction extends ActionSupport implements ModelDriven<Decidedzone> {
	private Decidedzone decidezone =  new Decidedzone();
	@Override
	public Decidedzone getModel() {
		return decidezone;
	}
	
	@Autowired
	private DecidedzoneService decidedzoneService;
	
		//属性驱动，接收多个分区id
		private String[] subareaid;
		public void setSubareaid(String[] subareaid) {
			this.subareaid = subareaid;
		}
		
	@Autowired
	private CustomerService proxy;	
		
		
	
	private int page;//当前页
	private int rows;//页容量
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * 
	 * @author 阿荣
	 * @Description: 定区的分页查询
	 * @date: 2018年7月16日 上午11:55:28
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException{
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(page);
		pageBean.setPageSize(rows);
		DetachedCriteria dc = DetachedCriteria.forClass(Decidedzone.class);
		pageBean.setDc(dc);
		
		decidedzoneService.pageQuery(pageBean);
		
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"currentPage","detachedCriteria","pageSize","subareas","decidedzones"});
		String json = JSONObject.fromObject(pageBean,config).toString();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
		return null;
	}
		
		
		public String addDecidedzone(){
			decidedzoneService.save(decidezone,subareaid);
			return "toDecidedzone";
		}
		
		/**
		 * 
		 * @author 阿荣
		 * @Description:通过webservice，找到crm系统中未关联的客户 
		 * @date: 2018年7月16日 上午11:46:15
		 * @return
		 * @throws IOException 
		 */
		public String findListNotAssociation() throws IOException{
			List<Customer> list = proxy.findListNotAssociation();
			//转换成json格式
			String json = JSONArray.fromObject(list).toString();
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().println(json);
			return null;
		}
}
