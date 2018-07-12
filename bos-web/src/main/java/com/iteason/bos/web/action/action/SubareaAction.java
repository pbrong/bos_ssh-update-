package com.iteason.bos.web.action.action;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.iteason.domain.Subarea;
import com.iteason.service.SubareaService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class SubareaAction extends ActionSupport implements ModelDriven<Subarea>{

	private Subarea subarea = new Subarea();
	@Override
	public Subarea getModel() {
		return subarea;
	}
	@Autowired
	private SubareaService subareaService;
	
	/**
	 * 
	 * @author 阿荣
	 * @Description: 添加分区
	 * @date: 2018年7月12日 下午8:10:51
	 * @return
	 * @throws IOException
	 */
	public String saveSubarea() throws IOException{
		subareaService.saveSubarea(subarea);
		return "toSubarea";
	}


}
