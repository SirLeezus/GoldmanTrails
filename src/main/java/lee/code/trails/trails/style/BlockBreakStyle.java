package lee.code.trails.trails.style;

import lee.code.trails.trails.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.TrailParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class BlockBreakStyle implements StyleInterface, Listener {
  private TrailManager trailManager;
  private final ConcurrentHashMap<UUID, TrailParticle> players = new ConcurrentHashMap<>();

  @Override
  public void start(TrailManager trailManager, Player player, TrailParticle trailParticle) {
    if (this.trailManager == null) this.trailManager = trailManager;
    players.put(player.getUniqueId(), trailParticle);
  }

  @Override
  public void stop(Player player) {
    players.remove(player.getUniqueId());
  }

  @EventHandler
  public void onTrailBlockBreak(BlockBreakEvent e) {
    if (!players.containsKey(e.getPlayer().getUniqueId())) return;
    Bukkit.getAsyncScheduler().runNow(trailManager.getTrails(), scheduledTask -> {
      final double radius = 0.5;
      final double maxYOffset = 1; // Adjust this value to control the height of particles
      final int numParticles = 25;
      final Location blockLocation = e.getBlock().getLocation().add(0.5, 0.0, 0.5); // Center of the block with no vertical offset

      for (int i = 0; i < numParticles; i++) {
        final double randomX = (Math.random() * 2 - 1) * radius;
        final double randomY = Math.random() * maxYOffset; // Limited height range
        final double randomZ = (Math.random() * 2 - 1) * radius;

        final Location particleLocation = blockLocation.clone().add(randomX, randomY, randomZ);
        players.get(e.getPlayer().getUniqueId()).spawnParticle(e.getPlayer(), particleLocation);
      }
    });
  }
}
