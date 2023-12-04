import java.io.IOException;

public interface IncomingPort {
  String readInput() throws IOException;
}