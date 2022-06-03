package stream;

import java.math.BigDecimal;

public class Name {

	private BigDecimal id ;
	
	private String idStr;
	
	

	public Name(BigDecimal id, String idStr) {
		super();
		this.id = id;
		this.idStr = idStr;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getIdStr() {
		return idStr;
	}

	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}

	@Override
	public String toString() {
		return "Name [id=" + id + ", idStr=" + idStr + "]";
	}
	
	
}
