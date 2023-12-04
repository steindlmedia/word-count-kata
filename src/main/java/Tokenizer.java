import java.util.LinkedList;
import java.util.List;

public class Tokenizer {
  // Words are stretches of letters (a-z,A-Z).
  public List<String> tokenize(String input) {
    // Handle null input
    if (input == null) {
      return List.of();
    }

    char[] charArray = input.toCharArray();
    List<String> tokens = new LinkedList<>();

    // Token starts at first character position, if there is no delimiter character there
    int tokenStartIndex = 0;

    for (int currentIndex = 0; currentIndex < charArray.length; currentIndex++) {
      if (!isValidLetter(charArray[currentIndex])) {
        // We have a delimiter character at the current index
        // Generate new token, if the length would be greater than 0
        if (currentIndex - tokenStartIndex > 0) {
          tokens.add(input.substring(tokenStartIndex, currentIndex));
        }

        // Move the start of the next token to the next index position
        tokenStartIndex = currentIndex + 1;
      }
    }

    // In case the end of the input does not contain a delimiter, we need to add the last token here
    if (tokenStartIndex < charArray.length) {
      tokens.add(input.substring(tokenStartIndex, charArray.length));
    }

    return tokens;
  }

  private boolean isValidLetter(char c) {
    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
  }
}