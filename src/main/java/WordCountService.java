import java.util.List;

public class WordCountService {
  private Tokenizer tokenizer;

  public WordCountService(Tokenizer tokenizer) {
    this.tokenizer = tokenizer;
  }

  public long countWords(String input) {
    List<String> tokens = tokenizer.tokenize(input);

    return tokens.size();
  }
}
