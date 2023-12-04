import java.io.IOException;

public interface OutgoingPort {
  void writeOutput(long wordCount) throws IOException;
}