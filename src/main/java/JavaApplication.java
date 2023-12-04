import java.io.IOException;

public class JavaApplication {
  private final IncomingPort adapter;
  private final OutgoingPort outgoingAdapter;

  public JavaApplication(IncomingPort adapter, OutgoingPort outgoingAdapter) {
    this.adapter = adapter;
    this.outgoingAdapter = outgoingAdapter;
  }

  public void run() throws IOException {
    String input = adapter.readInput();
    WordCountService wordCountService = new WordCountService(new Tokenizer());
    outgoingAdapter.writeOutput(wordCountService.countWords(input));
  }

  public static void main(String[] args) throws IOException {
    JavaApplication application = new JavaApplication(new IncomingAdapter(System.in, System.out), new OutgoingAdapter(System.out));
    application.run();
  }
}