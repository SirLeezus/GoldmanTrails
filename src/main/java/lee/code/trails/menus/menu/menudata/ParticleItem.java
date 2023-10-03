package lee.code.trails.menus.menu.menudata;

import lee.code.trails.lang.Lang;
import lee.code.trails.trails.data.TrailParticle;
import lee.code.trails.utils.CoreUtil;
import lee.code.trails.utils.ItemUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public enum ParticleItem {
  HAPPY_VILLAGER(TrailParticle.HAPPY_VILLAGER, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTI4Mzk2NTkyM2MwZTNlYWQwZmU4NzYwZTA4Y2JhODk4MmUzM2E2YWNkYjg5MWVjYzZmNmRmZTZkNWQxODBiYyJ9fX0="),
  VILLAGER_ANGRY(TrailParticle.VILLAGER_ANGRY, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmFiZWMyNDBhNGNiNTFjYWRjMTg1YTY2MDcyYTJhZWQ3ZjU2MTEyNjk2ZjI0NjU3M2E1MmVlN2RmNGMzMiJ9fX0="),
  REDSTONE(TrailParticle.REDSTONE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmIyYzI2NzhlOTM2NDA5ODhlNjg1YWM4OTM0N2VmYTQ1MjQxMTljOWQ4ZjcyNzhjZTAxODFiYzNlNGZiMmIwOSJ9fX0="),
  RAINBOW(TrailParticle.RAINBOW, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjg3ZmQyM2E3ODM2OWJkMzg3NWRhODg5NmYxNTBjNGFmOWYyMzM3NGUwNDhlMzA5MTM5MDBlM2ZkZDc3ODU5YSJ9fX0="),
  SPELL_MOB(TrailParticle.SPELL_MOB, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2UwOTM5MTc0Nzk3OWU5NGM5N2EyNTk0NTM1NTE2M2ExODFjZDllNDkwZDY5MDE0MzFkNGZiMGFkNzgxZmFjYyJ9fX0="),
  FALLING_DUST(TrailParticle.FALLING_DUST, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTM1YTUwYTNhYjYyZTg2YWMxYzE1MDJmMjNlZDE3ZmFjZDQyM2M4M2ZiN2NjNWFlNTMyZDc4ZDU0MjQ4NmY4In19fQ=="),
  BLOCK_CRACK(TrailParticle.BLOCK_CRACK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTI1OTVlMTVjYWZkOWQzZmE1NmM1ZjRhZjA2YjRkOWRmNTNjNTQzNjQ0ZjUyYmE3ZmVkZWEzYjVjYjk1YjA3OCJ9fX0="),

  ;

  @Getter private final TrailParticle trailParticle;
  private final String skin;

  public ItemStack createItem() {
    return ItemUtil.createItem(Material.PLAYER_HEAD, Lang.MENU_PARTICLE_ITEM_TITLE.getString(new String[]{CoreUtil.capitalize(trailParticle.name())}), Lang.MENU_PARTICLE_ITEM_LORE.getString(null), 0, skin);
  }

  public boolean hasPermission(Player player) {
    return player.hasPermission("trails.particle." + trailParticle.name().toLowerCase());
  }
}
