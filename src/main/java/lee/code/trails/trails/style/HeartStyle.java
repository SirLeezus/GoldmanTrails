package lee.code.trails.trails.style;

import lee.code.trails.trails.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.TrailParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.concurrent.TimeUnit;

public class HeartStyle implements StyleInterface {
  private final int numPoints = 100; // Adjust the number of points for the heart shape
  private final double radius = 0.2; // Adjust the size of the heart here
  private final double increment = (2 * Math.PI) / numPoints;

  @Override
  public void start(TrailManager trailManager, Player player, TrailParticle trailParticle, String[] data) {
    trailManager.setActiveTrailTask(player.getUniqueId(), Bukkit.getAsyncScheduler().runAtFixedRate(trailManager.getTrails(), scheduledTask -> {
      if (trailManager.getMovementManager().isMoving(player.getUniqueId())) {
        trailParticle.spawnParticle(player, player.getLocation().add(0, 0.2, 0), data);
        return;
      }
      final double playerYaw = Math.toRadians(player.getLocation().getYaw());

      for (int i = 0; i < numPoints; i++) {
        final double angle = 0 + i * increment;
        double x = radius * (16 * Math.pow(Math.sin(angle), 3));
        double y = radius * (13 * Math.cos(angle) - 5 * Math.cos(2 * angle) - 2 * Math.cos(3 * angle) - Math.cos(4 * angle));
        double z = 0;

        // Rotate the point based on player's yaw
        final Vector rotated = new Vector(x, y, z).rotateAroundY(playerYaw);
        x = rotated.getX();
        y = rotated.getY();
        z = rotated.getZ();

        final Location loc = player.getLocation().add(x, y + 2.2, z);
        trailParticle.spawnParticle(player, loc, data);
      }
    },0, 200, TimeUnit.MILLISECONDS));
  }
}
