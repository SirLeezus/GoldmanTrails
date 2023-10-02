package lee.code.trails.trails;

import org.bukkit.entity.Player;

public interface StyleInterface {

  default Style create(TrailManager trailManager, Player player, TrailParticle trailParticle, String[] data) {
    return null;
  }

  default void start(TrailManager trailManager, Player player, TrailParticle trailParticle, String[] data) {
  }

  default void stop(Player player) {
  }
}
