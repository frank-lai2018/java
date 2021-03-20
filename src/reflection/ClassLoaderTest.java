package reflection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

public class ClassLoaderTest {
	
	@Test
	public void test1() {
		
	}
	
	@Test
	public void test2() throws Exception {
		Properties prop = new Properties();
		
		//利用FileInputStream獲取InputStream
//		FileInputStream fis = new FileInputStream("src/jdbc.properties");
//		prop.load(fis);
		
		//利用classLoader獲取InputStream，與上差別在於路徑不同
		ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
		InputStream is = classLoader.getResourceAsStream("jdbc.properties");
		prop.load(is);
		
		
		String user = prop.getProperty("user");
		System.out.println(user);
		String password = prop.getProperty("password");
		System.out.println(password);
		
	}

}
