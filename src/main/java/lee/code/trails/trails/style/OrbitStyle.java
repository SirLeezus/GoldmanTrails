package lee.code.trails.trails.style;

import lee.code.trails.trails.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.TrailParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class OrbitStyle implements StyleInterface {

  @Override
  public void start(TrailManager trailManager, Player player, TrailParticle trailParticle) {
    trailManager.setActiveTrailTask(player.getUniqueId(), Bukkit.getAsyncScheduler().runAtFixedRate(trailManager.getTrails(), scheduledTask -> {
      if (trailManager.getMovementManager().isMoving(player.getUniqueId())) {
        trailParticle.spawnParticle(player, player.getLocation().add(0, 0.2, 0));
        return;
      }
      final int numPoints = 3; // Number of orbiting points.
      final double orbitRadius = 1.0; // Radius of the orbit.
      final double orbitSpeed = 0.02; // Speed of rotation.

      final Location playerLocation = player.getLocation().add(0, 1, 0);
      final double angleIncrement = 2 * Math.PI / numPoints;

      for (int i = 0; i < numPoints; i++) {
        final double angle = i * angleIncrement + (orbitSpeed * System.currentTimeMillis()); // Calculate angle with rotation.
        final double x = orbitRadius * Math.cos(angle);
        final double z = orbitRadius * Math.sin(angle);

        final Location particleLocation = playerLocation.clone().add(x, 0, z);
        trailParticle.spawnParticle(player, particleLocation);
      }
    },0, 200, TimeUnit.MILLISECONDS));
  }
}
