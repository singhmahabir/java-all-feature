/**
 * All rights reserved.
 */

package singh.mahabir.java8;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

/**
 * @author Mahabir Singh
 *
 */
public class IO {

    /**
     * lines method added in java 8
     * 
     * new File(
     * "C:\\Users\\MahabirSingh\\my-project\\java-all-feature\\src\\test\\java\\singh\\mahabir\\java8\\debug.log");
     */
    @Test
    public void bufferReader_lines() {
	try (BufferedReader bufferedReader = new BufferedReader(
		new FileReader(
			new File(".").getCanonicalPath() + "/src/test/java/singh/mahabir/java8/debug.log"));) {
	    Stream<String> lines = bufferedReader.lines();
	    lines.filter(line -> line.contains("error"))
		    .findFirst()
		    .ifPresent(System.out::println);
	    assertThat(bufferedReader.lines().count()).isEqualTo(0);
	} catch (IOException e) {
	    System.out.println(e);
	}

	try (BufferedReader bufferedReader = new BufferedReader(
		new FileReader(
			new File(".").getCanonicalPath() + "/src/test/java/singh/mahabir/java8/debug.log"));) {
	    assertThat(bufferedReader.lines().count()).isEqualTo(3);
	} catch (IOException e) {
	    System.out.println(e);
	}
    }

    /**
     * Stream implements AutoCloseable
     */
    @Test
    public void file_lines() {
	Path path = Paths.get("C:", "Users", "MahabirSingh", "my-project", "java-all-feature", "src", "test", "java",
		"singh", "mahabir", "java8", "debug.log");
	try (Stream<String> lines = Files.lines(path)) {
	    lines.filter(line -> line.contains("error"))
		    .findFirst()
		    .ifPresent(System.out::println);
	} catch (IOException e) {
	    System.out.println(e);
	}
	try {
	    assertThat(Files.lines(path).count()).isEqualTo(3);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
