package lee.code.trails.trails.style;

import lee.code.trails.trails.data.Style;
import lee.code.trails.trails.data.StyleInterface;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.data.TrailParticle;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class NormalStyle implements StyleInterface {

  @Override
  public Style create(TrailManager trailManager, Player player, TrailParticle trailParticle, String[] data) {
    if (!trailManager.getMovementManager().isMoving(player.getUniqueId())) return null;
    final Style style = new Style(trailParticle, data, new ArrayList<>());
    style.addStyleLocation(player.getLocation().add(0, 0.2, 0));
    return style;
  }
}
