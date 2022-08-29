import java.util.HashMap;
class Network {
  
  HashMap<String, Line> lines;
  
  public Network() {
    lines = new HashMap<String, Line>();
  }

  private void addLine(Line line) {
    lines.put(line.toString(), line);
  }

  public void addStation(Station station) {
     
  }

  public void removeStation(Station station) {
    
  }

}
