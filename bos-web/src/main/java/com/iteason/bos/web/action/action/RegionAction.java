package com.iteason.bos.web.action.action;

import java.io.File;

import org.springframework.stereotype.Controller;


/**
 * 
 * @author 阿荣
 * @Description: 区域管理
 * @date: 2018年7月12日 下午1:30:00
 */
@Controller
public class RegionAction {
	//属性驱动接受上传的文件
	private File regionFile;
	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	} 
	/**
	 * 
	 * @author 阿荣
	 * @Description: 导入表格
	 * @date: 2018年7月12日 下午1:31:31
	 * @return
	 */
	public String importXls(){
		return "toRegion";
	}
	
}
