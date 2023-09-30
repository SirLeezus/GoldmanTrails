package lee.code.trails.trails.style;

import com.google.common.util.concurrent.AtomicDouble;
import lee.code.trails.trails.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.TrailParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class HelixStyle implements StyleInterface {
  private final ConcurrentHashMap<UUID, AtomicDouble> phi = new ConcurrentHashMap<>();

  @Override
  public void start(TrailManager trailManager, Player player, TrailParticle trailParticle) {
    trailManager.setActiveTrailTask(player.getUniqueId(), Bukkit.getAsyncScheduler().runAtFixedRate(trailManager.getTrails(), scheduledTask -> {
      if (trailManager.getMovementManager().isMoving(player.getUniqueId())) {
        trailParticle.spawnParticle(player, player.getLocation().add(0, 0.2, 0));
        return;
      }
      final AtomicDouble phi = getPhi(player.getUniqueId());
      double x, y, z;
      final Location loc = player.getLocation();
      for (double t = 0; t <= 2 * Math.PI; t += Math.PI / 16) {
        for (double i = 0; i <= 1; i += 1) {
          x = 0.4 * (2 * Math.PI - t) * 0.5 * Math.cos(t + phi.get() + i * Math.PI);
          y = 0.5 * t;
          z = 0.4 * (2 * Math.PI - t) * 0.5 * Math.sin(t + phi.get() + i * Math.PI);
          loc.add(x, y, z);
          trailParticle.spawnParticle(player, loc);
          loc.subtract(x, y, z);
        }
      }
      phi.addAndGet(Math.PI / 16);
    },0, 200, TimeUnit.MILLISECONDS));
  }

  @Override
  public void stop(Player player) {
    phi.remove(player.getUniqueId());
  }

  private AtomicDouble getPhi(UUID uuid) {
    if (!phi.containsKey(uuid)) {
      phi.put(uuid, new AtomicDouble(0));
    }
    return phi.get(uuid);
  }
}
