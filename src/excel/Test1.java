package excel;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Test1 {
	  /**
	   * @param args
	   */
	  public static void main(String[] args) {
	    // TODO Auto-generated method stub
	    String url = "C:\\Users\\admin\\Desktop\\2022\\aaa.xlsx";

	    try {
	      File file = new File(url);
	      FileOutputStream fileOutputStream = new FileOutputStream(file);
	      //创建工作薄
	      Workbook workbook = new XSSFWorkbook();
	      Sheet sheet = workbook.createSheet();
	      workbook.createSheet();
	      workbook.createSheet();
	      //注意工作表的名字是不能重复的不然就报错啦
	      workbook.setSheetName(0, "口袋里的小龙一号");
	      workbook.setSheetName(1, "口袋里的小龙二号");
	      workbook.setSheetName(2, "口袋里的小龙三号");
	      //复制某一个工作表
	      workbook.cloneSheet(2);
	      //隐藏某一个工作表 第一个参数是你需要隐藏的工作表的序列 第二个是时候隐藏true为隐藏 false为不隐藏
	      //如果你选择不隐藏 这就话就可以不要
	      workbook.setSheetHidden(1, true);
	      //默认选中的工作表
	      workbook.setSelectedTab(3);
	      workbook.write(fileOutputStream);
	      fileOutputStream.close();
	      System.out.println("执行成功！");
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }

	  }
}
