package lee.code.trails.trails.style;

import lee.code.trails.trails.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.TrailParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class ForceFieldStyle implements StyleInterface {
  private final double radius = 0.8;
  private final int numParticles = 20;
  private final double angleIncrement = 2 * Math.PI / numParticles;

  @Override
  public void start(TrailManager trailManager, Player player, TrailParticle trailParticle) {
    trailManager.setActiveTrailTask(player.getUniqueId(), Bukkit.getAsyncScheduler().runAtFixedRate(trailManager.getTrails(), scheduledTask -> {
      if (trailManager.getMovementManager().isMoving(player.getUniqueId())) {
        trailParticle.spawnParticle(player, player.getLocation().add(0, 0.2, 0));
        return;
      }
      final Location playerLocation = player.getLocation().clone().add(0, 1, 0);
      final double yOffset = Math.sin(System.currentTimeMillis() * 0.001) * 1; // Adjust the speed and range of up and down motion here.
      playerLocation.setY(playerLocation.getY() + yOffset);

      for (int i = 0; i < numParticles; i++) {
        final double angle = i * angleIncrement;
        final double x = radius * Math.cos(angle);
        final double z = radius * Math.sin(angle);
        final Location particleLocation = playerLocation.clone().add(x, 0, z);
        trailParticle.spawnParticle(player, particleLocation);
      }
    },0, 200, TimeUnit.MILLISECONDS));
  }
}
