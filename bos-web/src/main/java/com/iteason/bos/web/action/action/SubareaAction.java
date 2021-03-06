package com.iteason.bos.web.action.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iteason.domain.Region;
import com.iteason.domain.Subarea;
import com.iteason.service.SubareaService;
import com.iteason.utils.FileUtils;
import com.iteason.utils.Java2Json;
import com.iteason.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
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
	 * @Description: 分区模糊查询
	 * @date: 2018年7月13日 上午10:00:04
	 * @return
	 * @throws IOException 
	 */
	public String queryPage() throws IOException{
		//封装分页查询的pageBean
		PageBean pageBean = new PageBean();
		//封装当前页
		pageBean.setCurrentPage(page);
		//封装页容量
		pageBean.setPageSize(rows);
		//封装查询条件
		DetachedCriteria dc = DetachedCriteria.forClass(Subarea.class);
		
		//动态封装
			//如果关键字不为空
		if(StringUtils.isNotBlank(subarea.getAddresskey())){
			//为dc封装模糊查询
			dc.add(Restrictions.like("addresskey", "%"+subarea.getAddresskey()+"%"));
		}
		
			Region region = subarea.getRegion();
			if(region != null){
				String province = region.getProvince();
				String city = region.getCity();
				String district = region.getDistrict();
				dc.createAlias("region", "r");
				if(StringUtils.isNotBlank(province)){
					//添加过滤条件，根据省份模糊查询-----多表关联查询，使用别名方式实现
					//参数一：分区对象中关联的区域对象属性名称
					//参数二：别名，可以任意
					dc.add(Restrictions.like("r.province", "%"+province+"%"));
				}
				if(StringUtils.isNotBlank(city)){
					//添加过滤条件，根据市模糊查询-----多表关联查询，使用别名方式实现
					//参数一：分区对象中关联的区域对象属性名称
					//参数二：别名，可以任意
					dc.add(Restrictions.like("r.city", "%"+city+"%"));
				}
				if(StringUtils.isNotBlank(district)){
					//添加过滤条件，根据区模糊查询-----多表关联查询，使用别名方式实现
					//参数一：分区对象中关联的区域对象属性名称
					//参数二：别名，可以任意
					dc.add(Restrictions.like("r.district", "%"+district+"%"));
				}
			}
					pageBean.setDc(dc);
				//调用service查询出完整的pageBean
				pageBean = subareaService.queryPage(pageBean);
				//json转换时排除掉decidezone和region属性
				JsonConfig jsonConfig = new JsonConfig();
				String[] excludes = {"decidedzone","subareas","currentPage","dc","pageSize"};
				jsonConfig.setExcludes(excludes);
				String json = JSONObject.fromObject(pageBean,jsonConfig).toString();
				//json数据回显
				ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
				ServletActionContext.getResponse().getWriter().print(json);
				System.out.println("-------------"+json);
				return null;
	}
		
		/**
		 * 
		 * @author 阿荣
		 * @Description: 导出表格
		 * @date: 2018年7月13日 下午3:33:11
		 * @return
		 */
	public String exportXls() throws IOException{
		//第一步：查询所有的分区数据
		List<Subarea> list = subareaService.findAll();
		
		//第二步：使用POI将数据写到Excel文件中
		//在内存中创建一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建一个标签页
		HSSFSheet sheet = workbook.createSheet("分区数据");
		//创建标题行
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("分区编号");
		headRow.createCell(1).setCellValue("开始编号");
		headRow.createCell(2).setCellValue("结束编号");
		headRow.createCell(3).setCellValue("位置信息");
		headRow.createCell(4).setCellValue("省市区");
		
		for (Subarea subarea : list) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getStartnum());
			dataRow.createCell(2).setCellValue(subarea.getEndnum());
			dataRow.createCell(3).setCellValue(subarea.getPosition());
			dataRow.createCell(4).setCellValue(subarea.getRegion().getName());
		}
		
		//第三步：使用输出流进行文件下载（一个流、两个头）
		
		String filename = "分区数据.xls";
		String contentType = ServletActionContext.getServletContext().getMimeType(filename);
		ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
		ServletActionContext.getResponse().setContentType(contentType);
		
		//获取客户端浏览器类型
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		filename = FileUtils.encodeDownloadFilename(filename, agent);
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
		workbook.write(out);
		return NONE;
	}
	/**
	 * 
	 * @author 阿荣
	 * @Description: 查询未关联的分区
	 * @date: 2018年7月14日 下午3:27:36
	 * @return
	 * @throws IOException 
	 */
	public String listajax() throws IOException{
		List<Subarea> list = subareaService.findListNotAssociation();
		JsonConfig jsonConfig = new JsonConfig();
		String[] excludes = {"decidedzone","region"};
		jsonConfig.setExcludes(excludes);
		String json = JSONArray.fromObject(list,jsonConfig).toString();
		//json数据回显
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
		return NONE;
	}
	/**
	 * 
	 * @author 阿荣
	 * @Description:查询图表所需的数据
	 * @date: 2018年7月22日 上午11:58:17
	 * @return
	 * @throws IOException 
	 */
	public String findSubareasGroupByProvince() throws IOException{
		List<Object> list = subareaService.findSubareasGroupByProvince();
		Java2Json.ArrayToJson(list, new String[]{});
		return null;
	}
}
