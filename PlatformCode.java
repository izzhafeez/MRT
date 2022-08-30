public class PlatformCode {
  private Line line;
  private int platformNumber;
  private String suffix;

  public PlatformCode(Line line, int platformNumber, String suffix) {
    this.line = line;
    this.platformNumber = platformNumber;
    this.suffix = suffix;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31*result + this.line.hashCode();
    result = 31*result + this.platformNumber;
    result = 31*result + this.suffix.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return String.format(
      "%s%d%s",
      this.line.prefix,
      this.platformNumber,
      this.suffix
      );
  }

  public PlatformCode getNextPlatformCode() {
    if (this.platformNumber == 0) {
      return null;
    }

    return new PlatformCode(
      this.line,
      this.platformNumber+1,
      this.suffix
      );
  }

  public PlatformCode getPrevPlatformCode() {
    if (this.line.maxNumber == this.platformNumber) {
      return null;
    }

    return new PlatformCode(
      this.line,
      this.platformNumber-1,
      this.suffix
      );
  }
}
