package lee.code.trails.trails;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import lee.code.trails.Trails;
import lee.code.trails.trails.data.Style;
import lee.code.trails.trails.data.StyleType;
import lee.code.trails.trails.data.TrailParticle;
import lee.code.trails.trails.data.TrailStyle;
import lee.code.trails.utils.RainbowUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class TrailManager {
  @Getter private final Trails trails;
  @Getter private final MovementManager movementManager;
  private final ConcurrentHashMap<UUID, ScheduledTask> activeTrailTasks = new ConcurrentHashMap<>();
  private final ConcurrentHashMap<UUID, TrailStyle> activeTrails = new ConcurrentHashMap<>();

  public TrailManager(Trails trails) {
    this.trails = trails;
    this.movementManager = new MovementManager();
  }

  public void startTrail(Player player, TrailParticle trailParticle, TrailStyle trailStyle, String[] data) {
    if (hasActiveTrail(player.getUniqueId())) stopTrail(player);
    movementManager.startTracking(trails, player.getUniqueId());
    activeTrails.put(player.getUniqueId(), trailStyle);
    if (trailStyle.getType().equals(StyleType.TIMER)) timerTrail(player, trailParticle, trailStyle, data);
    else startEventTrail(player, trailParticle, trailStyle, data);
  }

  private void startEventTrail(Player player, TrailParticle trailParticle, TrailStyle trailStyle, String[] data) {
    trailStyle.getStyle().start(this, player, trailParticle, data);
  }

  public void spawnEventTrail(Player player, Style style) {
    setRainbowData(player.getUniqueId(), style);
    for (Location location : style.getStyleLocations()) {
      style.getTrailParticle().spawnParticle(player, location, style.getTrailData());
    }
  }

  private void timerTrail(Player player, TrailParticle trailParticle, TrailStyle trailStyle, String[] data) {
    setActiveTrailTask(player.getUniqueId(), Bukkit.getAsyncScheduler().runAtFixedRate(trails, scheduledTask -> {
      final Style style = trailStyle.getStyle().create(this, player, trailParticle, data);
      if (style == null) return;
      setRainbowData(player.getUniqueId(), style);
      for (Location location : style.getStyleLocations()) {
        style.getTrailParticle().spawnParticle(player, location, style.getTrailData());
      }
    },0, 200, TimeUnit.MILLISECONDS));
  }

  private void setRainbowData(UUID uuid, Style style) {
    final TrailParticle trailParticle = style.getTrailParticle();
    if (trailParticle.equals(TrailParticle.REDSTONE_RAINBOW) || trailParticle.equals(TrailParticle.SPELL_MOB_RAINBOW)) style.setTrailData(RainbowUtil.getNextColor(uuid));
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
