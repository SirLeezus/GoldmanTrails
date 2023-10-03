package lee.code.trails.trails.style;

import lee.code.trails.trails.data.Style;
import lee.code.trails.trails.data.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.data.TrailParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DamageStyle implements StyleInterface, Listener {
  private TrailManager trailManager;
  private final ConcurrentHashMap<UUID, Style> playerTrail = new ConcurrentHashMap<>();

  @Override
  public void start(TrailManager trailManager, Player player, TrailParticle trailParticle, String[] data) {
    if (this.trailManager == null) this.trailManager = trailManager;
    playerTrail.put(player.getUniqueId(), new Style(trailParticle, data, new ArrayList<>()));
  }

  @Override
  public void stop(Player player) {
    playerTrail.remove(player.getUniqueId());
  }

  @EventHandler
  public void onTrailDamage(EntityDamageByEntityEvent e) {
    if (!(e.getDamager() instanceof Player player)) return;
    if (!playerTrail.containsKey(player.getUniqueId())) return;
    Bukkit.getAsyncScheduler().runNow(trailManager.getTrails(), scheduledTask -> {
      final Style style = playerTrail.get(player.getUniqueId());
      style.clearLocations();
      final Location mobLocation = e.getEntity().getLocation().add(0, e.getEntity().getHeight() / 2, 0);
      final double radius = 0.5;
      final int numParticles = 15;
      for (int i = 0; i < numParticles; i++) {
        final double randomAngle = Math.random() * 2 * Math.PI;
        final double randomX = radius * Math.cos(randomAngle);
        final double randomZ = radius * Math.sin(randomAngle);
        final double randomY = (Math.random() - 0.5) * e.getEntity().getHeight();

        final Location particleLocation = mobLocation.clone().add(randomX, randomY, randomZ);
        style.addStyleLocation(particleLocation);
      }
      trailManager.spawnEventTrail(player, style);
    });
  }
}
