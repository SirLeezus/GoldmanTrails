package lee.code.trails.lang;

import lee.code.trails.utils.CoreUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.kyori.adventure.text.Component;

@AllArgsConstructor
public enum Lang {
  PREFIX("&d&lTrails &6➔ "),
  MENU_PARTICLE_TITLE("&d&lTrail Particles"),
  MENU_COLOR_TITLE("&d&lTrail Color Option"),
  MENU_STYLE_TITLE("&d&lTrail Styles"),
  MENU_OPTION_TITLE("&d&lTrail Particle Options"),
  MENU_PARTICLE_ITEM_TITLE("&e&l{0}"),
  MENU_PARTICLE_ITEM_LORE("&6» &aClick to activate trail!"),
  MENU_COLOR_ITEM_LORE("&6» &aClick to activate color!"),
  MENU_STYLE_ITEM_TITLE("&e&l{0}"),
  MENU_STYLE_ITEM_LORE("&6» &aClick to activate style!"),
  ERROR_NOT_CONSOLE_COMMAND("&cThis command does not work in console."),

  ;
  @Getter private final String string;

  public String getString(String[] variables) {
    String value = string;
    if (variables == null || variables.length == 0) return value;
    for (int i = 0; i < variables.length; i++) value = value.replace("{" + i + "}", variables[i]);
    return value;
  }

  public Component getComponent(String[] variables) {
    String value = string;
    if (variables == null || variables.length == 0) return CoreUtil.parseColorComponent(value);
    for (int i = 0; i < variables.length; i++) value = value.replace("{" + i + "}", variables[i]);
    return CoreUtil.parseColorComponent(value);
  }
}
