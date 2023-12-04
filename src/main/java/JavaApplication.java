import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JavaApplication {
  private final IncomingPort incomingPort;
  private final OutgoingPort outgoingPort;
  private final WordCountService wordCountService;

  public JavaApplication(IncomingPort adapter, OutgoingPort outgoingAdapter, WordCountService wordCountService) {
    this.incomingPort = adapter;
    this.outgoingPort = outgoingAdapter;
    this.wordCountService = wordCountService;
  }

  public JavaApplication(IncomingPort adapter, OutgoingPort outgoingAdapter) {
    this(adapter, outgoingAdapter, new WordCountService(new Tokenizer(), StopWordFilter.of(List.of())));
  }

  public void run() throws IOException {
    String input = this.incomingPort.readInput();
    this.outgoingPort.writeOutput(this.wordCountService.countWords(input));
  }

  public static void main(String[] args) throws IOException {
    try (InputStream stopWordInputStream = JavaApplication.class.getResourceAsStream("stopwords.txt")) {
      WordCountService wordCountService = new WordCountService(new Tokenizer(), StopWordFilter.of(stopWordInputStream));

      JavaApplication application = new JavaApplication(
          new IncomingAdapter(System.in, System.out),
          new OutgoingAdapter(System.out),
          wordCountService
      );

      application.run();
    }
  }
}