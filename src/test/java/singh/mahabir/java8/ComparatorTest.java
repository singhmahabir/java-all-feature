/**
 * All rights reserved.
 */

package singh.mahabir.java8;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * @author Mahabir Singh
 *
 */
public class ComparatorTest {

    @Test
    public void comparing() {
	Comparator<Person> compareLastNameThenFirstName = Comparator.comparing(Person::getLastName)
		.thenComparing(Person::getFirstName);
	List<String> strings = Arrays.asList("one", "two", "three", "four");
	List<String> list = new ArrayList<>(strings);

	list.sort(Comparator.naturalOrder());

	System.out.println(list);
	assertEquals(4, list.size());
    }

    @Test
    public void reverse() {
	Comparator<Person> compare = Comparator.comparing(Person::getLastName);
	Comparator<Person> reversedComp = compare.reversed();
    }

    @Test
    public void nullFirstAndNulllast() {
	List<Person> list = Arrays.asList(new Person(1, null, "Singh", "Bermo", "India", 20),
		new Person(2, null, "JI", "Bermo", "India", 30),
		new Person(3, "Shayam", "radhe", "Bermo", "India", 17),
		new Person(4, "Om", "sankar", "Bermo", "India", 20));

	// Consider null values lesser than non null values
	list.stream()
		.map(Person::getFirstName)
		.sorted(Comparator.nullsFirst(Comparator.naturalOrder()))
		.forEach(System.out::print);

	System.out.println();
	// Consider null values grater than non null values
	list.stream()
		.map(Person::getFirstName)
		.sorted(Comparator.nullsLast(Comparator.naturalOrder()))
		.forEach(System.out::print);
    }
}
