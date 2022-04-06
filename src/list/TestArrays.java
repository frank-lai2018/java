package list;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestArrays {

	@Test
	public void test() {
//		int[] arr = {1,2,3};
//		List<int[]> asList = Arrays.asList(arr);
		

		User[] users = {
				new User("1", "frank1"),
				new User("2", "frank2"),
				new User("3", "frank3"),
				new User("4", "frank4"),
				new User("5", "frank5")
		}; 
		
		User[] users1 = {
				new User("1", "frank1"),
				new User("2", "frank2"),
				new User("3", "frank3"),
				new User("4", "frank4"),
				new User("5", "frank5")
		}; 
		
		System.out.println(users == users1);
		System.out.println(Arrays.equals(users, users1));
		
//		System.out.println(Arrays.binarySearch(users, "frank3"));
		
	}

}
