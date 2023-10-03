package lee.code.trails.trails.style;

import lee.code.trails.trails.data.Style;
import lee.code.trails.trails.data.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.data.TrailParticle;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class HeartStyle implements StyleInterface {

  @Override
  public Style create(TrailManager trailManager, Player player, TrailParticle trailParticle, String[] data) {
    final Style style = new Style(trailParticle, data, new ArrayList<>());
    if (trailManager.getMovementManager().isMoving(player.getUniqueId())) {
      style.addStyleLocation(player.getLocation().add(0, 0.2, 0));
      return style;
    }
    final double playerYaw = Math.toRadians(player.getLocation().getYaw());
    final int numPoints = 100; // Adjust the number of points for the heart shape
    final double radius = 0.2; // Adjust the size of the heart here
    final double increment = (2 * Math.PI) / numPoints;

    // Calculate the rotation matrix based on player's yaw
    final double cosYaw = Math.cos(playerYaw);
    final double sinYaw = Math.sin(playerYaw);

    for (int i = 0; i < numPoints; i++) {
      final double angle = 0 + i * increment;
      final double x = radius * (16 * Math.pow(Math.sin(angle), 3));
      final double y = radius * (13 * Math.cos(angle) - 5 * Math.cos(2 * angle) - 2 * Math.cos(3 * angle) - Math.cos(4 * angle));
      final double z = 0;

      // Apply the rotation matrix
      final double newX = cosYaw * x - sinYaw * z;
      final double newZ = sinYaw * x + cosYaw * z;

      style.addStyleLocation(player.getLocation().add(newX, y + 2.2, newZ));
    }
    return style;
  }
}
