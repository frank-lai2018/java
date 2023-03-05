package Inet.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {

	public static void main(String[] args) throws IOException {
		//1.創建套接字(Socket):指定服務器得端口號
		ServerSocket ss = new ServerSocket(8888);
		
		//2.等著客戶端發來的信息
		Socket s = ss.accept();//阻塞方法:等待接收客戶端的數據，什麼時候接收到數據，什麼時候程序繼續向下執行
		//accept()返回值回一個Socket，這個Socket其實就是客戶端的Socket
		//接到這個Socket以後，客戶端和服務器才真正產生了連接，才真正可以通信了
		
		//3.感受到的操作流:
		
		InputStream is = s.getInputStream();
		
		DataInputStream dis = new DataInputStream(is);
		
		//4.讀取客戶端發來的數據
		String res = dis.readUTF();
		
		System.out.println("客戶端發來的數據:"+res);
		
		//像客戶端端再發送一句話
		OutputStream os = s.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		dos.writeUTF("您好，我是服務器端，我接受到你的請求了");
		
		//2.關閉流 + 關閉網絡資源
		dos.close();
		os.close();
		dis.close();
		is.close();
		s.close();
		ss.close();
		
		
		
	}
}
