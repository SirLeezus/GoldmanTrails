package lee.code.trails.trails;

import lee.code.trails.utils.CoreUtil;
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
  ELECTRIC_SPARK(Particle.ELECTRIC_SPARK),
  BUBBLE_POP(Particle.BUBBLE_POP),
  CAMPFIRE_COSY_SMOKE(Particle.CAMPFIRE_COSY_SMOKE),
  CLOUD(Particle.CLOUD),
  CRIMSON_SPORE(Particle.CRIMSON_SPORE),
  CRIT(Particle.CRIT),
  CRIT_MAGIC(Particle.CRIT_MAGIC),
  DAMAGE_INDICATOR(Particle.DAMAGE_INDICATOR),
  DOLPHIN(Particle.DOLPHIN),
  DRAGON_BREATH(Particle.DRAGON_BREATH),
  DRIP_LAVA(Particle.DRIP_LAVA),
  DRIP_WATER(Particle.DRIP_WATER),
  DRIPPING_OBSIDIAN_TEAR(Particle.DRIPPING_OBSIDIAN_TEAR),
  FALLING_SPORE_BLOSSOM(Particle.FALLING_SPORE_BLOSSOM),
  WATER_DROP(Particle.WATER_DROP),
  ENCHANTMENT_TABLE(Particle.ENCHANTMENT_TABLE),
  FIREWORKS_SPARK(Particle.FIREWORKS_SPARK),
  GLOW(Particle.GLOW),
  GLOW_SQUID_INK(Particle.GLOW_SQUID_INK),
  LAVA(Particle.LAVA),
  NAUTILUS(Particle.NAUTILUS),
  NOTE(Particle.NOTE),
  ;

  private final Particle particle;

  public void spawnParticle(Player player, Location location) {
    switch (this) {
      case NOTE -> player.getWorld().spawnParticle(particle, location, 0, CoreUtil.getRandomNoteColor() / 24.0, 0, 0, 1);
      case RAINBOW -> player.getWorld().spawnParticle(particle, location, 0, 0, 0, 0, 1,  new Particle.DustOptions(RainbowUtil.getNextColor(player.getUniqueId()), 1));
      case REDSTONE -> player.getWorld().spawnParticle(particle, location, 0, 0, 0, 0, 1, new Particle.DustOptions(Color.RED, 1));
      default -> player.getWorld().spawnParticle(particle, location, 0);
    }
  }
}
