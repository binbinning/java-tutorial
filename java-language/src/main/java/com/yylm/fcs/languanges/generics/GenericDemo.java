package com.yylm.fcs.languanges.generics;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * public class封装generic demo.
 * 
 * Why Generic？
 * <ul>
 * <li>Stronger type checks at compile time.</li>
 * <li>Elimination of casts.</li>
 * <li>Enabling programmers to implement generic algorithms.</li>
 * </ul>
 * 
 * Demos:
 * <li>Generic Types</li>
 * <li>Generic Methods</li>
 * <li>Bounded Type Parameters</li>
 * <li>Type Inference</li>
 * 
 * @see https://docs.oracle.com/javase/tutorial/java/generics/types.html
 *
 */
public class GenericDemo {
	public static void main(String[] args) {

	}
}

/**
 * Generic version of the Box class.
 * 
 * @param <T>
 *            the type of the value being boxed
 */
class Box<T> {
	/**
	 * 
	 * Type Erasure: Replace all type parameters in generic types with their
	 * bounds or Object if the type parameters are unbounded.
	 * 
	 * <pre>
	private T t;
		descriptor: Ljava/lang/Object;
		flags: ACC_PRIVATE
		Signature: #8                           // TT;
	 * </pre>
	 */
	// T stands for "Type"
	private T t;

	public Box() {
		super();
	}

	public Box(T t) {
		super();
		this.t = t;
	}

	public void set(T t) {
		this.t = t;
	}

	public T get() {
		return t;
	}

	/**
	 * Bounded Type Parameters
	 * 
	 * 
	 * Type Erasure: Replace all type parameters in generic types with their
	 * bounds or Object if the type parameters are unbounded.
	 * 
	 * <pre>
	 *  public <U extends java.lang.Number> void inspect(U);
			descriptor: (Ljava/lang/Number;)V
			flags: ACC_PUBLIC
			Signature: #30                          // <U:Ljava/lang/Number;>(TU;)V
	 * </pre>
	 * 
	 * @param u
	 */
	public <U extends Number> void inspect(U u) {
		System.out.println("T: " + t.getClass().getName());
		System.out.println("U: " + u.getClass().getName());
	}

	/**
	 * Generic Methods and Bounded Type Parameters. Bounded type parameters are
	 * key to the implementation of generic algorithms.
	 * 
	 * @param anArray
	 * @param elem
	 * @return
	 */
	public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
		int count = 0;
		for (T e : anArray)
			if (e.compareTo(elem) > 0)
				++count;
		return count;
	}

	public static void main(String[] args) {
		Box<Integer> integerBox = new Box<Integer>(); // Parameterized Types
		integerBox.set(new Integer(10));
		// integerBox.inspect("some text"); // error: this is still String!
	}
}

/**
 * Multiple Type Parameters
 * 
 * @param <K>
 * @param <V>
 */
interface Pair<K, V> {
	public K getKey();

	public V getValue();
}

/**
 * 
 * @param <K>
 * @param <V>
 */
class OrderedPair<K, V> implements Pair<K, V> {

	private K key;
	private V value;

	public OrderedPair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	public static void main(String[] args) {
		// Type Inference.
		Pair<String, Integer> p1 = new OrderedPair<>("Even", 8);
		Pair<String, String> p2 = new OrderedPair<>("hello", "world");
		Pair<String, Box<Integer>> p3 = new OrderedPair<>("primes", new Box<Integer>(10));
		System.out.println(p1.getKey());
		System.out.println(p2.getKey());
		System.out.println(p3.getValue().get());
	}
}

class Util {

	/**
	 * Generic methods are methods that introduce their own type parameters.
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
		return p1.getKey().equals(p2.getKey()) && p1.getValue().equals(p2.getValue());
	}

	public static void main(String[] args) {
		Pair<Integer, String> p1 = new OrderedPair<>(1, "apple");
		Pair<Integer, String> p2 = new OrderedPair<>(2, "pear");
		// boolean same = Util.<Integer, String>compare(p1, p2);
		Util.compare(p1, p2);
	}

}

/**
 * 
 * Generic Methods introduced you to type inference, which enables you to invoke
 * a generic method as you would an ordinary method, without specifying a type
 * between angle brackets.
 * 
 */
class BoxDemo {

	public static <U> void addBox(U u, java.util.List<Box<U>> boxes) {
		Box<U> box = new Box<>();
		box.set(u);
		boxes.add(box);
	}

	public static <U> void outputBoxes(java.util.List<Box<U>> boxes) {
		int counter = 0;
		for (Box<U> box : boxes) {
			U boxContents = box.get();
			System.out.println("Box #" + counter + " contains [" + boxContents.toString() + "]");
			counter++;
		}
	}

	public static void main(String[] args) {
		java.util.ArrayList<Box<Integer>> listOfIntegerBoxes = new java.util.ArrayList<>();
		BoxDemo.<Integer>addBox(Integer.valueOf(10), listOfIntegerBoxes);
		BoxDemo.addBox(Integer.valueOf(20), listOfIntegerBoxes);
		BoxDemo.addBox(Integer.valueOf(30), listOfIntegerBoxes);
		BoxDemo.outputBoxes(listOfIntegerBoxes);

		printCollection(listOfIntegerBoxes);

		/**
		 * wildcards
		 */
		List<Integer> li = Arrays.asList(1, 2, 3);
		System.out.println("sum = " + sumOfList(li));
		System.out.println("sum = " + sumOfList2(li));

		List<Double> ld = Arrays.asList(1.2, 2.3, 3.5);
		System.out.println("sum = " + sumOfList(ld));
		System.out.println("sum = " + sumOfList2(ld));
	}

	public static <T> void printCollection(Collection<T> c) {
		for (T e : c) {
			System.out.println(e);
		}
	}

	public static double sumOfList(List<? extends Number> list) {
		double s = 0.0;
		for (Number n : list)
			s += n.doubleValue();
		return s;
	}

	public static <T extends Number> double sumOfList2(List<T> list) {
		double s = 0.0;
		for (Number n : list)
			s += n.doubleValue();
		return s;
	}

	public static <T extends Number> void process(List<T> list) {
		for (Number elem : list) {
			// ...
			elem.doubleValue();
		}
	}

	public static void process2(List<? extends Number> list) {
		for (Number elem : list) {
			// ...
			elem.doubleValue();
		}
	}
}
