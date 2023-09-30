package lee.code.trails.trails.style;

import lee.code.trails.trails.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.TrailParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class SphereStyle implements StyleInterface {

  @Override
  public void start(TrailManager trailManager, Player player, TrailParticle trailParticle) {
    trailManager.setActiveTrail(player.getUniqueId(), Bukkit.getAsyncScheduler().runAtFixedRate(trailManager.getTrails(), scheduledTask -> {
      if (trailManager.getMovementManager().isMoving(player.getUniqueId())) {
        trailParticle.spawnParticle(player, player.getLocation().add(0, 0.2, 0));
        return;
      }
      final double radius = 2; // Adjust the radius of the sphere as needed
      final int numParticles = 100; // Number of particles in the sphere

      final Location playerLocation = player.getLocation().add(0, 1, 0);

      for (int i = 0; i < numParticles; i++) {
        final double u = Math.random();
        final double v = Math.random();
        final double theta = 2 * Math.PI * u;
        final double phi = Math.acos(2 * v - 1);

        final double x = radius * Math.sin(phi) * Math.cos(theta);
        final double y = radius * Math.sin(phi) * Math.sin(theta);
        final double z = radius * Math.cos(phi);

        final Location particleLocation = playerLocation.clone().add(x, y, z);
        trailParticle.spawnParticle(player, particleLocation);
      }
    },0, 200, TimeUnit.MILLISECONDS));
  }
}
