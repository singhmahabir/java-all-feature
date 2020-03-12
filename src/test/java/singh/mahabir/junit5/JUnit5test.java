/**
 *
 */
package singh.mahabir.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * @author Mahabir Singh
 *
 */
@DisplayName("JUnit 5 Test")
public class JUnit5test {

    @Test
    @DisplayName("😱")
//    @Disabled
//    @Timeout(1l)
    @Tag("fast")
    public void test() {

	int[] x = new int[0];
	assertEquals(x, x);
    }
}