package lee.code.trails.menus.menu.menudata;

import lee.code.trails.lang.Lang;
import lee.code.trails.utils.CoreUtil;
import lee.code.trails.utils.ItemUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public enum BlockItem {
  NETHERITE_BLOCK(Material.NETHERITE_BLOCK),
  EMERALD_BLOCK(Material.EMERALD_BLOCK),
  DIAMOND_BLOCK(Material.DIAMOND_BLOCK),
  GOLD_BLOCK(Material.GOLD_BLOCK),
  IRON_BLOCK(Material.IRON_BLOCK),
  COPPER_BLOCK(Material.COPPER_BLOCK),
  COAL_BLOCK(Material.COAL_BLOCK),
  LAPIS_BLOCK(Material.LAPIS_BLOCK),
  REDSTONE_BLOCK(Material.REDSTONE_BLOCK),
  RED_MUSHROOM_BLOCK(Material.RED_MUSHROOM_BLOCK),
  SLIME_BLOCK(Material.SLIME_BLOCK),
  HONEY_BLOCK(Material.HONEY_BLOCK),
  SCULK(Material.SCULK),
  SHROOMLIGHT(Material.SHROOMLIGHT),
  CRYING_OBSIDIAN(Material.CRYING_OBSIDIAN),
  VERDANT_FROGLIGHT(Material.VERDANT_FROGLIGHT),
  MAGMA_BLOCK(Material.MAGMA_BLOCK),
  PURPUR_BLOCK(Material.PURPUR_BLOCK),
  AMETHYST_BLOCK(Material.AMETHYST_BLOCK),
  ORANGE_GLAZED_TERRACOTTA(Material.ORANGE_GLAZED_TERRACOTTA),
  WHITE_GLAZED_TERRACOTTA(Material.WHITE_GLAZED_TERRACOTTA),
  PURPLE_GLAZED_TERRACOTTA(Material.PURPLE_GLAZED_TERRACOTTA),
  LIGHT_BLUE_GLAZED_TERRACOTTA(Material.LIGHT_BLUE_GLAZED_TERRACOTTA),
  CHERRY_LEAVES(Material.CHERRY_LEAVES),
  ICE(Material.ICE),
  MELON(Material.MELON),
  PRISMARINE(Material.PRISMARINE),
  SPAWNER(Material.SPAWNER)

  ;

  @Getter private final Material material;

  public ItemStack createItem() {
    final ItemStack item = ItemUtil.createItem(material, Lang.MENU_BLOCK_ITEM_NAME.getString(new String[]{CoreUtil.capitalize(material.name())}), Lang.MENU_BLOCK_ITEM_LORE.getString(), 0, null);
    ItemUtil.hideItemFlags(item);
    return item;
  }
}
