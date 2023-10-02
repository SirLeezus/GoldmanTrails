package lee.code.trails.trails.style;

import lee.code.trails.trails.Style;
import lee.code.trails.trails.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.TrailParticle;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Random;

public class ThickStyle implements StyleInterface {
  private final Random random = new Random();

  @Override
  public Style create(TrailManager trailManager, Player player, TrailParticle trailParticle, String[] data) {
    final Style style = new Style(trailParticle, data, new ArrayList<>());
    final Location playerLocation = player.getLocation().add(0, 1, 0);
    final int count = 10;
    final double radius = 1.0;
    for (int i = 0; i < count; i++) {
      final double xOffset = (random.nextDouble() * 2 - 1) * radius;
      final double yOffset = (random.nextDouble() * 2 - 1) * radius;
      final double zOffset = (random.nextDouble() * 2 - 1) * radius;
      style.addStyleLocation(playerLocation.clone().add(xOffset, yOffset, zOffset));
    }
    return style;
  }
}
