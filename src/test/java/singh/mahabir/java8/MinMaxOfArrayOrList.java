/**
 * Copyright 2019 Mahabir Singh. All rights reserved.
 */

package singh.mahabir.java8;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Mahabir Singh
 *
 */
public class MinMaxOfArrayOrList {

    @Test
    public void maxOfList() {
	final List<Integer> list = Arrays.asList(4, 8, 99, 2, 0);
	final Integer max = list
		.stream()
		.mapToInt(i -> i)
		.max()
		.orElseThrow(NumberFormatException::new);

	final Integer expected = 99;
	assertEquals(expected, max, "Should be 99");
	Optional<Integer> collect = list
		.stream()
		.collect(Collectors.maxBy(Comparator.naturalOrder()));
	System.out.println(collect.get());
    }

    @Test
    public void minOfList() {
	final List<Integer> list = Arrays.asList(4, 8, 99, 2, 0);

	final Integer min = list.stream().mapToInt(i -> i).min().orElseThrow(NumberFormatException::new);

	final Integer expected = 0;
	assertEquals(expected, min, "Should be 0");
    }

    @Test
    public void minOfListUsingComparator() {
	final List<Integer> list = Arrays.asList(4, 8, 99, 2, 0);

	final Integer min = list.stream().min(Comparator.naturalOrder()).orElseThrow(NumberFormatException::new);

	final Integer expected = 0;
	assertEquals(expected, min, "Should be 0");
    }

    @Test
    public void minOfListUsingComparatorForPersonClass() {
	final List<PersonData> list = Arrays.asList(new PersonData("ram", 2), new PersonData("shyam", 7),
		new PersonData("ram12", 99),
		new PersonData("rani", 8));

	final PersonData min = list.stream()
		.min(Comparator.comparing(PersonData::getAge))
		.orElseThrow(NumberFormatException::new);

	final Integer expected = 2;
	assertEquals(expected, min.getAge(), "Should be 2");

	var collect = list
		.stream()
		.collect(Collectors.maxBy(Comparator.comparing(PersonData::getAge)));
	System.out.println(collect.get().getAge());
    }

    @Setter
    @Getter
    @AllArgsConstructor
    class PersonData {
	String name;
	Integer age;
    }
}
