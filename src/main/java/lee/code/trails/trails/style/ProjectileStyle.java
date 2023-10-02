package lee.code.trails.trails.style;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import lee.code.trails.trails.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.TrailParticle;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class ProjectileStyle implements StyleInterface, Listener {
  private TrailManager trailManager;
  private final ConcurrentHashMap<UUID, String[]> playerTrailData = new ConcurrentHashMap<>();
  private final ConcurrentHashMap<UUID, TrailParticle> playerTrail = new ConcurrentHashMap<>();

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
  public void onTrailShoot(ProjectileLaunchEvent e) {
    if (!(e.getEntity().getShooter() instanceof Player player)) return;
    if (!playerTrail.containsKey(player.getUniqueId())) return;
    final ScheduledTask shootTask = Bukkit.getAsyncScheduler().runAtFixedRate(trailManager.getTrails(), scheduledTask -> {
      if (!e.getEntity().isDead()) playerTrail.get(player.getUniqueId()).spawnParticle(player, e.getEntity().getLocation(), playerTrailData.get(player.getUniqueId()));
    }, 0, 100, TimeUnit.MILLISECONDS);
    Bukkit.getAsyncScheduler().runDelayed(trailManager.getTrails(), scheduledTask -> shootTask.cancel(), 10, TimeUnit.SECONDS);
  }
}
