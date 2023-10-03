package lee.code.trails.menus.menu.menudata.options;

import lombok.AllArgsConstructor;
import org.bukkit.Material;

@AllArgsConstructor
public enum Option {
  COLOR(Material.RED_DYE, "&e&lColor"),
  RAINBOW(Material.RED_DYE, "&e&lRainbow"),
  BLOCK(Material.GOLD_BLOCK, "&e&lBlock"),

  ;
  private final Material material;
  private final String name;
}
