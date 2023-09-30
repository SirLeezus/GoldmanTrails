package lee.code.trails.trails;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import lee.code.trails.Trails;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TrailManager {
  @Getter private final Trails trails;
  @Getter private final MovementManager movementManager;
  private final ConcurrentHashMap<UUID, ScheduledTask> activeTrailTasks = new ConcurrentHashMap<>();
  private final ConcurrentHashMap<UUID, TrailStyle> activeTrails = new ConcurrentHashMap<>();

  public TrailManager(Trails trails) {
    this.trails = trails;
    this.movementManager = new MovementManager();
  }

  public void startTrail(Player player, TrailParticle trailParticle, TrailStyle trailStyle) {
    movementManager.startTracking(trails, player.getUniqueId());
    activeTrails.put(player.getUniqueId(), trailStyle);
    trailStyle.getStyle().start(this, player, trailParticle);
  }

  public void stopTrail(Player player) {
    if (activeTrailTasks.get(player.getUniqueId()) != null) activeTrailTasks.get(player.getUniqueId()).cancel();
    activeTrailTasks.remove(player.getUniqueId());
    movementManager.stopTracking(player.getUniqueId());
    activeTrails.get(player.getUniqueId()).getStyle().stop(player);
  }

  public void setActiveTrailTask(UUID uuid, ScheduledTask scheduledTask) {
    activeTrailTasks.put(uuid, scheduledTask);
  }

  public boolean hasActiveTrail(UUID uuid) {
    return activeTrails.containsKey(uuid);
  }
}
