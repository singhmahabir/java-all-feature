/**
 *
 */
package singh.mahabir.java13;

import org.junit.jupiter.api.Test;

/**
 * @author Mahabir Singh
 *
 */

public class TextBlock {

    @Test
    public void textBlock() {
	String s = """
		{
		"name" : "ram"
		"shayam":"shyam"
		}
		""";

	System.out.println(s);
	System.out.println("""
		Hi
		Hey
		 """);
    }
}
