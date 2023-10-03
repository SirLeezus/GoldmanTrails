package lee.code.trails.trails.style;

import lee.code.trails.trails.data.Style;
import lee.code.trails.trails.data.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.data.TrailParticle;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class HaloStyle implements StyleInterface {

  @Override
  public Style create(TrailManager trailManager, Player player, TrailParticle trailParticle, String[] data) {
    final Style style = new Style(trailParticle, data, new ArrayList<>());
    if (trailManager.getMovementManager().isMoving(player.getUniqueId())) {
      style.addStyleLocation(player.getLocation().add(0, 0.2, 0));
      return style;
    }

    final Location playerLocation = player.getLocation().add(0, 2.2, 0);
    final double radius = 0.5;
    final int numParticles = 20;
    final double angleIncrement = 2 * Math.PI / numParticles;

    for (int i = 0; i < numParticles; i++) {
      final double angle = i * angleIncrement;
      final double x = radius * Math.cos(angle);
      final double z = radius * Math.sin(angle);
      final Location particleLocation = playerLocation.clone().add(x, 0, z);
      style.addStyleLocation(particleLocation);
    }
    return style;
  }
}
