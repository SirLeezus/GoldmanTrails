package lee.code.trails.trails;

import lombok.AllArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

@AllArgsConstructor
public enum TrailParticle {
  HAPPY_VILLAGER(Particle.VILLAGER_HAPPY),
  END_ROD(Particle.END_ROD),
  ;

  private final Particle particle;

  public void spawnParticle(Player player, Location location) {
    player.getWorld().spawnParticle(particle, location, 0);
  }
}
