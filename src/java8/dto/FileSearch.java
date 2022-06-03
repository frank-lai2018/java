package java8.dto;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class FileSearch {
	
	public static void main(String[] args) {
		String path = "C:\\Users\\admin\\Desktop\\1213213";
		String path1 = "C:\\Users\\admin\\Desktop\\1231";
		getFolderAllFiles(path);
		File file = new File(path);
//		if(!file.exists()) {
//			file.mkdirs();
//		}else {
//			System.out.println("111111111");
//		}
//		file.deleteOnExit();
	}
	
	public static List<File> getFolderAllFiles(String path) {
		File file = new File(path);
		File[] listFiles = file.listFiles();
		System.out.println("listFiles:"+listFiles);
		System.out.println(Arrays.asList(file.listFiles()));
		List<File> asList = Arrays.asList(file.listFiles());
		List<File> res = asList.stream().filter(list -> {
			System.out.println("list.getName():"+list.getName());
			String extension = FilenameUtils.getExtension(list.getName());
			System.out.println("extension:"+extension);
			if(StringUtils.equals("csv", extension)) {
				return true;
			}
			return false;
		}).collect(Collectors.toList());
		System.out.println("res:"+res);
		res.forEach(filesss -> System.out.println(filesss.getName()));
		res.forEach(filesss -> System.out.println(filesss.getAbsolutePath()));
		return res;
	}

}
