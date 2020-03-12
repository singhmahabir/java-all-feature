/**
 * All rights reserved.
 */

package singh.mahabir.java9;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Mahabir Singh
 *
 */
public class CollectionFactoryMethods {

    @Test
    public void List_Of() {

	List<String> list = List.of();
	assertThat(list).isNullOrEmpty();

	List<String> list1 = List.of("one", "two");
	assertThat(list1.size()).isEqualTo(2);
    }

    @Test
    public void Set_Of() {

	Set<String> set = Set.of();
	assertThat(set).isNullOrEmpty();

	Set<String> set1 = Set.of("one", "two");
	assertThat(set1.size()).isEqualTo(2);

	// Set.of add only unique element else api throw exception
	Assertions.assertThrows(IllegalArgumentException.class, () -> Set.of("one", "one"));
	// Don't add null values
	Assertions.assertThrows(NullPointerException.class, () -> Set.of("one", null));
    }

    @Test
    public void Map_Of() {

	Map<String, String> map = Map.of();
	assertThat(map).isNullOrEmpty();

	Map<String, String> map1 = Map.of("one", "two");
	assertThat(map1.size()).isEqualTo(1);

	// Map.of add only unique key else api throw exception
	Assertions.assertThrows(IllegalArgumentException.class, () -> Map.of("one", "one", "one", "one"));
	// Don't add null values
	Assertions.assertThrows(NullPointerException.class, () -> Map.of(null, "value"));
    }

    @Test
    public void Map_ofEntries() {

	Map<String, String> map = Map.ofEntries();
	assertThat(map).isNullOrEmpty();

	Map<String, String> map1 = Map.ofEntries(Map.entry("one", "two"));
	assertThat(map1.size()).isEqualTo(1);
    }

}
