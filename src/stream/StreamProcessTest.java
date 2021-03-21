package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import java8.dto.Employee;
import java8.dto.EmployeeData;

/**
 * 測試Stream的中間操作
 * */
public class StreamProcessTest {
    //1-篩選其切片
    @Test
    public void test1(){
        List<Employee> list = EmployeeData.getEmployees();
//        filter(Predicate p)——接收 Lambda ，從流中排除某些元素。
        Stream<Employee> stream = list.stream();
        stream.filter(e -> e.getSalary() > 7000).forEach(System.out::println);

        System.out.println();
//        limit(n)——截斷流，使其元素不操過給定的數量。
        list.stream().limit(3).forEach(System.out::println);
        System.out.println();

//        skip(n) —— 跳過元素，返回一個排除前N個元素的流\。若流中元素不足N個，則返回一個空流。與 limit(n) 互補
        list.stream().skip(30).forEach(System.out::println);

        System.out.println();
//        distinct()——篩選，通過流所生成元素的 hashCode() 和 equals() 去除重複元素

        list.add(new Employee(1010,"MEIMEI",40,8000));
        list.add(new Employee(1010,"MEIMEI",41,8000));
        list.add(new Employee(1010,"MEIMEI",40,8000));
        list.add(new Employee(1010,"MEIMEI",40,8000));
        list.add(new Employee(1010,"MEIMEI",40,8000));

//        System.out.println(list);

        list.stream().distinct().forEach(System.out::println);
    }

    //映射
    @Test
    public void test2(){
//        map(Function f)——接收一個函數作為參數，將元素轉換成其他形式或提取信息，該函數會被應用到每個元素上，并將其映射成一個新的元素。
        /**
         * 	a=[1, 2, 3]
			b=[4, 5, 6]
			a+b=[1, 2, 3, [4, 5, 6]]
         * 
         * */
    	
    	List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);

        List<Employee> employees = EmployeeData.getEmployees();
        Stream<String> namesStream = employees.stream().map(Employee::getName);
        namesStream.filter(name -> name.length() > 3).forEach(System.out::println);
        System.out.println();
        
        Stream<Stream<Character>> streamStream = list.stream().map(StreamProcessTest::fromStringToStream);
        streamStream.forEach(s ->{
            s.forEach(System.out::println);
        });
        System.out.println();
//        flatMap(Function f)——接收一個函數作為參數，將流中的每個值都換成另一個流，然後把所有流連接成一個流。
        /**
         * 	a=[1, 2, 3]
			b=[4, 5, 6]
			a+b=[1, 2, 3, 4, 5, 6]
         * 
         * */
        Stream<Character> characterStream = list.stream().flatMap(StreamProcessTest::fromStringToStream);
        characterStream.forEach(System.out::println);

    }

    public static Stream<Character> fromStringToStream(String str){//aa
        ArrayList<Character> list = new ArrayList<>();
        for(Character c : str.toCharArray()){
            list.add(c);
        }
       return list.stream();

    }



    @Test
    public void test3(){
        ArrayList list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        System.out.println(list1);
        
        ArrayList list2 = new ArrayList();
        list2.add(4);
        list2.add(5);
        list2.add(6);
        System.out.println(list2);

//        list1.add(list2);
        list1.addAll(list2);
        System.out.println(list1);

    }

    //3-排序
    @Test
    public void test4(){
//        sorted()——自然排序
        List<Integer> list = Arrays.asList(12, 43, 65, 34, 87, 0, -98, 7);
        list.stream().sorted().forEach(System.out::println);
        //拋異常，原因:Employee沒有實現Comparable接口
//        List<Employee> employees = EmployeeData.getEmployees();
//        employees.stream().sorted().forEach(System.out::println);


//        sorted(Comparator com)——訂製排序

        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().sorted( (e1,e2) -> {

           int ageValue = Integer.compare(e1.getAge(),e2.getAge());
           if(ageValue != 0){
               return ageValue;
           }else{
               return -Double.compare(e1.getSalary(),e2.getSalary());
           }

        }).forEach(System.out::println);
    }

}
