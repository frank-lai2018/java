import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;

public class Test {

	public static void main(String[] args) {
		BigDecimal bd = new BigDecimal("121.126456789012456");
		BigDecimal setScale = bd.setScale(2, RoundingMode.FLOOR);
		System.out.println(bd);
		System.out.println(setScale);
		
	}
}
