public class Platform {
  private PlatformCode platformCode;
  private Platform nextPlatform;
  private Platform prevPlatform;

  public Platform(PlatformCode platformCode) {
    this.platformCode = platformCode;
    this.nextPlatform = null;
    this.prevPlatform = null;
  }

  public PlatformCode getNextPlatformCode() {
    return this.platformCode.getNextPlatformCode();
  }

  public PlatformCode getPrevPlatformCode() {
    return this.platformCode.getPrevPlatformCode();
  }
}
