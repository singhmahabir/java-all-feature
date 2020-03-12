/**
 * All rights reserved.
 */

package singh.mahabir.java8;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.StringJoiner;

import org.junit.jupiter.api.Test;

/**
 * @author Mahabir Singh
 *
 */
public class StringTrest {

    @Test
    public void join() {
	// String::join come in java 8
	String stream = String.join(", ", "one", "two", "three", "four");
	String[] split = stream.split(",");
	System.out.println(stream);
	assertThat(split.length).isEqualTo(4);
    }

    @Test
    public void String_Joiner() {
	// StringJoiner come in java 8
	StringJoiner sj = new StringJoiner(", ");
	sj.add("one").add("two").add("three").add("four");
	String sjJoiner = sj.toString();

	String[] split = sjJoiner.split(",");
	System.out.println(sjJoiner);
	assertThat(split.length).isEqualTo(4);

	sj = new StringJoiner(", ", "{", "}");
	sj.add("one").add("two").add("three").add("four");
	sjJoiner = sj.toString();

	split = sjJoiner.split(",");
	System.out.println(sjJoiner);
	assertThat(split.length).isEqualTo(4);
    }
}
