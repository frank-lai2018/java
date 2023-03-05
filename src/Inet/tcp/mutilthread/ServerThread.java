package Inet.tcp.mutilthread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import Inet.tcp.User;

//專門除裡客戶端的請求
public class ServerThread extends Thread{
	Socket s;
	InputStream is=null ;
	ObjectInputStream dis=null ;
	OutputStream os=null ;
	DataOutputStream dos = null;
	
	
	
	public ServerThread(Socket s) {
		this.s = s;
	}



	@Override
	public void run() {
		try {
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
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			
			//2.關閉流 + 關閉網絡資源
			try {
				dos.close();
				os.close();
				dis.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
}
