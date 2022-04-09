package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class ReadFileWriteFile {
	
	private static final String distName = "C:\\Users\\admin\\Desktop\\20220408\\eanID111.txt";
	private static final String inputFileName = "C:\\Users\\admin\\Desktop\\20220408\\20220408.txt";

	public static void main(String[] args) throws FileNotFoundException, IOException {
		List<String> readLines = IOUtils.readLines(new FileInputStream(new File(inputFileName)), Charset.defaultCharset());
		System.err.println("total data count="+readLines.size());
		StringBuffer sb = new StringBuffer();
		for(String str:readLines) {
			sb.append("\'").append(str).append("\'").append(",");
		}
//		IOUtils.write(sb, new FileOutputStream(distName), "UTF-8");
		IOUtils.write(sb.toString().getBytes(), new FileOutputStream(distName));
	}
}
