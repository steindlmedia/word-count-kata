import java.util.List;
import java.util.stream.Collectors;

public class WordCountService {
  private final Tokenizer tokenizer;
  private final StopWordFilter stopWordFilter;

  public WordCountService(Tokenizer tokenizer,
                          StopWordFilter stopWordFilter) {
    this.tokenizer = tokenizer;
    this.stopWordFilter = stopWordFilter;
  }

  public WordCountService(Tokenizer tokenizer) {
    this(tokenizer, StopWordFilter.of(List.of()));
  }

  public long countWords(String input) {
    List<String> tokens = tokenizer.tokenize(input)
        .stream()
        .filter(word -> !stopWordFilter.isStopWord(word))
        .collect(Collectors.toList());

    return tokens.size();
  }
}
