package stream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test {

	public static void main(String[] args) {
		OutName outName1 = new OutName(1, new Name(new BigDecimal(1), "1"));

		List<OutName> asList = Arrays.asList(new OutName(1, new Name(new BigDecimal(1), "1")),
				new OutName(3, new Name(new BigDecimal(3), "3")), new OutName(2, new Name(new BigDecimal(12), "2")),
				new OutName(9, new Name(new BigDecimal(9), "9")), new OutName(6, new Name(new BigDecimal(16), "6"))

		);

		System.err.println(asList);
		asList.stream().sorted(Comparator.comparing(new Function<OutName, BigDecimal>() {

			@Override
			public BigDecimal apply(OutName t) {
				// TODO Auto-generated method stub
				return t.getName().getId();
			}

		})).collect(Collectors.toCollection(ArrayList::new)).forEach(System.out::println);
	}
}
