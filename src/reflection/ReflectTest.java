package reflection;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class ReflectTest {

	@Test
	public void test1() {
		Person person = new Person("frank",32);
		System.out.println(person);
		
		person.age = 35;
		System.out.println(person);
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test2() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Class clazz = Person.class;
		
		//1.通過反射，創建Person類的物件
		Constructor constructor =clazz.getConstructor(String.class,int.class);
		
		Object obj = constructor.newInstance("frank",32);
		
		System.out.println(obj.toString());
		//2.通過反射，調用物件指定的屬性、方法
		//調用屬性
		Field age = clazz.getDeclaredField("age");
		
		age.set(obj, 35);
		
		System.out.println(obj);
		
		//調用方法
		Method show = clazz.getDeclaredMethod("show");
		show.invoke(obj);
		
		//通過反射，可以調用Person類的私有結構的，例如:私有的建構式、方法、屬性
		
		Constructor constructor1 = clazz.getDeclaredConstructor(String.class);
		
		constructor1.setAccessible(true);
		
		Person person = (Person)constructor1.newInstance("frank");
		
		System.out.println(person);
		
		//調用私有屬性
		Field name = clazz.getDeclaredField("name");
		name.setAccessible(true);
		name.set(person, "Tiffany");
		System.out.println(person);
		
		
		//調用私有方法
		
		Method showNation = clazz.getDeclaredMethod("showNation", String.class);
		showNation.setAccessible(true);
		String res = (String)showNation.invoke(person, "台灣");//相當於String str = person.showNation("台灣")
		System.out.println(res);
		
	}
	
	/*
	 * 關於java.lang.Class類的理解
    1.類的加載過程：
	    程序經過javac.exe命令以後，會生成一個或多個字節碼文件(.class結尾)。
	    接著我們使用java.exe命令對某個字節碼文件進行解釋運行。相當於將某個字節碼文件
	    加載到內存中。此過程就稱為類的加載。加載到內存中的類，我們就稱為運行時類，此
	    運行時類，就作為Class的一個實例。
	
	2.換句話說，Class的實例就對應著一個運行時類。
	3.加載到內存中的運行時類，會緩存一定的時間。在此時間之內，我們可以通過不同的方式
	    來獲取此運行時類。
	 * */
	
	@SuppressWarnings("unchecked")
	@Test
	public void test3() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, ClassNotFoundException {
        //方式一：調用運行時類的屬性:.class
        Class clazz1 = Person.class;
        System.out.println(clazz1);
        //方式二：通過運行時類的物件，調用getClass()
        Person p1 = new Person();
        Class clazz2 = p1.getClass();
        System.out.println(clazz2);

        //方式三：調用Class的靜態方法：forName(String classPath)
        Class clazz3 = Class.forName("reflection.Person");
//        clazz3 = Class.forName("java.lang.String");
        System.out.println(clazz3);

        System.out.println(clazz1 == clazz2);
        System.out.println(clazz1 == clazz3);

        //方式四：使用類的加載器：ClassLoader 
        ClassLoader classLoader = ReflectTest.class.getClassLoader();
        Class clazz4 = classLoader.loadClass("reflection.Person");
        System.out.println(clazz4);

        System.out.println(clazz1 == clazz4);
		
	}
	
	
	//Class實例可以是那些結構的說明:
    @Test
    public void test4(){
        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;
        Class c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();
        //只要陣列的元素類型與維度樣，就是同一個Class
        System.out.println(c10 == c11);

    }
}
