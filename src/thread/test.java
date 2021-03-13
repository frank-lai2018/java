package thread;

public class test {
	
	public static void main(String[] args) throws InterruptedException {
		for(int i =0;i<=10;i++) {
			Thread.sleep(15000);
			System.out.println(i);
		}
	}
}
