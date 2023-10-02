package lee.code.trails.trails.style;

import lee.code.trails.trails.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.TrailParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DamageStyle implements StyleInterface, Listener {
  private TrailManager trailManager;
  private final ConcurrentHashMap<UUID, String[]> playerTrailData = new ConcurrentHashMap<>();
  private final ConcurrentHashMap<UUID, TrailParticle> playerTrail = new ConcurrentHashMap<>();
  private final double radius = 0.5;
  private final int numParticles = 15;

  @Override
  public void start(TrailManager trailManager, Player player, TrailParticle trailParticle, String[] data) {
    if (this.trailManager == null) this.trailManager = trailManager;
    playerTrail.put(player.getUniqueId(), trailParticle);
    playerTrailData.put(player.getUniqueId(), data);
  }

  @Override
  public void stop(Player player) {
    playerTrail.remove(player.getUniqueId());
    playerTrailData.remove(player.getUniqueId());
  }

  @EventHandler
  public void onTrailDamage(EntityDamageByEntityEvent e) {
    if (!(e.getDamager() instanceof Player player)) return;
    if (!playerTrail.containsKey(player.getUniqueId())) return;
    Bukkit.getAsyncScheduler().runNow(trailManager.getTrails(), scheduledTask -> {
      final Location mobLocation = e.getEntity().getLocation().add(0, e.getEntity().getHeight() / 2, 0);

      for (int i = 0; i < numParticles; i++) {
        final double randomAngle = Math.random() * 2 * Math.PI;
        final double randomX = radius * Math.cos(randomAngle);
        final double randomZ = radius * Math.sin(randomAngle);
        final double randomY = (Math.random() - 0.5) * e.getEntity().getHeight();

        final Location particleLocation = mobLocation.clone().add(randomX, randomY, randomZ);
        playerTrail.get(player.getUniqueId()).spawnParticle(player, particleLocation, playerTrailData.get(player.getUniqueId()));
      }
    });
  }
}
