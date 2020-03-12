/**
 * All rights reserved.
 */

package singh.mahabir.java8;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

/**
 * <ul>
 * <li>
 * <p>
 * Stream is a java typed<T> interface.</li>
 * 
 * <li>
 * <p>
 * A Stream can't be <<reused>> once it have been used to process a set of data
 * , it cannot be used again to process another set</li>
 * 
 * <li>
 * <p>
 * There is no limit on the amount of data that can be process</li>
 * 
 * <li>
 * <p>
 * It does not hold any data and it should not be change the data it
 * process.</li>
 * 
 * <li>
 * <p>
 * The call to filter and any other method is lazy call And all the methods of
 * stream that return another stream are lazy</li>
 * 
 * <li>
 * <p>
 * An operation on a stream that return a stream is called an intermediary
 * operation which is a declarative operation that does not do anything</li>
 * 
 * <li>
 * <p>
 * Peek and filter are intermediary operation whereas forEach is final
 * operation</li>
 * </ul>
 * 
 * @author Mahabir Singh
 *
 */
public class StreamTest {

    /**
     * Stream has 3 lazy/intermediary operations
     * 
     * They return the another stream but not trigger the processing of data
     * 
     * peek, map, flatMap, filter
     * 
     */
    @Test
    public void peek() {
	// It is like forEach only but is process the data when encounter with terminal
	// operation
	final Stream<String> stream = Stream.of("one", "two", "three", "four");
	// it won't print any value but it return another stream
	assertNotNull(stream.peek(System.out::print));
    }

    @Test
    public void filter() {
	final Stream<String> stream = Stream.of("one", "two", "three", "four");
	// it won't print any value but it return another stream
	assertNotNull(stream.filter(String::isBlank));
    }

    @Test
    public void map() {
	// it convert from one object to another
	final Stream<String> stream = Stream.of("3", "2", "5", "7");
	// it won't print any value but it return another stream
	assertNotNull(stream.map(Integer::valueOf));
    }

    /**
     * Stream has 3 terminal operations
     * 
     * They trigger the processing of data
     * 
     * {@link Stream#forEach(java.util.function.Consumer)}
     * {@link Stream#reduce(java.util.function.BinaryOperator)}
     * {@link Stream#collect(java.util.stream.Collector)}
     */
    @Test
    public void forEach() {
	final Stream<String> stream = Stream.of("one", "two", "three", "four");
	assertThat(stream)
		.contains("one")
		.hasSize(4);
    }

    /**
     * <<Reduction Operations>> It is a average. This is the reduction step,
     * equivalent to the SQL aggregation. Like min , max, sum
     */
    @Test
    public void reduce_Max() {
	final var list = List.of("3", "1", "6", "2");
	// returns optoinal because of stram is empty then it will return empty
	Optional<Integer> max = list.stream()
		.map(Integer::valueOf)
		.max((a, b) -> a.compareTo(b));
//		.max(Comparator.naturalOrder()); // terminal operation
	System.out.println(max.get());
	assertEquals(6, max.get().intValue());
    }

    @Test
    public void reduce_Min() {
	final var list = List.of("3", "1", "6", "2");
	Optional<Integer> max = list.stream()
		.map(Integer::valueOf)
		.min(Comparator.naturalOrder()); // terminal operation
	System.out.println(max.get());
	assertEquals(1, max.get().intValue());
    }

    @Test
    public void reduce_count() {
	final var list = List.of("3", "1", "6", "2");
	long max = list.stream()
		.map(Integer::valueOf)
		.count(); // terminal operation
	System.out.println(max);
	assertEquals(4, max);
    }

    @Test
    public void reduce_sum() {
	final var list = List.of("3", "1", "6", "2");
	int sum = list.stream()
		.mapToInt(Integer::valueOf)
		.sum(); // terminal operation
	System.out.println(sum);
	assertEquals(12, sum);
    }

    @Test
    public void reduce_sum_String() {
	StringBuilder builder = new StringBuilder();
	List.of("three", "one", "aa", "Aad")
		.stream()
//		.sorted()
		.sorted(Comparator.reverseOrder())
		.forEach(builder::append); // terminal operation
	System.out.println(builder.toString());
	assertThat("threeoneaaAad").isEqualTo(builder.toString());
    }

