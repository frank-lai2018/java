package reflection;

import org.junit.Test;
import reflection.dto.Person;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 獲取運行時類的方法結構
 *
 */
public class MethodTest {

    @Test
    public void test1(){

        Class clazz = Person.class;

        //getMethods():獲取運行時類及其所有的父類中聲名為public的權限的方法
        Method[] methods = clazz.getMethods();
        for(Method m : methods){
            System.out.println(m);
        }
        System.out.println();
        //getDeclaredMethods():獲取當前運行時類中聲名的所有方法。（不包含父類中聲名的方法）
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for(Method m : declaredMethods){
            System.out.println(m);
        }
    }

    /*
    @Xxxx
    權限修飾符 返回值類型 方法名(參數類型1 形參名1,...)throws XxxException{}
     */
    @Test
    public void test2(){
        Class clazz = Person.class;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for(Method m : declaredMethods){
            //1.獲取方法聲明的註解
            Annotation[] annos = m.getAnnotations();
            for(Annotation a : annos){
                System.out.println(a);
            }

            //2.權限修飾符
            System.out.print(Modifier.toString(m.getModifiers()) + "\t");

            //3.返回值類型
            System.out.print(m.getReturnType().getName() + "\t");

            //4.方法名
            System.out.print(m.getName());
            System.out.print("(");
            //5.形參列表
            Class[] parameterTypes = m.getParameterTypes();
            if(!(parameterTypes == null && parameterTypes.length == 0)){
                for(int i = 0;i < parameterTypes.length;i++){

                    if(i == parameterTypes.length - 1){
                        System.out.print(parameterTypes[i].getName() + " args_" + i);
                        break;
                    }

                    System.out.print(parameterTypes[i].getName() + " args_" + i + ",");
                }
            }

            System.out.print(")");

            //6.拋出異常
            Class[] exceptionTypes = m.getExceptionTypes();
            if(exceptionTypes.length > 0){
                System.out.print("throws ");
                for(int i = 0;i < exceptionTypes.length;i++){
                    if(i == exceptionTypes.length - 1){
                        System.out.print(exceptionTypes[i].getName());
                        break;
                    }

                    System.out.print(exceptionTypes[i].getName() + ",");
                }
            }


            System.out.println();
        }



    }
}
