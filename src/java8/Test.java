package java8;

public class Test {
	public static void main(String[] args) {
		String str = "【快閃晚鳥9折】再露營｜豪華/免裝備露營+賞螢";
		System.out.println(str.replaceAll("//", "\\/"));
	}
}