    @Test
    public void reductions_Optoinal_findFirst_findAny() {
	List<String> list = List.of("three", "one", "aa", "Aad");
	Optional<String> findAny = list
		.stream()
		.findAny(); // terminal operation

	System.out.println(findAny);
	assertThat(findAny).isNotNull();

	Optional<String> findFirst = list
		.stream()
		.findFirst();
	System.out.println(findFirst);

	assertThat(findFirst).isNotNull();
    }

    @Test
    public void reduce() {
	String sum = List.of("three", "one", "aa", "Aad")
		.stream()
		.reduce("", (s1, s2) -> s1 + " " + s2);

	System.out.println(sum);
	assertThat(sum).isNotNull();

	Integer total = List.of("3", "1", "8", "2")
		.stream()
		.map(Integer::valueOf)
		.reduce(0, (s1, s2) -> s1 + s2);

	System.out.println(total);
	assertThat(total).isNotNull().isEqualTo(14);

	Stream<Integer> intStream = Stream.empty();
	BinaryOperator<Integer> binaryOpration = (i1, i2) -> i1 + i2;
	int empty = intStream.reduce(0, binaryOpration);
	System.out.println(empty);
	assertThat(empty).isEqualTo(0);

	Stream<Integer> oneStream = Stream.of(1);
	int one = oneStream.reduce(0, binaryOpration);
	System.out.println(one);
	assertThat(one).isEqualTo(1);
    }

    /**
     * It is another type of reduction
     * 
     * Instead of aggregating element, this reduction put them in a container
     */
    @Test
    public void collectors_Joining() {
	List<String> list = List.of("three", "one", "aa", "Aad");
	String add = list
		.stream()
		.collect(Collectors.joining(","));
	System.out.println(add);
	assertThat(add).isNotNull();
    }

    @Test
    public void collectors_toList() {
	List<Person> list = List.of(new Person(1, "Mahabir", "Singh", "Bermo", "India", 20),
		new Person(2, "Ram", "JI", "Bermo", "India", 30),
		new Person(3, "Shayam", "radhe", "Bermo", "India", 17),
		new Person(4, "Om", "sankar", "Bermo", "India", 20));
	List<String> names = list
		.stream()
		.filter(p -> p.getId() > 2)
		.map(Person::getLastName)
		.collect(Collectors.toList());
	System.out.println(names);
	assertThat(names).isNotNull().hasSize(2);
    }

    @Test
    public void collectors_toMap() {
	List<Person> list = List.of(new Person(1, "Mahabir", "Singh", "Bermo", "India", 20),
		new Person(2, "Ram", "JI", "Bermo", "India", 30),
		new Person(3, "Shayam", "radhe", "Bermo", "India", 17),
		new Person(4, "Om", "sankar", "Bermo", "India", 21));
	Map<Integer, String> map = list
		.stream()
		.filter(p -> p.getAge() > 18)
		.collect(Collectors.toMap(Person::getAge, Person::getFirstName));
	System.out.println(map);
	assertThat(map).isNotNull().hasSize(3);
    }

    @Test
    public void collectors_groupingBy() {
	List<Person> list = List.of(new Person(1, "Mahabir", "Singh", "Bermo", "India", 20),
		new Person(2, "Ram", "JI", "Bermo", "India", 30),
		new Person(3, "Shayam", "radhe", "Bermo", "India", 17),
		new Person(4, "Om", "sankar", "Bermo", "India", 21));
	Map<Integer, List<Person>> map = list
		.stream()
		.filter(p -> p.getAge() > 18)
		.collect(Collectors.groupingBy(Person::getAge));
	System.out.println(map);
	assertThat(map).isNotNull().hasSize(3);
    }

    @Test
    public void collectors_groupingBy_Counting() {
	List<Person> list = List.of(new Person(1, "Mahabir", "Singh", "Bermo", "India", 20),
		new Person(2, "Ram", "JI", "Bermo", "India", 30),
		new Person(3, "Shayam", "radhe", "Bermo", "India", 17),
		new Person(4, "Om", "sankar", "Bermo", "India", 20));
	Map<Integer, Long> map = list
		.stream()
		.filter(p -> p.getAge() > 18)
		.collect(Collectors.groupingBy(Person::getAge, Collectors.counting())); // download collector
	System.out.println(map);
	assertThat(map).isNotNull().hasSize(2);
    }
}
