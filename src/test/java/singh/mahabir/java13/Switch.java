/**
 *
 */
package singh.mahabir.java13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;

/**
 * @author Mahabir Singh
 *
 */
public class Switch {

    @SuppressWarnings("preview")
    String getShopingHours(DayOfWeek day) {
	return switch (day) {
	case MONDAY, WEDNESDAY, FRIDAY: {
	    yield "9 AM to 6 PM";
	}
	default:
	    yield "closed";
	};
    }

    @Test
    public void yeildSwitch() {
	assertEquals("closed", getShopingHours(DayOfWeek.THURSDAY));
	assertEquals("9 AM to 6 PM", getShopingHours(DayOfWeek.MONDAY));

    }
}
