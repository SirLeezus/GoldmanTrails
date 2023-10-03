package lee.code.trails.trails.style;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import lee.code.trails.trails.data.Style;
import lee.code.trails.trails.data.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.data.TrailParticle;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class ProjectileStyle implements StyleInterface, Listener {
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
  public void onTrailShoot(ProjectileLaunchEvent e) {
    if (!(e.getEntity().getShooter() instanceof Player player)) return;
    if (!playerTrail.containsKey(player.getUniqueId())) return;
    final Entity entity = e.getEntity();
    final Style style = playerTrail.get(player.getUniqueId());
    final Style targetStyle = new Style(style.getTrailParticle(), style.getTrailData(), new ArrayList<>());
    final ScheduledTask shootTask = Bukkit.getAsyncScheduler().runAtFixedRate(trailManager.getTrails(), scheduledTask -> {
      if (!entity.isDead()) {
        targetStyle.clearLocations();
        targetStyle.addStyleLocation(entity.getLocation());
        trailManager.spawnEventTrail(player, targetStyle);
      }
    }, 0, 100, TimeUnit.MILLISECONDS);
    Bukkit.getAsyncScheduler().runDelayed(trailManager.getTrails(), scheduledTask -> shootTask.cancel(), 10, TimeUnit.SECONDS);
  }
}
