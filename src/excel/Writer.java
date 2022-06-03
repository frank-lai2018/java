package excel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class Writer {
	
	@Test
	public void test() {
	    // TODO Auto-generated method stub
	    String url = "C:\\Users\\admin\\Desktop\\2022\\aaa4.xlsx";

	    try {
	      File file = new File(url);
	      FileOutputStream fileOutputStream = new FileOutputStream(file);
	      //创建工作薄
	      Workbook workbook = new XSSFWorkbook();
	      Sheet first = workbook.createSheet("first");
	      Sheet first1 = workbook.createSheet("first1");
	      Sheet first2 = workbook.createSheet("first2");
	      
	      for(int i = 0;i<5;i++) {//幾筆資料
	    	  Row row = first.createRow(i);
	    	  for(int j = 0;j<=5;j++) {// 每個欄位值
	    		  Cell cell = row.createCell(j);
	    		  cell.setCellValue("first_"+j);
	    		  
	    	  }
	      }
	      
	      for(int i = 0;i<5;i++) {//幾筆資料
	    	  Row row = first1.createRow(i);
	    	  for(int j = 0;j<=5;j++) {// 每個欄位值
	    		  Cell cell = row.createCell(j);
	    		  cell.setCellValue("first1_"+j);
	    		  
	    	  }
	      }
	      
	      for(int i = 0;i<5;i++) {//幾筆資料
	    	  Row row = first2.createRow(i);
	    	  for(int j = 0;j<=5;j++) {// 每個欄位值
	    		  Cell cell = row.createCell(j);
	    		  cell.setCellValue("first2_"+j);
	    		  
	    	  }
	      }
	      
	      workbook.write(fileOutputStream);
	      fileOutputStream.close();
	      System.out.println("执行成功！");
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	}
	
	@Test
	public void test2() {
		String filePath = "C:\\Users\\admin\\Desktop\\2022\\aaa8.xlsx";
		
		String[] header = {
				"符和",
				"PAYEASY",
				"AGODA",
				"PCHOME",
				"MOMO",
		};
		
		List<String> headers = Arrays.asList(header);
		
		Map<String, List<List<String>>> sheets = new HashMap<String, List<List<String>>>();
		
		List<List<String>> rows = new ArrayList<>();
		rows.add(headers);
		List<String> cells = new ArrayList<String>();
		cells.add("");
		cells.add("first1-1111");
		cells.add("first1-22222");
		cells.add("拉拉拉");
		rows.add(cells);
		cells = new ArrayList<String>();
		cells.add("");
		cells.add("first1-3333");
		cells.add("first1-4444");
		cells.add("拉魑魑");
		rows.add(cells);
		sheets.put("first1", rows);
		
		rows = new ArrayList<>();
		rows.add(headers);
		cells = new ArrayList<String>();
		cells.add("first2-1111");
		cells.add("first2-22222");
		cells.add("拉拉拉");
		rows.add(cells);
		cells = new ArrayList<String>();
		cells.add("first2-3333");
		cells.add("first2-4444");
		cells.add("拉魑魑");
		rows.add(cells);
		sheets.put("first2", rows);
		
		PoiUtils.writerFile(filePath, sheets);
		
		
	}

}
