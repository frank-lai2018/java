package Inet.tcp;

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
		InputStream is=null ;
		ObjectInputStream dis=null ;
		OutputStream os=null ;
		DataOutputStream dos = null;
		try {
			ss = new ServerSocket(8888);
			//2.等著客戶端發來的信息
			s = ss.accept();//阻塞方法:等待接收客戶端的數據，什麼時候接收到數據，什麼時候程序繼續向下執行
			//accept()返回值回一個Socket，這個Socket其實就是客戶端的Socket
			//接到這個Socket以後，客戶端和服務器才真正產生了連接，才真正可以通信了
			
			//3.感受到的操作流:
			
			is = s.getInputStream();
			
			dis = new ObjectInputStream(is);
			
			//4.讀取客戶端發來的數據
			User res = (User)dis.readObject();
			
			System.out.println("客戶端發來的數據:"+res);
			
			//像客戶端端再發送一句話
			os = s.getOutputStream();
			dos = new DataOutputStream(os);
			
			if("frank".equals(res.getAccount()) && "1234".equals(res.getPassword())) {
				dos.writeBoolean(true);
			}else {
				dos.writeBoolean(false);
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			//2.關閉流 + 關閉網絡資源
			try {
				dos.close();
				os.close();
				dis.close();
				is.close();
				s.close();
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
	}
}
