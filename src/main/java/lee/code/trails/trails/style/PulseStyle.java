package lee.code.trails.trails.style;

import lee.code.trails.trails.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.TrailParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class PulseStyle implements StyleInterface {
  private final int numPoints = 50;
  private final double maxCircleRadius = 3.0;
  private final double pulseSpeed = 0.001;

  @Override
  public void start(TrailManager trailManager, Player player, TrailParticle trailParticle, int[] data) {
    trailManager.setActiveTrailTask(player.getUniqueId(), Bukkit.getAsyncScheduler().runAtFixedRate(trailManager.getTrails(), scheduledTask -> {
      if (trailManager.getMovementManager().isMoving(player.getUniqueId())) {
        trailParticle.spawnParticle(player, player.getLocation().add(0, 0.2, 0), data);
        return;
      }
      final double currentRadius = maxCircleRadius * Math.abs(Math.sin(pulseSpeed * System.currentTimeMillis()));
      final Location playerLocation = player.getLocation().add(0, 0.1, 0);
      final double angleIncrement = 2 * Math.PI / numPoints;

      for (int i = 0; i < numPoints; i++) {
        final double angle = i * angleIncrement; // Calculate angle without pulse effect.
        final double x = currentRadius * Math.cos(angle);
        final double z = currentRadius * Math.sin(angle);

        final Location particleLocation = playerLocation.clone().add(x, 0, z);
        trailParticle.spawnParticle(player, particleLocation, data);
      }
    },0, 200, TimeUnit.MILLISECONDS));
  }
}
