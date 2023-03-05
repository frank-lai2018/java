package Inet.tcp;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = -8771445918327872387L;
	
	private String account;
	private String password;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [account=" + account + ", password=" + password + "]";
	}
	
	
}
