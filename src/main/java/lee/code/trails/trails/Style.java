package lee.code.trails.trails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
public class Style {
  private final TrailParticle trailParticle;
  @Setter private String[] trailData;
  private final ArrayList<Location> styleLocations;

  public void addStyleLocation(Location location) {
    styleLocations.add(location);
  }

  public void clearLocations() {
    styleLocations.clear();
  }
}
