package reflection;

import org.junit.Test;
import reflection.dto.Person;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 獲取當前運行時類的屬性結構
 *
 */
public class FieldTest {

    @Test
    public void test1(){

        Class clazz = Person.class;

        //獲取屬性結構
        //getFields():獲取當前運行時類及其父類中聲名為public訪問權限的屬性
        Field[] fields = clazz.getFields();
        for(Field f : fields){
            System.out.println(f);
        }
        System.out.println();
        //getDeclaredFields():獲取當前運行時類中聲名的所有屬性。(不包含父類中聲名的屬性\)
        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field f : declaredFields){
            System.out.println(f);
        }
    }

    //權限修飾符 數據類型 變量名
    @Test
    public void test2(){
        Class clazz = Person.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field f : declaredFields){
            //1.權限修飾符
            int modifier = f.getModifiers();
            System.out.print(Modifier.toString(modifier) + "\t");

            //2.數據類型
            Class type = f.getType();
            System.out.print(type.getName() + "\t");

            //3.變量名
            String fName = f.getName();
            System.out.print(fName);

            System.out.println();
        }


    }


}
