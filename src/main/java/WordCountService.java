import java.util.List;

public class WordCountService {
  private final Tokenizer tokenizer;

  public WordCountService(Tokenizer tokenizer) {
    this.tokenizer = tokenizer;
  }

  public long countWords(String input) {
    List<String> tokens = tokenizer.tokenize(input);

    return tokens.size();
  }
}
