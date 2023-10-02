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

  public void startTrail(Player player, TrailParticle trailParticle, TrailStyle trailStyle, int[] data) {
    movementManager.startTracking(trails, player.getUniqueId());
    activeTrails.put(player.getUniqueId(), trailStyle);
    trailStyle.getStyle().start(this, player, trailParticle, data);
  }

  public void stopTrail(Player player) {
    final UUID uuid = player.getUniqueId();
    if (activeTrailTasks.containsKey(uuid)) {
      activeTrailTasks.get(uuid).cancel();
      activeTrailTasks.remove(uuid);
    }
    movementManager.stopTracking(uuid);
    activeTrails.get(uuid).getStyle().stop(player);
    activeTrails.remove(uuid);
  }

  public void setActiveTrailTask(UUID uuid, ScheduledTask scheduledTask) {
    activeTrailTasks.put(uuid, scheduledTask);
  }

  public boolean hasActiveTrail(UUID uuid) {
    return activeTrails.containsKey(uuid);
  }
}
