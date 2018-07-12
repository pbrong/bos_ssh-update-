package com.iteason.bos.web.action.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.iteason.domain.Region;
import com.iteason.service.RegionService;


/**
 * 
 * @author 阿荣
 * @Description: 区域管理
 * @date: 2018年7月12日 下午1:30:00
 */
@Controller
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
		//创建集合存储region对象
		List<Region> regionList = new ArrayList<Region>();
		
		//使用POI解析Excel文件
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(regionFile));
		//根据名称获得第一个sheet
		HSSFSheet sheetAt = hssfWorkbook.getSheetAt(0);
		//获得每行对象
		for(Row row:sheetAt){
			//标题行去掉
			if(row.getRowNum() == 0){
				continue;
			}else{
				//遍历行内的每个单元格
				for(Cell cell:row){
					String id = row.getCell(0).getStringCellValue();
					String province = row.getCell(1).getStringCellValue();
					String city = row.getCell(2).getStringCellValue();
					String district = row.getCell(3).getStringCellValue();
					String postcode = row.getCell(4).getStringCellValue();
					//包装一个区域对象
					Region region = new Region(id, province, city, district, postcode, null, null, null);
					regionList.add(region);
				}
			}
		}
		//批量保存，不要频繁地开事务
		regionService.saveBatch(regionList);
		
		return "toRegion";
	}
	
}
