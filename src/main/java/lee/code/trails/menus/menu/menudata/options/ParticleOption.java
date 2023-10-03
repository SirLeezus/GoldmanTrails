package lee.code.trails.menus.menu.menudata.options;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ParticleOption {
  HAPPY_VILLAGER(null),
  VILLAGER_ANGRY(null),
  REDSTONE(new String[]{ Option.RAINBOW.toString(), Option.COLOR.toString() }),

  ;

  @Getter private final String[] options;
}
