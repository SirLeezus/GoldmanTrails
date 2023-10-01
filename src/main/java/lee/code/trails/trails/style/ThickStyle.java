package lee.code.trails.trails.style;

import lee.code.trails.trails.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.TrailParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ThickStyle implements StyleInterface {
  private final int count = 10;
  private final double radius = 1.0;
  private final Random random = new Random();

  @Override
  public void start(TrailManager trailManager, Player player, TrailParticle trailParticle) {
    trailManager.setActiveTrailTask(player.getUniqueId(), Bukkit.getAsyncScheduler().runAtFixedRate(trailManager.getTrails(), scheduledTask -> {
      final Location playerLocation = player.getLocation().add(0, 1, 0);

      for (int i = 0; i < count; i++) {
        final double xOffset = (random.nextDouble() * 2 - 1) * radius;
        final double yOffset = (random.nextDouble() * 2 - 1) * radius;
        final double zOffset = (random.nextDouble() * 2 - 1) * radius;

        final Location particleLocation = playerLocation.clone().add(xOffset, yOffset, zOffset);
        trailParticle.spawnParticle(player, particleLocation);
      }
    },0, 200, TimeUnit.MILLISECONDS));
  }
}
