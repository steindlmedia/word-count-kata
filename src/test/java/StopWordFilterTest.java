import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class StopWordFilterTest {
  @Test
  void testOf_constructStopWordList() throws IOException {
    try (InputStream inputStream = getClass().getResourceAsStream("stopwords.txt")) {
      StopWordFilter filter = StopWordFilter.of(inputStream);
      Assertions.assertEquals(List.of("the", "a", "on", "off"), filter.stopWords);
    }
  }

  @Test
  void testOf_constructStopWordList_nullList_throwsException() {
    Assertions.assertThrows(NullPointerException.class, () -> StopWordFilter.of((List<String>) null));
  }

  @ParameterizedTest
  @ValueSource(strings = {"the", "a", "on", "off"})
  void testIsStopWord_stopWord_returnsTrue(String stopWord) {
    StopWordFilter filter = StopWordFilter.of(List.of("the", "a", "on", "off"));
    Assertions.assertTrue(filter.isStopWord(stopWord));
  }

  @ParameterizedTest
  @ValueSource(strings = {"this", "an", "I"})
  void testIsStopWord_nonStopWord_returnsFalse(String nonStopWord) {
    StopWordFilter filter = StopWordFilter.of(List.of("the", "a", "on", "off"));
    Assertions.assertFalse(filter.isStopWord(nonStopWord));
  }

  @ParameterizedTest
  @NullAndEmptySource
  void testIsStopWord_nullOrEmpty_returnsFalse(String nonStopWord) {
    StopWordFilter filter = StopWordFilter.of(List.of("the", "a", "on", "off"));
    Assertions.assertFalse(filter.isStopWord(nonStopWord));
  }
}