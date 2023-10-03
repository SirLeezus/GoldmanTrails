package lee.code.trails.trails.style;

import lee.code.trails.trails.data.Style;
import lee.code.trails.trails.data.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.data.TrailParticle;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SphereStyle implements StyleInterface {

  @Override
  public Style create(TrailManager trailManager, Player player, TrailParticle trailParticle, String[] data) {
    final Style style = new Style(trailParticle, data, new ArrayList<>());
    if (trailManager.getMovementManager().isMoving(player.getUniqueId())) {
      style.addStyleLocation(player.getLocation().add(0, 0.2, 0));
      return style;
    }
    final Location playerLocation = player.getLocation().add(0, 1, 0);
    final double radius = 2;
    final int numParticles = 100;

    for (int i = 0; i < numParticles; i++) {
      final double u = Math.random();
      final double v = Math.random();
      final double theta = 2 * Math.PI * u;
      final double phi = Math.acos(2 * v - 1);

      final double x = radius * Math.sin(phi) * Math.cos(theta);
      final double y = radius * Math.sin(phi) * Math.sin(theta);
      final double z = radius * Math.cos(phi);

      style.addStyleLocation(playerLocation.clone().add(x, y, z));
    }
    return style;
  }
}
