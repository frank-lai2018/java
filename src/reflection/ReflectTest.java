package reflection;

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
	
	@SuppressWarnings("unchecked")
	@Test
	public void test3() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Class<Person> clazz = Person.class;
		Constructor<Person> constructor =clazz.getConstructor(String.class,int.class);
		Person person = constructor.newInstance("frank",32);
		System.out.println(person);
		
	}
}
