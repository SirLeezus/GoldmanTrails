package lee.code.trails.menus.menu.menudata;

import lee.code.trails.lang.Lang;
import lee.code.trails.utils.ItemUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public enum ColorItem {
  WHITE(Material.WHITE_CONCRETE, "&#FFFFFF&lWhite", new String[]{"255", "255", "255"}),
  LIGHT_GRAY(Material.LIGHT_GRAY_CONCRETE, "&#8E8E8E&lLight Gray", new String[]{"142", "142", "142"}),
  GRAY(Material.GRAY_CONCRETE, "&#515151&lGray", new String[]{"81", "81", "81"}),
  BLACK(Material.BLACK_CONCRETE, "&#323232&lBlack", new String[]{"0", "0", "0"}),
  BROWN(Material.BROWN_CONCRETE, "&#7B3F00&lBrown", new String[]{"123", "63", "0"}),
  RED(Material.RED_CONCRETE, "&#FF0000&lRed", new String[]{"255", "0", "0"}),
  ORANGE(Material.ORANGE_CONCRETE, "&#FF5500&lOrange", new String[]{"255", "85", "0"}),
  YELLOW(Material.YELLOW_CONCRETE, "&#FAFF58&lYellow", new String[]{"250", "255", "88"}),
  LIME(Material.LIME_CONCRETE, "&#00ff00&lLime", new String[]{"0", "255", "0"}),
  GREEN(Material.GREEN_CONCRETE, "&#00B808&lGreen", new String[]{"0", "184", "8"}),
  CYAN(Material.CYAN_CONCRETE, "&#00C0CC&lCyan", new String[]{"0", "192", "204"}),
  LIGHT_BLUE(Material.LIGHT_BLUE_CONCRETE, "&#51CDFF&lLight Blue", new String[]{"81", "205", "255"}),
  BLUE(Material.BLUE_CONCRETE, "&#005EC8&lBlue", new String[]{"0", "94", "200"}),
  PURPLE(Material.PURPLE_CONCRETE, "&#8A00E3&lPurple", new String[]{"138", "0", "227"}),
  MAGENTA(Material.MAGENTA_CONCRETE, "&#DF01DF&lMagenta", new String[]{"223", "1", "223"}),
  PINK(Material.PINK_CONCRETE, "&#FF68F1&lPink", new String[]{"255", "104", "241"}),

  ;

  private final Material material;
  private final String name;
  @Getter private final String[] data;

  public ItemStack createItem() {
    return ItemUtil.createItem(material, name, Lang.MENU_COLOR_ITEM_LORE.getString(), 0, null);
  }
}
