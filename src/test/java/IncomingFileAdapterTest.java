import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IncomingFileAdapterTest {
  @Test
  void testReadInput() throws IOException {
    String fileName = getClass().getClassLoader().getResource("iteration3.txt").getPath();
    IncomingFileAdapter adapter = new IncomingFileAdapter(fileName);

    Assertions.assertEquals("Mary had a little lamb", adapter.readInput());
  }
}