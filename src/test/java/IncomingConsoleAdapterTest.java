import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IncomingConsoleAdapterTest {
  @Test
  void testReadInput() throws IOException {
    String input = "Mary had a little lamb";

    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    IncomingConsoleAdapter adapter = new IncomingConsoleAdapter(inputStream, outputStream);

    Assertions.assertEquals(input, adapter.readInput());
    Assertions.assertEquals("Enter text: ", outputStream.toString());
  }
}