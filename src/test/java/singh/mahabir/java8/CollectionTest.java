/**
 * All rights reserved.
 */

package singh.mahabir.java8;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Mahabir Singh
 *
 */
public class CollectionTest {

    /**
     * removeIf method added in java 8
     */
    @Test
    public void removeIf() {
	Collection<String> strings = Arrays.asList("one", "two", "three", "four");

	Assertions.assertThrows(UnsupportedOperationException.class, () -> {
	    strings.removeIf(s -> s.length() > 3);
	});
	// will not work if list is unmodifiable
	Collection<String> list = new ArrayList<>(strings);

	boolean removeIf = list.removeIf(s -> s.length() > 3);
	assertTrue(removeIf);
    }

    @Test
    public void replaceAll() {
	List<String> strings = Arrays.asList("one", "two", "three", "four");
	List<String> list = new ArrayList<>(strings);

	list.replaceAll(String::toUpperCase);

	System.out.println(list);
	assertEquals(4, list.size());
    }

    @Test
    public void sort() {
	List<String> strings = Arrays.asList("one", "two", "three", "four");
	List<String> list = new ArrayList<>(strings);

	list.sort(Comparator.naturalOrder());

	System.out.println(list);
	assertEquals(4, list.size());
    }

    // new method on Map
    @Test
    public void forEach_getOrDefault_putIfAbsent() {
	Map<String, Person> map = new HashMap<>();
	// forEach takes BiConsumer
	map.forEach((key, person) -> System.out.println(key + " " + person));
	assertEquals(0, map.size());

	Person p = map.getOrDefault("key", Person.DEFAULT_PERSON);// java 8
	assertThat(p).isEqualTo(Person.DEFAULT_PERSON);

	Person newOb = new Person();
	map.put("one", newOb); // java 7
	Person putIfAbsent = map.putIfAbsent("one", Person.DEFAULT_PERSON); // java 8
	assertThat(putIfAbsent).isEqualTo(newOb);
    }

    @Test
    public void replace_replaceAll() {
	Map<String, Person> map = new HashMap<>();
	Person newOb = new Person();
	map.put("one", newOb);

	// It will replace only if key and value will match
	Person replace = map.replace("one", Person.DEFAULT_PERSON);// java 8
	assertThat(replace).isEqualTo(newOb);

	boolean rep = map.replace("one", newOb, Person.DEFAULT_PERSON);// java 8
	assertThat(rep).isFalse();

	map.put("two", Person.DEFAULT_PERSON);
//	map.replaceAll(("one",Person.DEFAULT_PERSON)-> newOb);
    }

    @Test
    public void remove() {
	Map<String, Person> map = new HashMap<>();
	Person newOb = new Person();
	map.put("one", newOb);

	// It will remove only if key and value will match
	boolean removed = map.remove("one", Person.DEFAULT_PERSON);// java 8
	assertThat(removed).isFalse();

	map.put("default", Person.DEFAULT_PERSON);
	boolean removedDefault = map.remove("default", Person.DEFAULT_PERSON);// java 8
	assertThat(removedDefault).isTrue();
    }
}
