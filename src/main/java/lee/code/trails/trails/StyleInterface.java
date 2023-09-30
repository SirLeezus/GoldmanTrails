package lee.code.trails.trails;

import org.bukkit.entity.Player;

public interface StyleInterface {

  void start(TrailManager trailManager, Player player, TrailParticle trailParticle);

  default void stop(Player player) {
  }
}
