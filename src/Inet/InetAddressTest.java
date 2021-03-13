package Inet;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
	
	public static void main(String[] args) {
		try {
			InetAddress it1 = InetAddress.getLocalHost();
			System.out.println(it1);
			System.out.println(it1.getHostName());
			
			InetAddress it2 = InetAddress.getByName("www.google.com");
			System.out.println(it2);
			InetAddress it3 = InetAddress.getByName("www.payeasy.com.tw");
			System.out.println(it3);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
