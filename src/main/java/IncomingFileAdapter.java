import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;

public class IncomingFileAdapter implements IncomingPort {
  private final String fileName;

  public IncomingFileAdapter(String fileName) {
    Objects.requireNonNull(fileName);
    this.fileName = fileName;
  }

  @Override
  public String readInput() throws IOException {
    return Files.readString(Path.of(this.fileName)).replaceAll("\\R", " ");
  }
}