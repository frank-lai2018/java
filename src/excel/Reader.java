package excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class Reader {

	@Test
	public void test() {
		// TODO Auto-generated method stub
		String url = "C:\\Users\\admin\\Desktop\\2022\\aaa7.xlsx";

		try {
			// 建立工作簿
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(url);
			System.out.println("xssfWorkbook物件：" + xssfWorkbook);
			// 讀取第一個工作表(這裡的下標與list一樣的，從0開始取，之後的也是如此)
			Iterator<Sheet> sheets = xssfWorkbook.sheetIterator();

			while (sheets.hasNext()) {
				Sheet sheet = sheets.next();
				Iterator<Row> rows = sheet.rowIterator();
				while(rows.hasNext()) {
					Row row = rows.next();
					Iterator<Cell> cells = row.cellIterator();
					while(cells.hasNext()) {
						Cell cell = cells.next();
						String stringCellValue = cell.getStringCellValue();
						System.out.println("111:"+stringCellValue);
					}
				}
			}

			// 獲取第一行的資料
//			XSSFRow row = sheet.getRow(0);
//			System.out.println("row物件：" + row);
//			// 獲取該行第一個單元格的資料
//			XSSFCell cell0 = row.getCell(0);
//			System.out.println("cello物件：" + cell0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test1() {
		String url = "C:\\Users\\admin\\Desktop\\2022\\aaa7.xlsx";
		
		List<Cell> cells = PoiUtils.readerFile(url);
		
		for(Cell cell:cells) {
			System.out.println("cell:"+cell.getStringCellValue());
			
		}

	}

}
