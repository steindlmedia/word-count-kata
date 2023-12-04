import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IncomingPortFactoryTest {
  @Test
  void testCreatePort_oneArg_returnsFileAdapter() throws IOException {
    String fileName = getClass().getClassLoader().getResource("iteration3.txt").getPath();
    IncomingPort incomingPort = IncomingPortFactory.createPort(new String[] { fileName }, InputStream.nullInputStream(), OutputStream.nullOutputStream());
    Assertions.assertTrue(incomingPort instanceof IncomingFileAdapter);
  }

  @Test
  void testCreatePort_zeroSysArgs_returnsConsoleAdapter() throws IOException {
    IncomingPort incomingPort = IncomingPortFactory.createPort(new String[] { }, InputStream.nullInputStream(), OutputStream.nullOutputStream());
    Assertions.assertTrue(incomingPort instanceof IncomingConsoleAdapter);
  }
}