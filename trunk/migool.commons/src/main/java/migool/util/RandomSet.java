package migool.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 
 * @author Denis Migol
 * 
 * @param <E>
 */
public class RandomSet<E> extends LinkedHashSet<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7132440228835780980L;

	public RandomSet() {
		super();
	}

	public RandomSet(int initialCapacity) {
		super(initialCapacity);
	}

	@SuppressWarnings("unchecked")
	private final List<E> getRandomList(Collection<? extends E> c) {
		if (c == null) {
			return null;
		}
		final int size = c.size();
		final E[] elements = (E[]) c.toArray();
		final List<E> ret = new ArrayList<E>();
		if (size > 1) {
			final Random rand = new Random();
			final Set<Integer> indexes = new HashSet<Integer>(size);
			int index = -1;
			for (int i = 0; i < size; i++) {
				do {
					index = rand.nextInt(size);
				} while (indexes.contains(new Integer(index)));
				indexes.add(index);
				ret.add(elements[index]);
			}
		} else if (size == 1) {
			ret.add(elements[0]);
		}
		return ret;
	}

	public RandomSet(Collection<? extends E> c) {
		// super(c);
		super(Math.max(2 * c.size(), 11), .75f);
		addAll(getRandomList(c));
	}

	public RandomSet(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	public static void main(String[] args) {
		final List<String> input = new ArrayList<String>();
		input.add("a");
		input.add("b");
		input.add("c");
		input.add("d");
		input.add("e");

		final RandomSet<String> test = new RandomSet<String>(input);
		for (final String string : test) {
			System.out.println(string);
		}
	}
}
