package com.iteason.bos.web.action.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iteason.domain.Function;
import com.iteason.service.FunctionService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
/**
 * 
 * @author 阿荣
 * @Description:权限action
 * @date: 2018年7月19日 下午2:30:32
 */
public class FunctionAction extends ActionSupport implements ModelDriven<Function> {

	private Function function = new Function();
	@Override
	public Function getModel() {
		return function;
	}
	
	@Autowired
	private FunctionService functionService;
	/**
	 * 
	 * @author 阿荣
	 * @Description:查询全部权限名
	 * @date: 2018年7月19日 下午2:31:06
	 * @return
	 * @throws IOException 
	 */
	public String listajax() throws IOException{
		List<Function> list = functionService.findAll();
		//json配置排除
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"parentFunction","roles","children"});
		//转json
		String json = JSONArray.fromObject(list,config).toString();
		//输出到前台
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
		return null;
	}
	/**
	 * 
	 * @author 阿荣
	 * @Description:添加权限
	 * @date: 2018年7月19日 下午4:13:40
	 * @return
	 */
	public String add(){
		functionService.save(function);
		return "toFunction";
	}
}
