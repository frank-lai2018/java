package Inet.tcp.mutilthread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		//1.創建套接字(Socket):指定服務器得端口號
		ServerSocket ss=null;
		Socket s=null;
		int count=0;//用來計算客戶端的請求次數
		try {
			ss = new ServerSocket(8888);
			while(true) {//加入死循環，服務器一直監聽客戶端是否發送數據
				s = ss.accept();
				
				//每次請求要一個thread去執行
				new ServerThread(s).start();
				count++;
				System.out.println("當前是第幾"+count+"個用戶訪問服務器，對應的用戶為:"+s.getInetAddress());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			//2.關閉流 + 關閉網絡資源
			try {
				s.close();
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
	}
}
