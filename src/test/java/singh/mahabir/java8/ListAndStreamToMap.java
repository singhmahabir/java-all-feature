/**
 * All rights reserved.
 */
package singh.mahabir.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Map returns a Stream, so it is an intermediary operation
 * 
 * @author Mahabir Singh
 *
 */
public class ListAndStreamToMap {

    static List<Person> list = new ArrayList<>();;

    @BeforeAll
    public static void setup() {
	System.out.println("\n setup is called");
	list.add(new Person(1, "Mahabir", "Singh", "Bermo", "India", 20));
	list.add(new Person(2, "Shaym", "Radhe", "Patna", "US", 20));
	list.add(new Person(3, "Divya", "madhurika", "Patahi", "UK", 17));
    }

    /**
     * org.junit.jupiter.api.Test Use this if you want to use @BeforeAll
     */
    @Test
    public void listToMap() {
	System.out.println(" I am in listToMap Test");
	Assertions.assertEquals(3, list.size(), "size is not same");
	Assertions.assertEquals("Mahabir", list.get(0).getFirstName(), "name is not same");

	Map<Integer, String> map = list.stream().collect(Collectors.toMap(Person::getId, Person::getAddress));
	map.forEach((k, v) -> System.out.println(k + " " + v));
    }

    @Test
    public void listToMap_Using_GroupingBy() {
	System.out.println("I am in listToMap_Using_GroupingBy Test");
	Assertions.assertEquals(3, list.size(), "size is not same");
	Assertions.assertEquals("Mahabir", list.get(0).getFirstName(), "name is not same");

	Map<Integer, List<Person>> map = list.stream()
		.collect(Collectors.groupingBy(Person::getAge));
	map.forEach((k, v) -> System.out.println(k + " " + v));
    }

    @Test
    public void listOfPersonToProperty() {
	list.forEach(person -> System.out.print(" " + person.getFirstName()));
	System.out.println();
	// stream can work only one time
	final Stream<Person> stream = list.stream();

//	final Stream<String> names = stream.map(person -> person.getFirstName());
	final Stream<String> nameStream = stream.map(Person::getFirstName);

	List<String> listOfNames = nameStream.collect(Collectors.toList());
	listOfNames.stream().forEach(System.out::println);

	// size will be same of both List.
	Assertions.assertEquals(list.size(), listOfNames.size());

	list.stream().map(p -> {
	    p.setLastName("changed");
	    return p;
	}).forEach(p -> System.out.println(p.getLastName()));

    }

}
