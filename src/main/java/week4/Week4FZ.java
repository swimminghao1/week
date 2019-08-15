package week4;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Week4FZ implements Week4Worker {
	@Override
	public <T> List<Collection<T>> sort(List<Pair<T, T>> relations) {
	/*	Map<T, List<T>> dependencies = relations.stream().collect(Collectors.groupingBy(Pair::getRight, Collectors.mapping(Pair::getLeft, Collectors.toList())));
		relations.stream().map(Pair::getLeft).distinct().forEach(k -> dependencies.putIfAbsent(k, new ArrayList<>()));
		List<Collection<T>> sorted = new ArrayList<>();
		for (; !dependencies.isEmpty(); ) {
			Collection<T> independents = dependencies.entrySet().stream().filter(e -> e.getValue().isEmpty()).map(Map.Entry::getKey).collect(Collectors.toSet());
			if (independents.isEmpty()) {return null;
				throw new RuntimeException("circular dependency: " + dependencies);
			}
			for (T t : independents) {
				dependencies.values().forEach(c -> c.remove(t));
				dependencies.remove(t);
			}
			sorted.add(independents);
		}
		return sorted;*/
		System.out.println("relations1:"+relations);

		Map<T, List<T>> dependencies = new HashMap<>();
		for (Pair<T, T> pair : relations) {
			T left = pair.getLeft();
			dependencies.computeIfAbsent(pair.getRight(), key -> new ArrayList<>()).add(left);
		}
		System.out.println("dependencies:"+dependencies);
		System.out.println("dependencies.values():"+dependencies.values());
		Set<T> uniqueValues = new HashSet<>();
		for (Pair<T, T> relation : relations) {
			T k = relation.getLeft();
			if (uniqueValues.add(k)) {
				dependencies.putIfAbsent(k, new ArrayList<>());
			}
		}
		System.out.println("relations2:"+relations);
		List<Collection<T>> sorted = new ArrayList<>();
		for (; !dependencies.isEmpty(); ) {
			Collection<T> independents = dependencies.entrySet().stream().filter(e -> e.getValue().isEmpty()).map(Map.Entry::getKey).collect(Collectors.toSet());
			if (independents.isEmpty()) {
				throw new RuntimeException("circular dependency: " + dependencies);
			}
			for (T t : independents) {
				dependencies.values().forEach(c -> c.remove(t));
				dependencies.remove(t);
			}
			sorted.add(independents);
		}
		return sorted;
	}
}