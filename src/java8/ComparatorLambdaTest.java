package java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ComparatorLambdaTest {

	public static void main(String[] args) {
		// 匿名類
		Comparator<Apple> byColor = new Comparator<Apple>() {
			@Override
			public int compare(Apple o1, Apple o2) {
				return o1.getColor().compareTo(o2.getColor());
			}
		};

		List<Apple> apples = Arrays.asList(new Apple("red", 110), new Apple("green", 110), new Apple("yellow", 110),
				new Apple("blue", 110), new Apple("white", 110), new Apple("red", 110));

		apples.sort(byColor);
		System.out.println(apples);

		// Lambda
		Comparator<Apple> byColorLambda = (o1, o2) -> o2.getColor().compareTo(o1.getColor());
		apples.sort(byColorLambda);
		System.out.println(apples);
		
	}
}
