import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OutgoingAdapterTest {
  @Test
  void testWriteOutput() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OutgoingAdapter adapter = new OutgoingAdapter(outputStream);
    adapter.writeOutput(2);

    Assertions.assertEquals("Number of words: 2\n", outputStream.toString());
  }
}