package lee.code.trails.menus.system;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class MenuSoundManager {

  public void playClickSound(Player player) {
    player.playSound(player, Sound.UI_BUTTON_CLICK, (float) 0.5, (float) 1);
  }

  public void playRedeemBookSound(Player player) {
    player.playSound(player, Sound.ENTITY_ALLAY_ITEM_GIVEN, (float) 0.5, (float) 1);
  }
}
