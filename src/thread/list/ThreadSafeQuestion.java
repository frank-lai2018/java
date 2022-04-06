package thread.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadSafeQuestion {
	
	public static void main(String[] args) {
		
		//ArrayList有執行緒安全問題
		//Vector 跟 Collections.synchronizedList可以處理，但這是古老方式
//		List<String> list = new ArrayList<String>();
//		List<String> list = new Vector<String>();
//		List<String> list = Collections.synchronizedList(new ArrayList<String>());
		
		//新的處理方式為使用CopyOnWriteArrayList
		List<String> list = new CopyOnWriteArrayList<String>();
		
		for(int i = 1;i<=10;i++) {
			new Thread(() -> {
				list.add(UUID.randomUUID().toString().substring(0,9));
				System.out.println(list);
			},String.valueOf(i)).start();
		}
	}

}
