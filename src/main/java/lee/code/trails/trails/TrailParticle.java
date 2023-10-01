package lee.code.trails.trails;

import lee.code.trails.utils.RainbowUtil;
import lombok.AllArgsConstructor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

@AllArgsConstructor
public enum TrailParticle {
  HAPPY_VILLAGER(Particle.VILLAGER_HAPPY),
  END_ROD(Particle.END_ROD),
  FLAME(Particle.FLAME),
  TOTEM(Particle.TOTEM),
  WAX_ON(Particle.WAX_ON),
  HEART(Particle.HEART),
  CHERRY_LEAVES(Particle.CHERRY_LEAVES),
  ASH(Particle.ASH),
  SOUL_FIRE_FLAME(Particle.SOUL_FIRE_FLAME),
  REDSTONE(Particle.REDSTONE),
  RAINBOW(Particle.REDSTONE),

  ;

  private final Particle particle;

  public void spawnParticle(Player player, Location location) {
    if (this.equals(RAINBOW)) player.getWorld().spawnParticle(particle, location, 0, 0, 0, 0, 1,  new Particle.DustOptions(RainbowUtil.getNextColor(player.getUniqueId()), 1));
    else if (this.equals(REDSTONE)) player.getWorld().spawnParticle(particle, location, 0, 0, 0, 0, 1, new Particle.DustOptions(Color.RED, 1));
    else player.getWorld().spawnParticle(particle, location, 0);
  }
}
