package lee.code.trails.trails.style;

import com.google.common.util.concurrent.AtomicDouble;
import lee.code.trails.trails.data.Style;
import lee.code.trails.trails.data.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.data.TrailParticle;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GroundSpiralStyle implements StyleInterface {
  private final ConcurrentHashMap<UUID, AtomicDouble> phi = new ConcurrentHashMap<>();

  @Override
  public Style create(TrailManager trailManager, Player player, TrailParticle trailParticle, String[] data) {
    final Style style = new Style(trailParticle, data, new ArrayList<>());
    if (trailManager.getMovementManager().isMoving(player.getUniqueId())) {
      trailParticle.spawnParticle(player, player.getLocation().add(0, 0.2, 0), data);
      return style;
    }
    final AtomicDouble phi = getPhi(player.getUniqueId());
    final Location loc = player.getLocation();

    spawnSpiralBelowPlayer(loc, style, phi.get());
    phi.addAndGet(Math.PI / 16);
    return style;
  }

  @Override
  public void stop(Player player) {
    phi.remove(player.getUniqueId());
  }

  private AtomicDouble getPhi(UUID uuid) {
    if (!phi.containsKey(uuid)) phi.put(uuid, new AtomicDouble(0));
    return phi.get(uuid);
  }

  private void spawnSpiralBelowPlayer(Location loc, Style style, double phi) {
    final int scale = 3;
    double x, y, z;
    for (double t = 0; t <= 2 * Math.PI; t += Math.PI / 16) {
      for (double i = 0; i <= 1; i += 1) {
        x = scale * 0.4 * (2 * Math.PI - t) * 0.5 * Math.cos(t + phi + i * Math.PI);
        y = 0.1; // Adjust this value to control the height of the spiral below the player
        z = scale * 0.4 * (2 * Math.PI - t) * 0.5 * Math.sin(t + phi + i * Math.PI);
        loc.add(x, y, z);
        style.addStyleLocation(loc);
        loc.subtract(x, y, z);
      }
    }
  }
}
