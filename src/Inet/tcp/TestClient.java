package Inet.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		//1.創建套接字(Socket):指定服務氣的IP和端口號
		
		Socket s = new Socket("localhost", 8888);
		
		//2.向外發送數據 -->利用輸出流
		OutputStream outputStream = s.getOutputStream();
		
		DataOutputStream dos = new DataOutputStream(outputStream);
		
		//利用OutputStream就可以向外發送數據了，但是沒有直接發送String方法
		//所以又在OutputStream外面套了一個處理流:DataOutputStream
		dos.writeUTF("您好");
		
		//接收服務器端的回話
		InputStream is = s.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		String res = dis.readUTF();
		System.out.println("服務器端為我說:"+res);
		
		//3.關閉流 +關閉網絡資源
		dis.close();
		is.close();
		dos.close();
		outputStream.close();
		s.close();
	}

}
