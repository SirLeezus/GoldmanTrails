package lee.code.trails.trails.style;

import com.google.common.util.concurrent.AtomicDouble;
import lee.code.trails.trails.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.TrailParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class CubeStyle implements StyleInterface {
  private final ConcurrentHashMap<UUID, AtomicDouble[]> angles = new ConcurrentHashMap<>();
  private final int[][] edges = {
    {0, 1}, {1, 3}, {3, 2}, {2, 0}, // Top face
    {4, 5}, {5, 7}, {7, 6}, {6, 4}, // Bottom face
    {0, 4}, {1, 5}, {2, 6}, {3, 7}  // Vertical edges
  };

  @Override
  public void start(TrailManager trailManager, Player player, TrailParticle trailParticle) {
    trailManager.setActiveTrailTask(player.getUniqueId(), Bukkit.getAsyncScheduler().runAtFixedRate(trailManager.getTrails(), scheduledTask -> {
      if (trailManager.getMovementManager().isMoving(player.getUniqueId())) {
        trailParticle.spawnParticle(player, player.getLocation().add(0, 0.2, 0));
        return;
      }
      final Location playerLocation = player.getLocation().add(0, 1, 0);
      // Define the initial vertices of the cube
      Location[] vertices = getVertices(playerLocation);

      final AtomicDouble[] angles = getAngles(player.getUniqueId());
      vertices = rotateVertices(vertices, playerLocation, angles[0].get(), angles[1].get());
      angles[0].addAndGet(.01);
      angles[1].addAndGet(.01);

      // Spawn particles along each edge
      for (int[] edge : edges) {
        final Location start = vertices[edge[0]];
        final Location end = vertices[edge[1]];
        spawnLine(player, trailParticle, start, end);
      }
    },0, 200, TimeUnit.MILLISECONDS));
  }

  @Override
  public void stop(Player player) {
    angles.remove(player.getUniqueId());
  }

  private AtomicDouble[] getAngles(UUID uuid) {
    if (!angles.containsKey(uuid)) {
      angles.put(uuid, new AtomicDouble[]{new AtomicDouble(0), new AtomicDouble(0)});
    }
    return angles.get(uuid);
  }

  private void spawnLine(Player player, TrailParticle trailParticle, Location start, Location end) {
    final int particles = 15;
    final double xOffset = (end.getX() - start.getX()) / particles;
    final double yOffset = (end.getY() - start.getY()) / particles;
    final double zOffset = (end.getZ() - start.getZ()) / particles;

    for (int i = 0; i < particles; i++) {
      trailParticle.spawnParticle(player, start.clone().add(i * xOffset, i * yOffset, i * zOffset));
    }
  }

  private Location[] getVertices(Location location) {
    final double size = 3;
    final double halfSize = size / 2.0;

    final Location[] vertices = new Location[8];
    vertices[0] = location.clone().add(halfSize, halfSize, halfSize);
    vertices[1] = location.clone().add(halfSize, halfSize, -halfSize);
    vertices[2] = location.clone().add(halfSize, -halfSize, halfSize);
    vertices[3] = location.clone().add(halfSize, -halfSize, -halfSize);
    vertices[4] = location.clone().add(-halfSize, halfSize, halfSize);
    vertices[5] = location.clone().add(-halfSize, halfSize, -halfSize);
    vertices[6] = location.clone().add(-halfSize, -halfSize, halfSize);
    vertices[7] = location.clone().add(-halfSize, -halfSize, -halfSize);

    return vertices;
  }

  private Location[] rotateVertices(Location[] vertices, Location center, double horizontalAngle, double verticalAngle) {
    final Location[] newVertices = new Location[vertices.length];

    for (int i = 0; i < vertices.length; i++) {
      Vector vector = vertices[i].toVector().subtract(center.toVector());

      // Perform horizontal rotation (around Y-axis)
      final double x = vector.getX();
      final double z = vector.getZ();

      final double cosH = Math.cos(horizontalAngle);
      final double sinH = Math.sin(horizontalAngle);

      final double newX = x * cosH - z * sinH;
      final double newZ = x * sinH + z * cosH;

      vector = new Vector(newX, vector.getY(), newZ);

      // Perform vertical rotation (around X-axis)
      final double y = vector.getY();
      final double z2 = vector.getZ();

      final double cosV = Math.cos(verticalAngle);
      final double sinV = Math.sin(verticalAngle);

      final double newY = y * cosV - z2 * sinV;
      final double newZ2 = y * sinV + z2 * cosV;

      vector = new Vector(vector.getX(), newY, newZ2);

      newVertices[i] = center.clone().add(vector);
    }

    return newVertices;
  }
}
