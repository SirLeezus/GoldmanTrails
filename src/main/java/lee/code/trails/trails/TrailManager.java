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
  private final ConcurrentHashMap<UUID, ScheduledTask> activeTrails = new ConcurrentHashMap<>();

  public TrailManager(Trails trails) {
    this.trails = trails;
    this.movementManager = new MovementManager();
  }

  public void startTrail(Player player, TrailParticle trailParticle, TrailStyle trailStyle) {
    movementManager.startTracking(trails, player.getUniqueId());
    trailStyle.getStyle().start(this, player, trailParticle);
  }

  public void stopTrail(Player player) {
    activeTrails.get(player.getUniqueId()).cancel();
    activeTrails.remove(player.getUniqueId());
    movementManager.stopTracking(player.getUniqueId());
  }

  public void setActiveTrail(UUID uuid, ScheduledTask scheduledTask) {
    activeTrails.put(uuid, scheduledTask);
  }

  public boolean hasActiveTrail(UUID uuid) {
    return activeTrails.containsKey(uuid);
  }
}
