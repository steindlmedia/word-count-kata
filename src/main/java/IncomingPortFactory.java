import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public class IncomingPortFactory {
  private IncomingPortFactory() { }

  public static IncomingPort createPort(String[] sysArgs, InputStream inputStream, OutputStream outputStream) {
    Objects.requireNonNull(sysArgs);

    if (sysArgs.length == 1) {
      return new IncomingFileAdapter(sysArgs[0]);
    } else {
      return new IncomingConsoleAdapter(inputStream, outputStream);
    }
  }
}