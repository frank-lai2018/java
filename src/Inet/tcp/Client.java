package Inet.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		//1.創建套接字(Socket):指定服務氣的IP和端口號
		
		Socket s=null;
		Scanner sc=null;
		OutputStream outputStream=null ;
		ObjectOutputStream dos=null ;
		InputStream is =null;
		DataInputStream dis =null;
	
		try {
			s = new Socket("localhost", 8888);
			sc = new Scanner(System.in);
			System.out.println("請輸入您的帳號:");
			String account = sc.next();
			System.out.println("請輸入您的密碼:");
			String pwd = sc.next();
			
			User user = new User();
			user.setAccount(account);
			user.setPassword(pwd);
			
			//2.向外發送數據 -->利用輸出流
			outputStream = s.getOutputStream();
			
			dos = new ObjectOutputStream(outputStream);
			
			//利用OutputStream就可以向外發送數據了，但是沒有直接發送String方法
			//所以又在OutputStream外面套了一個處理流:DataOutputStream
			dos.writeObject(user);
			
			//接收服務器端的回話
			 is = s.getInputStream();
			 dis = new DataInputStream(is);
			boolean res = dis.readBoolean();
			if(res) {
				
				System.out.println("登入成功...");
			}else {
				System.out.println("登入失敗...");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			
			//3.關閉流 +關閉網絡資源
			try {
				dis.close();
				is.close();
				dos.close();
				outputStream.close();
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
