package lee.code.trails.trails.style;

import lee.code.trails.trails.data.Style;
import lee.code.trails.trails.data.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.data.TrailParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class BlockStyle implements StyleInterface, Listener {
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
  public void onTrailBlockPlace(BlockPlaceEvent e) {
    if (!playerTrail.containsKey(e.getPlayer().getUniqueId())) return;
    final Player player = e.getPlayer();
    final Block block = e.getBlock();
    Bukkit.getAsyncScheduler().runNow(trailManager.getTrails(), scheduledTask -> {
      final Style style = playerTrail.get(player.getUniqueId());
      style.clearLocations();
      final Location blockLocation = block.getLocation().add(0.5, 0.0, 0.5); // Center of the block with no vertical offset
      final double radius = 1.1;
      final double maxYOffset = 1;
      final int numParticles = 15;
      for (int i = 0; i < numParticles; i++) {
        final double randomX = (Math.random() * 2 - 1) * radius;
        final double randomY = Math.random() * maxYOffset; // Limit the maximum offset below the block
        final double randomZ = (Math.random() * 2 - 1) * radius;

        final Location particleLocation = blockLocation.clone().add(randomX, randomY, randomZ);
        style.addStyleLocation(particleLocation);
      }
      trailManager.spawnEventTrail(player, style);
    });
  }

  @EventHandler
  public void onTrailBlockBreak(BlockBreakEvent e) {
    if (!playerTrail.containsKey(e.getPlayer().getUniqueId())) return;
    final Player player = e.getPlayer();
    final Block block = e.getBlock();
    Bukkit.getAsyncScheduler().runNow(trailManager.getTrails(), scheduledTask -> {
      final Location blockLocation = block.getLocation().add(0.5, 0.0, 0.5); // Center of the block with no vertical offset
      final double radius = 0.5;
      final double maxYOffset = 1;
      final int numParticles = 25;
      final Style style = playerTrail.get(player.getUniqueId());
      style.clearLocations();
      for (int i = 0; i < numParticles; i++) {
        final double randomX = (Math.random() * 2 - 1) * radius;
        final double randomY = Math.random() * maxYOffset; // Limited height range
        final double randomZ = (Math.random() * 2 - 1) * radius;

        final Location particleLocation = blockLocation.clone().add(randomX, randomY, randomZ);
        style.addStyleLocation(particleLocation);
      }
      trailManager.spawnEventTrail(player, style);
    });
  }
}
