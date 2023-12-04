import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class OutgoingAdapter implements OutgoingPort {
  private final OutputStream outputStream;

  public OutgoingAdapter(OutputStream outputStream) {
    this.outputStream = outputStream;
  }

  @Override
  public void writeOutput(long wordCount) {
    try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(this.outputStream), true)) {
      writer.println(String.format("Number of words: %d", wordCount));
    }
  }
}