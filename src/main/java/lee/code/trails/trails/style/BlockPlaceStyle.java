package lee.code.trails.trails.style;

import lee.code.trails.trails.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.TrailParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class BlockPlaceStyle implements StyleInterface, Listener {
  private TrailManager trailManager;
  private final ConcurrentHashMap<UUID, int[]> playerTrailData = new ConcurrentHashMap<>();
  private final ConcurrentHashMap<UUID, TrailParticle> playerTrail = new ConcurrentHashMap<>();
  private final double radius = 1.1;
  private final double maxYOffset = 1;
  private final int numParticles = 15;

  @Override
  public void start(TrailManager trailManager, Player player, TrailParticle trailParticle, int[] data) {
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
  public void onTrailBlockPlace(BlockPlaceEvent e) {
    if (!playerTrail.containsKey(e.getPlayer().getUniqueId())) return;
    Bukkit.getAsyncScheduler().runNow(trailManager.getTrails(), scheduledTask -> {
      final Location blockLocation = e.getBlock().getLocation().add(0.5, 0.0, 0.5); // Center of the block with no vertical offset

      for (int i = 0; i < numParticles; i++) {
        final double randomX = (Math.random() * 2 - 1) * radius;
        final double randomY = Math.random() * maxYOffset; // Limit the maximum offset below the block
        final double randomZ = (Math.random() * 2 - 1) * radius;

        final Location particleLocation = blockLocation.clone().add(randomX, randomY, randomZ);
        final UUID uuid = e.getPlayer().getUniqueId();
        playerTrail.get(uuid).spawnParticle(e.getPlayer(), particleLocation, playerTrailData.get(uuid));
      }
    });
  }
}
