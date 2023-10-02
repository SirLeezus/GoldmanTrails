package lee.code.trails.trails.style;

import lee.code.trails.trails.Style;
import lee.code.trails.trails.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.TrailParticle;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class HelixStyle implements StyleInterface {

  @Override
  public Style create(TrailManager trailManager, Player player, TrailParticle trailParticle, String[] data) {
    final Style style = new Style(trailParticle, data, new ArrayList<>());
    if (trailManager.getMovementManager().isMoving(player.getUniqueId())) {
      style.addStyleLocation(player.getLocation().add(0, 0.2, 0));
      return style;
    }
    double x, y, z;
    final Location loc = player.getLocation();
    final long currentTime = System.currentTimeMillis();

    for (double t = 0; t <= 2 * Math.PI; t += Math.PI / 16) {
      for (double i = 0; i <= 1; i += 1) {
        final double phi = (Math.PI / 16) * currentTime / 200.0; // Adjust the speed of rotation here.
        x = 0.4 * (2 * Math.PI - t) * 0.5 * Math.cos(t + phi + i * Math.PI);
        y = 0.5 * t;
        z = 0.4 * (2 * Math.PI - t) * 0.5 * Math.sin(t + phi + i * Math.PI);
        loc.add(x, y, z);
        style.addStyleLocation(loc);
        loc.subtract(x, y, z);
      }
    }
    return style;
  }
}
