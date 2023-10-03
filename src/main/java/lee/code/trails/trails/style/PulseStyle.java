package lee.code.trails.trails.style;

import lee.code.trails.trails.data.Style;
import lee.code.trails.trails.data.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.data.TrailParticle;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PulseStyle implements StyleInterface {

  @Override
  public Style create(TrailManager trailManager, Player player, TrailParticle trailParticle, String[] data) {
    final Style style = new Style(trailParticle, data, new ArrayList<>());
    if (trailManager.getMovementManager().isMoving(player.getUniqueId())) {
      style.addStyleLocation(player.getLocation().add(0, 0.2, 0));
      return style;
    }
    final int numPoints = 50;
    final double maxCircleRadius = 3.0;
    final double pulseSpeed = 0.001;
    final double currentRadius = maxCircleRadius * Math.abs(Math.sin(pulseSpeed * System.currentTimeMillis()));
    final Location playerLocation = player.getLocation().add(0, 0.1, 0);
    final double angleIncrement = 2 * Math.PI / numPoints;

    for (int i = 0; i < numPoints; i++) {
      final double angle = i * angleIncrement; // Calculate angle without pulse effect.
      final double x = currentRadius * Math.cos(angle);
      final double z = currentRadius * Math.sin(angle);

      style.addStyleLocation(playerLocation.clone().add(x, 0, z));
    }
    return style;
  }
}
