import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class TokenizerTest {
  @ParameterizedTest
  @CsvSource({
      "mary,mary", // case sensitive
      "Mary,Mary", // case sensitive
      " mary,mary", // leading white space
      "mary ,mary", // trailing white space
      " mary ,mary" // leading + trailing white space
  })
  void testTokenize_singleWord_caseSensitiveTrimmed(String input, String expectedSingleWord) {
    Tokenizer tokenizer = new Tokenizer();
    Assertions.assertEquals(List.of( expectedSingleWord), tokenizer.tokenize(input));
  }

  @ParameterizedTest
  @CsvSource({
      "mary had,mary had", // case sensitive
      "Mary had,Mary had", // case sensitive
      " mary had,mary had", // leading white space
      "mary had ,mary had", // trailing white space
      " mary had ,mary had" // leading + trailing white space
  })
  void testTokenize_twoWords_caseSensitiveTrimmed(String input, String expectedWords) {
    Tokenizer tokenizer = new Tokenizer();
    Assertions.assertEquals(List.of(expectedWords.split(" ")), tokenizer.tokenize(input));
  }

  @Test
  void testTokenize_multipleWords_caseSensitive() {
    Tokenizer tokenizer = new Tokenizer();
    Assertions.assertEquals(List.of("Mary", "had", "a", "little", "lamb"), tokenizer.tokenize("Mary had a little lamb"));
  }

  @Test
  void testTokenize_alphaNumericWords_numbersActAsDelimiter() {
    Tokenizer tokenizer = new Tokenizer();
    Assertions.assertEquals(List.of( "Ma", "ry", "had", "a"), tokenizer.tokenize("Ma3ry 1had a2"));
  }

  @Test
  void testTokenize_sentence_punctuationCharactersActAsDelimiter() {
    Tokenizer tokenizer = new Tokenizer();
    Assertions.assertEquals(List.of("Mary", "Had", "a", "little", "lamb"), tokenizer.tokenize("Mary. Had, a little lamb?!"));
  }

  @ParameterizedTest
  @NullSource
  @ValueSource(strings = {"", " "})
  void testTokenize_emptyOrNull_returnsEmptyArray(String input) {
    Tokenizer tokenizer = new Tokenizer();
    Assertions.assertEquals(List.of(), tokenizer.tokenize(input));
  }
}