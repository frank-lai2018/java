package reflection;

import org.junit.Test;
import reflection.dto.Person;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class OtherTest {

    /*
    獲取建構式結構

     */
    @Test
    public void test1(){

        Class clazz = Person.class;
        //getConstructors():獲取當前運行時類中聲明為public的建構式
        Constructor[] constructors = clazz.getConstructors();
        for(Constructor c : constructors){
            System.out.println(c);
        }

        System.out.println();
        //getDeclaredConstructors():獲取當前運行時類中聲明的所有建構式
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for(Constructor c : declaredConstructors){
            System.out.println(c);
        }

    }

    /*
    獲取運行時類的父類

     */
    @Test
    public void test2(){
        Class clazz = Person.class;

        Class superclass = clazz.getSuperclass();
        System.out.println(superclass);
    }

    /*
    獲取運行時類的帶泛型中的父類

     */
    @Test
    public void test3(){
        Class clazz = Person.class;

        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println(genericSuperclass);
    }

    /*
    獲取運行時類的帶泛型中的父類的泛型

     */
    @Test
    public void test4(){
        Class clazz = Person.class;

        Type genericSuperclass = clazz.getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;
        //獲取泛型類型
        Type[] actualTypeArguments = paramType.getActualTypeArguments();
//        System.out.println(actualTypeArguments[0].getTypeName());
        System.out.println(((Class)actualTypeArguments[0]).getName());
    }

    /*
    獲取運行時類實現的接口
     */
    @Test
    public void test5(){
        Class clazz = Person.class;

        Class[] interfaces = clazz.getInterfaces();
        for(Class c : interfaces){
            System.out.println(c);
        }

        System.out.println();
        //獲取運行時類的父類實現的接口
        Class[] interfaces1 = clazz.getSuperclass().getInterfaces();
        for(Class c : interfaces1){
            System.out.println(c);
        }

    }
    /*
獲取運行時類所在的包
     */
    @Test
    public void test6(){
        Class clazz = Person.class;

        Package pack = clazz.getPackage();
        System.out.println(pack);
    }

    /*
        獲取運行時聲明的註解

     */
    @Test
    public void test7(){
        Class clazz = Person.class;

        Annotation[] annotations = clazz.getAnnotations();
        for(Annotation annos : annotations){
            System.out.println(annos);
        }
    }

}
