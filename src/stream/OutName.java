package stream;

public class OutName {

	private Integer id;
	
	private Name name;
	
	

	public OutName(Integer id, Name name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "OutName [id=" + id + ", name=" + name + "]";
	}

	
	
}
