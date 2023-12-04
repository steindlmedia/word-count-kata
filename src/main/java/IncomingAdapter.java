import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class IncomingAdapter implements IncomingPort {
  private InputStream inputStream;
  private OutputStream outputStream;

  public IncomingAdapter(InputStream inputStream, OutputStream outputStream) {
    this.inputStream = inputStream;
    this.outputStream = outputStream;
  }

  @Override
  public String readInput() throws IOException {
    // Ask user to provide text
    try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(this.outputStream))) {
      writer.write("Enter text: ");
      writer.flush();
    }

    // Read line from input stream
    try (Scanner scanner = new Scanner(this.inputStream)) {
      if (scanner.hasNextLine()) {
        return scanner.nextLine();
      }
    }

    return null;
  }
}