import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class StopWordFilter {
  List<String> stopWords;

  private StopWordFilter(List<String> stopWords) {
    this.stopWords = stopWords;
  }

  public static StopWordFilter of(List<String> stopWords) {
    if (stopWords == null) {
      throw new NullPointerException("list must not be null");
    }

    return new StopWordFilter(stopWords);
  }

  public static StopWordFilter of(InputStream inputStream) {
    List<String> stopWords = new LinkedList<>();

    try (Scanner scanner = new Scanner(inputStream)) {
      while (scanner.hasNextLine()) {
        stopWords.add(scanner.nextLine().trim());
      }
    }

    return StopWordFilter.of(stopWords);
  }

  public boolean isStopWord(String word) {
    return word != null && this.stopWords.contains(word);
  }
}