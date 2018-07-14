package com.iteason.bos.web.action.action;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iteason.domain.Region;
import com.iteason.domain.Subarea;
import com.iteason.service.RegionService;
import com.iteason.utils.PageBean;
import com.iteason.utils.PinYin4jUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


/**
 * 
 * @author 阿荣
 * @Description: 区域管理
 * @date: 2018年7月12日 下午1:30:00
 */
@Controller
@Scope("prototype")
public class RegionAction {
	@Autowired
	private RegionService regionService;
	
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
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String importXls() throws FileNotFoundException, IOException{
		List<Region> regionList = new ArrayList<Region>();
		//使用POI解析Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
		//根据名称获得指定Sheet对象
		HSSFSheet hssfSheet = workbook.getSheet("Sheet1");
		for (Row row : hssfSheet) {
			int rowNum = row.getRowNum();
			if(rowNum == 0){
				continue;
			}
			String id = row.getCell(0).getStringCellValue();
			String province = row.getCell(1).getStringCellValue();
			String city = row.getCell(2).getStringCellValue();
			String district = row.getCell(3).getStringCellValue();
			String postcode = row.getCell(4).getStringCellValue();
			System.out.println(id+province+city);
			//包装一个区域对象
			Region region = new Region(id, province, city, district, postcode, null, null, null);
			
			province = province.substring(0, province.length() - 1);
			city = city.substring(0, city.length() - 1);
			district = district.substring(0, district.length() - 1);
			String info = province + city + district;
			String[] headByString = PinYin4jUtils.getHeadByString(info);
			String shortcode = StringUtils.join(headByString);
			//城市编码---->>shijiazhuang
			String citycode = PinYin4jUtils.hanziToPinyin(city, "");
			
			region.setShortcode(shortcode);
			region.setCitycode(citycode);
			regionList.add(region);
		}
		//批量保存
		regionService.saveBatch(regionList);
		return null;
	}
	
	
	private int page;
	private int rows;
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	/**
	 * 
	 * @author 阿荣
	 * @Description: 分页查询region
	 * @date: 2018年7月12日 下午6:43:56
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException{
		PageBean pageBean = new PageBean();
		
		pageBean.setCurrentPage(page);
		pageBean.setPageSize(rows);
		
		DetachedCriteria dc = DetachedCriteria.forClass(Region.class);
		pageBean.setDc(dc);
		
		pageBean  = regionService.queryPage(pageBean);
		//排除subareas转换json，避免死循环
		JsonConfig config = new JsonConfig();
		String[] exclude = {"subareas"};
		config.setExcludes(exclude);
		//将PageBean转化为json格式
		String json = JSONObject.fromObject(pageBean,config).toString();
		//向客户端写出json
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
		System.out.println("-------------"+json);
		return null;
	}
	
		/**
		 * 
		 * @author 阿荣
		 * @Description: 查询分区的数据的下拉框
		 * @date: 2018年7月12日 下午7:46:50
		 * @return
		 * @throws IOException 
		 */
		public String listajax() throws IOException{
			List<Region> list = regionService.findAll();
			//配置json转换排除subareas避免死循环
			JsonConfig config = new JsonConfig();
			String[] exclude = {"subareas"};
			config.setExcludes(exclude);
			String json = JSONArray.fromObject(list,config).toString();
			//向客户端写出json
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print(json);
			return null;
		}
	
}
