import java.util.ArrayList;

public class Station {
  private String name;
  private ArrayList<Platform> platforms;

  public Station(String name) {
    this.name = name;
    this.platforms = new ArrayList<Platform>();
  }

  public void addPlatform(Platform platform) {
    this.platforms.add(platform);
  }

  public String getName() {
    return this.name;
  }

  public ArrayList<Platform> getPlatforms() {
    return this.platforms;
  }
}
