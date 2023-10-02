package lee.code.trails.trails.style;

import lee.code.trails.trails.Style;
import lee.code.trails.trails.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.TrailParticle;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class OrbitStyle implements StyleInterface {

  @Override
  public Style create(TrailManager trailManager, Player player, TrailParticle trailParticle, String[] data) {
    final Style style = new Style(trailParticle, data, new ArrayList<>());
    if (trailManager.getMovementManager().isMoving(player.getUniqueId())) {
      style.addStyleLocation(player.getLocation().add(0, 0.2, 0));
      return style;
    }
    final Location playerLocation = player.getLocation().add(0, 1, 0);
    final int numPoints = 3;
    final double orbitRadius = 1.0;
    final double orbitSpeed = 0.02;
    final double angleIncrement = 2 * Math.PI / numPoints;

    for (int i = 0; i < numPoints; i++) {
      final double angle = i * angleIncrement + (orbitSpeed * System.currentTimeMillis()); // Calculate angle with rotation.
      final double x = orbitRadius * Math.cos(angle);
      final double z = orbitRadius * Math.sin(angle);

      style.addStyleLocation(playerLocation.clone().add(x, 0, z));
    }
    return style;
  }
}
