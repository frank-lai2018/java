package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

import java8.dto.Employee;
import java8.dto.EmployeeData;

public class StreamCreateTest {
	//創建Stream方式一:通過集合
    @Test
    public void test1(){
        List<Employee> employees = EmployeeData.getEmployees();

//        default Stream<E> stream() : 返回一個順序流
        Stream<Employee> stream = employees.stream();

//        default Stream<E> parallelStream() : 返回一個併行流
        Stream<Employee> parallelStream = employees.parallelStream();

    }

    //創建Stream方式二：通過陣列
    @Test
    public void test2(){
        int[] arr = new int[]{1,2,3,4,5,6};
        //調用Arrays類的static <T> Stream<T> stream(T[] array): 返回一個流
        IntStream stream = Arrays.stream(arr);

        Employee e1 = new Employee(1001,"Tom");
        Employee e2 = new Employee(1002,"Jerry");
        Employee[] arr1 = new Employee[]{e1,e2};
        Stream<Employee> stream1 = Arrays.stream(arr1);

    }
    //創建 Stream方式三：通過Stream的of()
    @Test
    public void test3(){

        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);

    }

    //創見 Stream方式四：創建無限流
    @Test
    public void test4(){

//      跌代
//      public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        //遍歷前10個偶數
    	/*
    	 *     static <T> UnaryOperator<T> identity() {
			        return t -> t;
			    }
    	 * */
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);


//      生成
//      public static<T> Stream<T> generate(Supplier<T> s)
        /*
         * T get();
         * */
        Stream.generate(Math::random).limit(10).forEach(System.out::println);

    }
}
