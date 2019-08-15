package week4;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;
import java.util.stream.Collectors;

@RunWith(Parameterized.class)
public class Week4WorkerTest {
	private Week4Worker worker;

	public Week4WorkerTest(Class<? extends Week4Worker> clazz) throws IllegalAccessException, InstantiationException {
		worker = clazz.newInstance();
	}

	@Parameterized.Parameters(name = "{0}")
	public static Object[][] parameters() {
		return new Object[][] {

				{ Week4XH.class },
				{ Week4FZ.class },
				};
	}

	@Test
	public void test() {
		char first = 'A', last = 'D';
		for (int t = 0; t < 20; t++) {
			List<Pair<String, String>> relations = new ArrayList<>();
			for (char i = first; i <= last; i++) {
				for (char j = first; j <= last; j++) {
					if (i < j && Math.random() < 0.5) {
						relations.add(Pair.of(String.valueOf(i), String.valueOf(j)));
					}
				}
			}
			System.out.println(relations.stream().map(p -> p.getLeft() + "->" + p.getRight()).collect(Collectors.joining(",")));
			List<Collection<String>> sorted = worker.sort(relations);
			Map<String, Integer> order = new HashMap<>();
			for (int i = 0; i < sorted.size(); i++) {
				for (String s : sorted.get(i)) {
					order.put(s, i);
				}
			}
			for (Pair<String, String> pair : relations) {
				Assert.assertTrue(order.get(pair.getKey()).compareTo(order.get(pair.getValue())) < 0);
			}
			System.out.println(sorted.stream().map(Collection::toString).collect(Collectors.joining(" -> ")));
			System.out.println(new String(new char[100]).replace('\0', '-'));
		}
	}
}