/**
 *
 */
package singh.mahabir.java8;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Mahabir Singh
 *
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person {

    public static final Person DEFAULT_PERSON = new Person(0, "default", "default", "default", "default", 0);

    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String country;
    private int age;
}
