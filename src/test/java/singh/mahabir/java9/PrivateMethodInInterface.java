/**
 * All rights reserved.
 */

package singh.mahabir.java9;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * You can add private method in Interface from Java 9 onwards
 * 
 * @author Mahabir Singh
 *
 */
public class PrivateMethodInInterface {

    interface MyInterface {

	private int getData() {
	    System.out.println("I'm Private method");
	    return 10;
	}
    }

    @Test
    public void privateMethod() {

	MyInterface m = new MyInterface() {
	};
	assertThat(m.getData()).isNotNull().isEqualTo(10);
    }
}
