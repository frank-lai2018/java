package Inet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class TCPTest1 {
	
	
	@Test
	public void client() {
		
//		InetAddress inet = InetAddress.getByName("127.0.0.1");
		try (
				Socket socket = new Socket("127.0.0.1",8001);
				OutputStream os =socket.getOutputStream();
				
				) {
			os.write("您好我叫FRANK，謝謝".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	@Test
	public void server() {
		try (
				ServerSocket serverSocket =new ServerSocket(8001);
				Socket socket =serverSocket.accept();
				InputStream is= socket.getInputStream();
				ByteOutputStream bos = new ByteOutputStream();
				
				){
			byte[] buff = new byte[20];
			int len = 0;
			while((len = is.read(buff)) != -1) {
				bos.write(buff, 0, len);
			}
			System.out.println(bos.toString());
		} catch (Exception e) {
			// TODO: handle exception
		} 
	}

}
