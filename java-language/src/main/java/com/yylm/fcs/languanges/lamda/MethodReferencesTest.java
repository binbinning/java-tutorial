package com.yylm.fcs.languanges.lamda;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

/**
 * 
 * @author ningjiangbin
 *
 */
public class MethodReferencesTest {

	// The method transferElements copies elements from one collection to
	// another

	public static <T, SOURCE extends Collection<T>, DEST extends Collection<T>> DEST transferElements(
			SOURCE sourceCollection, Supplier<DEST> collectionFactory) {

		DEST result = collectionFactory.get();
		for (T t : sourceCollection) {
			result.add(t);
		}
		return result;
	}

	public static void main(String[] args) {
		List<Person> persons = Person.createRoster();

		System.out.println("initial person list:");
		for (Person person : persons) {
			person.printPerson();
		}
		/**
		 * 匿名内部类实现
		 * 
		 * <pre>
		 * persons.sort(new Comparator<Person>() {
		 * 
		 * 	&#64;Override
		 * 	public int compare(Person o1, Person o2) {
		 * 		return Person.compareByAge(o1, o2);
		 * 	}
		 * 
		 * });
		 * </pre>
		 */

		// lamda实现
		// persons.sort((o1, o2) -> Person.compareByAge(o1, o2));

		// Method reference
		persons.sort(Person::compareByAge);

		System.out.println("current person list:");
		for (Person person : persons) {
			person.printPerson();
		}

		List<Person> roster = Person.createRoster();
//		Set<Person> rosterSetLambda = transferElements(roster, () -> {
//			return new HashSet<>();
//		});

		Set<Person> rosterSet = transferElements(roster, HashSet::new);
		System.out.println("Printing rosterSet:");
		rosterSet.stream().forEach(p -> p.printPerson());
	}
}
