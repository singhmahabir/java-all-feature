/**
 *
 */
package singh.mahabir.java8;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * @author Mahabir Singh
 *
 */
@DisplayName("Predicate Test")
public class PredicateTest {

    @Test
    @DisplayName("😱")
//    @Disabled
//    @Timeout(1l)
    @Tag("fast")
    public void predicate() {

	// List.of come with java 9 and var is java 10 feature
	final var list = List.of(9, 3, 8, 1, 5, 4, 6, 2);

	final Predicate<Integer> even = i -> i % 2 == 0;
	final Predicate<Integer> odd = i -> i % 2 == 1;

	list.stream().sorted().filter(even.or(odd)).forEach(i -> System.out.print(i));
	System.out.println();
	list.stream().sorted(Integer::compare).filter(even).forEach(i -> System.out.print(i));
	list.stream().sorted(Integer::compareTo).filter(odd).forEach(i -> System.out.print(i));

	var evenList = list.stream().sorted(Integer::compare).filter(even).collect(Collectors.toList());
	Assertions.assertAll("List", () -> Assertions.assertNotNull(evenList, "evenList is null"),
		() -> Assertions.assertNotEquals(list.size(), evenList.size(), "Size should not be equels"));
	assertThat(list).isNotEqualTo(evenList);
	int[] x = new int[0];
	assertEquals(x, x);
    }
}