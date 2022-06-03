package excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiUtils {

	/**
	 * @param pathName 
	 * @param sheets Map<String, List<List<String>>>  key=sheetName,內部LIST為每個欄位值，外部LIST為每筆資料
	 */
	public static void writerFile(String pathName, Map<String, List<List<String>>> sheets) {
		File file = new File(pathName);

		try (FileOutputStream fileOutputStream = new FileOutputStream(file); Workbook workbook = new XSSFWorkbook();) {
			Set<String> keySet = sheets.keySet();

			for (String sheetName : keySet) {
				Sheet sheet = workbook.createSheet(sheetName);

				List<List<String>> rows = sheets.get(sheetName);

				for (int i = 0; i < rows.size(); i++) {
					Row row = sheet.createRow(i);// 幾筆資料
					List<String> cells = rows.get(i);
					for (int j = 0; j < cells.size(); j++) {// 每個欄位值
						String value = cells.get(j);
						Cell cell = row.createCell(j);
						cell.setCellValue(value);

					}
				}

			}

			workbook.write(fileOutputStream);
			System.out.println("running success！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
	}
	
	public static List<Cell> readerFile(String pathName) {

		List<Cell> res  = new ArrayList<>();
		try (
				// 建立工作簿
				XSSFWorkbook xssfWorkbook = new XSSFWorkbook(pathName);
				){
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
						res.add(cells.next());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

}
