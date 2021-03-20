package reflection;

import reflection.dto.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 調用運行時類中指定的結構:屬性、方法、建構式
 *
 */
public class ReflectionTest {

    @Test
    public void testField() throws Exception {
        Class clazz = Person.class;

        //創建運行時類的物件
        Person p = (Person) clazz.newInstance();

        //獲取指定的屬性:要求運行時類中屬性聲明為public
        //通常不採用此方法
        Field id = clazz.getField("id");

        /*
        設置當前屬性的值

        set():參數1:指名設置哪個物件的屬性 參數2:將此屬性質設置為多少
         */

        id.set(p,1001);

        /*
        獲取當前屬性的值
        get():參數1:獲取哪個物件的當前屬性值
         */
        int pId = (int) id.get(p);
        System.out.println(pId);


    }
    /*
    如何操作運行時類中的指定的屬性--需要掌握
     */
    @Test
    public void testField1() throws Exception {
        Class clazz = Person.class;

        //創建運行時類的物件
        Person p = (Person) clazz.newInstance();

      //1. getDeclaredField(String fieldName):獲取運行時類中指定變量名的屬性
        Field name = clazz.getDeclaredField("name");

        //2.保證當前屬性是可訪問的
        name.setAccessible(true);
        //3.獲取、設置指定物件的此屬性值
        name.set(p,"Tom");

        System.out.println(name.get(p));
    }

    /*
    如何操作運行時類中的指定的方法-
     */
    @Test
    public void testMethod() throws Exception {

        Class clazz = Person.class;

        //創建運行時類的物件
        Person p = (Person) clazz.newInstance();

        /*
        1.獲取指定的否個方法
        getDeclaredMethod():參數1:指名獲取的方法的名稱 參數2:指名獲取的方法的型參列表
         */
        Method show = clazz.getDeclaredMethod("show", String.class);
        //2.保證當前方法是可訪問的
        show.setAccessible(true);

        /*
        3.調用方法的invoke():參數1方法的調用者 參數2:給方法型參賦值的實參
        invoke() 的返回值即為對應類中調用的方法的返回值。
         */
        Object returnValue = show.invoke(p,"CHN"); //String nation = p.show("CHN");
        System.out.println(returnValue);

        System.out.println("*************如何調用靜態方法*****************");

        // private static void showDesc()

        Method showDesc = clazz.getDeclaredMethod("showDesc");
        showDesc.setAccessible(true);
        //如果調用的運行時類中的方法沒有返回值，則此invoke()返回null
//        Object returnVal = showDesc.invoke(null);
        Object returnVal = showDesc.invoke(Person.class);
        System.out.println(returnVal);//null

    }

    /*
    如何調用運行時的指定的建構式
     */
    @Test
    public void testConstructor() throws Exception {
        Class clazz = Person.class;

        //private Person(String name)
        /*
        1.獲取指定的建構式
        getDeclaredConstructor():參數:指名建構式的參數列表
         */

        Constructor constructor = clazz.getDeclaredConstructor(String.class);

        //2.保證此建構式是可訪問的
        constructor.setAccessible(true);

        //3.調用此建構式創建運行時類的物件
        Person per = (Person) constructor.newInstance("Tom");
        System.out.println(per);

    }

}
