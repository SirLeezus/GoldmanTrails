package lee.code.trails.lang;

import lee.code.trails.utils.CoreUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.kyori.adventure.text.Component;

@AllArgsConstructor
public enum Lang {
  PREFIX("&#DF01DF&lTrails &6➔ "),
  COMMAND_HELP_TITLE("&a----------------- &7[ &#DF01DF&lTrails Help &7] &a-----------------"),
  COMMAND_HELP_LINE_1("&eTrails are for premium rank players only. Trails will spawn"),
  COMMAND_HELP_LINE_2("&eparticles around the player when running or idle. You"),
  COMMAND_HELP_LINE_3("&ecan select your trail and trail style from the &3/trails"),
  COMMAND_HELP_LINE_4("&ecommand."),
  COMMAND_HELP_FOOTER("&a-------------------------------------------------"),
  ON("&2&lON"),
  OFF("&c&lOFF"),
  MENU_PARTICLE_TITLE("&#DF01DF&lTrail Particles"),
  MENU_COLOR_TITLE("&#DF01DF&lTrail Color Option"),
  MENU_BLOCK_TITLE("&#DF01DF&lTrail Block Option"),
  MENU_STYLE_TITLE("&#DF01DF&lTrail Styles"),
  MENU_OPTION_TITLE("&#DF01DF&lTrail Particle Options"),
  MENU_PARTICLE_ITEM_TITLE("&e&l{0}"),
  MENU_PARTICLE_ITEM_LORE("&6» &aClick to activate trail!"),
  MENU_COLOR_ITEM_LORE("&6» &aClick to activate color!"),
  MENU_BLOCK_ITEM_LORE("&6» &aClick to activate block!"),
  MENU_BLOCK_ITEM_NAME("&e&l{0}"),
  MENU_STYLE_ITEM_TITLE("&e&l{0}"),
  MENU_TOGGLE_TRAIL_SUCCESSFUL("&aYou successfully toggled your trail {0}&a!"),
  MENU_STYLE_ITEM_LORE("&6» &aClick to activate style!"),
  ERROR_NOT_CONSOLE_COMMAND("&cThis command does not work in console."),
  ERROR_PREVIOUS_PAGE("&7You are already on the first page."),
  ERROR_NEXT_PAGE("&7You are on the last page."),
  ERROR_NO_TRAIL_DATA("&cYou have never selected a trail before so it can't be toggled."),

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
