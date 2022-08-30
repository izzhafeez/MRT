import java.util.HashMap;
class Network {
  
  private HashMap<String, Line> lines;
  private HashMap<String, Platform> platforms;
  private HashMap<String, Station> stations;
  
  public Network() {
    this.lines = new HashMap<String, Line>();
    this.platforms = new HashMap<String, Platform>();
    this.stations = new HashMap<String, Station>();
  }

  private void addLine(Line line) {
    this.lines.put(line.toString(), line);
  }

  public void addPlatform(
    String lineName,
    String stationName,
    int platformNumber,
    String suffix) {

    Line line = this.lines.get(lineName);
    PlatformCode platformCode = new PlatformCode(line, platformNumber, suffix);
    Platform platform = new Platform(platformCode);
    this.platforms.put(platform.toString(), platform);

    if (this.stations.containsKey(stationName)) {
      Station station = this.stations.get(stationName);
      station.addPlatform(platform);
    } else {
      Station station = new Station(stationName);
      station.addPlatform(platform);
      this.stations.put(station.getName(), station);
    }
  }

}
