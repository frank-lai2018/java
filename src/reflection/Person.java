package reflection;


public class Person {
	private String name;
	public int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	private Person(String name) {
		super();
		this.name = name;
	}
	public Person() {
		super();
	}
	
	public void show() {
		System.out.println("您好，我是一個人...");
	}
	
	private String showNation(String nation) {
		return "拉拉拉"+nation;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
	
}
