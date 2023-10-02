package lee.code.trails.trails.style;

import lee.code.trails.trails.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.TrailParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class HaloStyle implements StyleInterface {
  private final double radius = 0.5;
  private final int numParticles = 20;
  private final double angleIncrement = 2 * Math.PI / numParticles;

  @Override
  public void start(TrailManager trailManager, Player player, TrailParticle trailParticle, int[] data) {
    trailManager.setActiveTrailTask(player.getUniqueId(), Bukkit.getAsyncScheduler().runAtFixedRate(trailManager.getTrails(), scheduledTask -> {
      if (trailManager.getMovementManager().isMoving(player.getUniqueId())) {
        trailParticle.spawnParticle(player, player.getLocation().add(0, 0.2, 0), data);
        return;
      }
      final Location playerLocation = player.getLocation().add(0, 2.2, 0);

      for (int i = 0; i < numParticles; i++) {
        final double angle = i * angleIncrement;
        final double x = radius * Math.cos(angle);
        final double z = radius * Math.sin(angle);
        final Location particleLocation = playerLocation.clone().add(x, 0, z);
        trailParticle.spawnParticle(player, particleLocation, data);
      }
    },0, 200, TimeUnit.MILLISECONDS));
  }
}
