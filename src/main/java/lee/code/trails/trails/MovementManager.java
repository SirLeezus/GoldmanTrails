package lee.code.trails.trails;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import lee.code.trails.Trails;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class MovementManager {
  private final ConcurrentHashMap<UUID, ScheduledTask> tasks = new ConcurrentHashMap<>();
  private final ConcurrentHashMap<UUID, Vector> previousLocations = new ConcurrentHashMap<>();
  private final ConcurrentHashMap<UUID, Boolean> isMoving = new ConcurrentHashMap<>();

  public void startTracking(Trails trails, UUID uuid) {
    setTracker(uuid, Bukkit.getAsyncScheduler().runAtFixedRate(trails, scheduledTask -> {
      final OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
      if (!offlinePlayer.isOnline()) return;
      final Player player = offlinePlayer.getPlayer();
      if (player == null) return;
      final Vector previousLocation = previousLocations.getOrDefault(uuid, player.getLocation().toVector());
      isMoving.put(uuid, !player.getLocation().toVector().equals(previousLocation));
      previousLocations.put(uuid, player.getLocation().toVector());
    },0, 150, TimeUnit.MILLISECONDS));
  }

  private void setTracker(UUID uuid, ScheduledTask scheduledTask) {
    tasks.put(uuid, scheduledTask);
  }

  public void stopTracking(UUID uuid) {
    tasks.get(uuid).cancel();
    tasks.remove(uuid);
  }

  public boolean isMoving(UUID uuid) {
    return isMoving.getOrDefault(uuid, false);
  }
}
