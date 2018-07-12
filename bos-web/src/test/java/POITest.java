import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

/**
 * 
 * @author 阿荣
 * @Description: 使用Apache POI解析Excel文件
 * @date: 2018年7月12日 下午1:39:31
 */
public class POITest {
	
	//@Test
	public void fun1() throws FileNotFoundException, IOException{
		String path = "D:\\workspace_bos\\bos_parent\\bos-web\\target\\tomcat\\work\\Tomcat\\localhost\\bos\\upload_c2dee49d_d1a8_4ef1_9738_a9052c92c71c_00000002.tmp";
		//包装一个 Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(path)));
		//读取文件中第一个sheet的内容
		HSSFSheet sheetAt = workbook.getSheetAt(0);
		//遍历第一个sheet的所有行
		for(Row row:sheetAt){
			System.out.println();
			//解析每行的单元格
			for(Cell cell:row){
				if(row.getRowNum() == 0){
					continue;
				}
				String value = cell.getStringCellValue();
				System.out.print(value+" ");
			}
		}
	}
}
