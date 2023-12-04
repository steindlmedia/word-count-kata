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

    JavaApplication application = new JavaApplication(
        IncomingPortFactory.createPort(new String[] { }, inputStream, outputStream),
        new OutgoingAdapter(outputStream)
    );
    application.run();

    Assertions.assertEquals("Enter text: Number of words: 5\n", outputStream.toString());
  }

  @Test
  void testRunWithStopWordRemoval() throws IOException {
    String input = "Mary had a little lamb";

    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try (InputStream stopWordInputStream = JavaApplication.class.getResourceAsStream("stopwords.txt")) {
      JavaApplication application = new JavaApplication(
          IncomingPortFactory.createPort(new String[] { }, inputStream, outputStream),
          new OutgoingAdapter(outputStream),
          new WordCountService(new Tokenizer(), StopWordFilter.of(stopWordInputStream))
      );

      application.run();
    }

    Assertions.assertEquals("Enter text: Number of words: 4\n", outputStream.toString());
  }

  @Test
  void testRunWithStopWordRemoval_fromFile() throws IOException {
    InputStream inputStream = InputStream.nullInputStream();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try (InputStream stopWordInputStream = JavaApplication.class.getResourceAsStream("stopwords.txt")) {
      String fileName = getClass().getClassLoader().getResource("iteration3.txt").getPath();

      JavaApplication application = new JavaApplication(
          IncomingPortFactory.createPort(new String[] { fileName }, inputStream, outputStream),
          new OutgoingAdapter(outputStream),
          new WordCountService(new Tokenizer(), StopWordFilter.of(stopWordInputStream))
      );

      application.run();
    }

    Assertions.assertEquals("Number of words: 4\n", outputStream.toString());
  }

  @ParameterizedTest
  @ValueSource(strings = {"", " "})
  void testRun_emptyString(String input) throws IOException {
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    JavaApplication application = new JavaApplication(
        IncomingPortFactory.createPort(new String[] { }, inputStream, outputStream),
        new OutgoingAdapter(outputStream)
    );
    application.run();

    Assertions.assertEquals("Enter text: Number of words: 0\n", outputStream.toString());
  }
}