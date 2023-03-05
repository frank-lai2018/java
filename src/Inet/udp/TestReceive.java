package Inet.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

//接收方
public class TestReceive {

	public static void main(String[] args) {
		System.out.println("使用者B上線");
		
		//1.準備套接字: 指定接收方的端口號
		DatagramSocket ds = null;
		try {
			ds = new DatagramSocket(9999);
			while(true) {
				//2.有一個空的數據包，打算用來接收對方傳送過來的數據包
				byte[] b = new byte[1024];
				DatagramPacket dp = new DatagramPacket(b, b.length);
				
				//3.接收對方的數據包，然後放入我們的dp數據包填充
				ds.receive(dp);//接收完以後dp 裡面就填充好內容了
				
				//4.取出數據
				byte[] data = dp.getData();
				String s = new String(data,0,dp.getLength());
				System.out.println("使用者A說:"+s);
				
				if("byebye".equals(s)) {
					System.out.println("使用者B下線");
					break;
				}
				
				//使用者B進行回復
				//1.準備套接字: 指定發送方的端口號
				Scanner sc = new Scanner(System.in);
				System.out.println("使用者B:");
				String str = sc.next();
				
				/**
				 * 需要4個參數
				 * 1.指的是傳送數據轉為字節陣列
				 * 2.字節陣列的長度
				 * 3.封裝接收方的IP
				 * 4.指定接收方的端口號
				 * 
				 * */
				DatagramPacket dp2 = new DatagramPacket(str.getBytes(), str.getBytes().length, InetAddress.getByName("localhost"), 8888);
				
				ds.send(dp2);
				
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//5.關閉資源
			ds.close();
		}
		

		
		
		
	}
}
