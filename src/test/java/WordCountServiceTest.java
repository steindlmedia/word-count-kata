import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class WordCountServiceTest {
  @ParameterizedTest
  @CsvSource({
      "mary,mary", // case sensitive
      "Mary,Mary", // case sensitive
      " mary,mary", // leading white space
      "mary ,mary", // trailing white space
      " mary ,mary" // leading + trailing white space
  })
  void testCountWords_singleWord_caseSensitiveTrimmed(String input, String expectedSingleWord) {
    WordCountService wordCountService = new WordCountService(new Tokenizer());
    Assertions.assertEquals(1, wordCountService.countWords(input));
  }

  @ParameterizedTest
  @CsvSource({
      "mary had,mary had", // case sensitive
      "Mary had,Mary had", // case sensitive
      " mary had,mary had", // leading white space
      "mary had ,mary had", // trailing white space
      " mary had ,mary had" // leading + trailing white space
  })
  void testCountWords_twoWords_caseSensitiveTrimmed(String input, String expectedWords) {
    WordCountService wordCountService = new WordCountService(new Tokenizer());
    Assertions.assertEquals(2, wordCountService.countWords(input));
  }

  @Test
  void testCountWords_multipleWords_caseSensitive() {
    WordCountService wordCountService = new WordCountService(new Tokenizer());
    Assertions.assertEquals(5, wordCountService.countWords("Mary had a little lamb"));
  }

  @Test
  void testCountWords_stopWordFilter_oneWordNotCounted() {
    WordCountService wordCountService = new WordCountService(new Tokenizer(), StopWordFilter.of(List.of("the", "a", "on", "off")));
    Assertions.assertEquals(4, wordCountService.countWords("Mary had a little lamb"));
  }

  @Test
  void testCountWords_alphaNumericWords_numbersActAsDelimiter() {
    WordCountService wordCountService = new WordCountService(new Tokenizer());
    Assertions.assertEquals(4, wordCountService.countWords("Ma3ry 1had a2"));
  }

  @Test
  void testCountWords_sentence_punctuationCharactersActAsDelimiter() {
    WordCountService wordCountService = new WordCountService(new Tokenizer());
    Assertions.assertEquals(5, wordCountService.countWords("Mary. Had, a little lamb?!"));
  }

  @ParameterizedTest
  @NullSource
  @ValueSource(strings = {"", " "})
  void testCountWords_emptyOrNull_returnsEmptyArray(String input) {
    WordCountService wordCountService = new WordCountService(new Tokenizer());
    Assertions.assertEquals(0, wordCountService.countWords(input));
  }
}