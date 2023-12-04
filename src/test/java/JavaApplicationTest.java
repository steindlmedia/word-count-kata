import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class JavaApplicationTest {
  @Test
  void testRun() throws IOException {
    String input = "Mary had a little lamb";

    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    JavaApplication application = new JavaApplication(new IncomingAdapter(inputStream, outputStream), new OutgoingAdapter(outputStream));
    application.run();

    Assertions.assertEquals("Enter text: Number of words: 5\n", outputStream.toString());
  }

  @ParameterizedTest
  @ValueSource(strings = {"", " "})
  void testRun_emptyString(String input) throws IOException {
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    JavaApplication application = new JavaApplication(new IncomingAdapter(inputStream, outputStream), new OutgoingAdapter(outputStream));
    application.run();

    Assertions.assertEquals("Enter text: Number of words: 0\n", outputStream.toString());
  }
}