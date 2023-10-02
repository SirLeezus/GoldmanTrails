package lee.code.trails.trails;

import com.destroystokyo.paper.ParticleBuilder;
import lee.code.trails.utils.CoreUtil;
import lee.code.trails.utils.RainbowUtil;
import lombok.AllArgsConstructor;
import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

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
  PORTAL(Particle.PORTAL),
  REVERSE_PORTAL(Particle.REVERSE_PORTAL),
  SCRAPE(Particle.SCRAPE),
  SLIME(Particle.SLIME),
  SNEEZE(Particle.SNEEZE),
  SNOWBALL(Particle.SNOWBALL),
  SNOWFLAKE(Particle.SNOWFLAKE),
  SPELL(Particle.SPELL),
  SPELL_MOB(Particle.SPELL_MOB),
  SPELL_INSTANT(Particle.SPELL_INSTANT),
  SPELL_WITCH(Particle.SPELL_WITCH),
  SPIT(Particle.SPIT),
  SQUID_INK(Particle.SQUID_INK),
  WHITE_ASH(Particle.WHITE_ASH),
  WAX_OFF(Particle.WAX_OFF),
  WARPED_SPORE(Particle.WARPED_SPORE),
  VILLAGER_ANGRY(Particle.VILLAGER_ANGRY),
  TOWN_AURA(Particle.TOWN_AURA),
  FALLING_DUST(Particle.FALLING_DUST),
  BLOCK_CRACK(Particle.BLOCK_CRACK),
  ;

  private final Particle particle;

  public void spawnParticle(Player player, Location location, String[] data) {
    switch (this) {
      case NOTE -> player.getWorld().spawnParticle(particle, location, 0, CoreUtil.getRandomNoteColor() / 24.0, 0, 0, 1);
      case RAINBOW -> player.getWorld().spawnParticle(particle, location, 0, 0, 0, 0, 1,  new Particle.DustOptions(RainbowUtil.getNextColor(player.getUniqueId()), 1));
      case REDSTONE -> player.getWorld().spawnParticle(particle, location, 0, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])), 1));
      case FALLING_DUST, BLOCK_CRACK -> player.getWorld().spawnParticle(particle, location, 0, 0, 0, 0, 1, Material.valueOf(data[0]).createBlockData());
      case SPELL_MOB -> player.getWorld().spawnParticle(particle, location, 0, Integer.parseInt(data[0]) / 255D, Integer.parseInt(data[1]) / 255D, Integer.parseInt(data[2]) / 255D, 1);
      default -> player.getWorld().spawnParticle(particle, location, 0);
    }
  }
}
