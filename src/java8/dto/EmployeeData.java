package java8.dto;

import java.util.ArrayList;
import java.util.List;
/**
 * 提供用於測試的數據
 * 
 *
 */
public class EmployeeData {
	
	public static List<Employee> getEmployees(){
		List<Employee> list = new ArrayList<>();
		
		list.add(new Employee(1001, "FRANK", 34, 6000.38));
		list.add(new Employee(1002, "APPLE", 12, 9876.12));
		list.add(new Employee(1003, "JENNY", 33, 3000.82));
		list.add(new Employee(1004, "TINA", 26, 7657.37));
		list.add(new Employee(1005, "PEGGY", 65, 5555.32));
		list.add(new Employee(1006, "JIM", 42, 9500.43));
		list.add(new Employee(1007, "TOM", 26, 4333.32));
		
		return list;
	}
	
}
