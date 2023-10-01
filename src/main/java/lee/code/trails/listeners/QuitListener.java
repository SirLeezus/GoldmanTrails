package lee.code.trails.listeners;

import lee.code.trails.Trails;
import lee.code.trails.utils.RainbowUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {
  private final Trails trails;

  public QuitListener(Trails trails) {
    this.trails = trails;
  }

  @EventHandler
  public void onTrailActiveQuit(PlayerQuitEvent e) {
    if (trails.getTrailManager().hasActiveTrail(e.getPlayer().getUniqueId())) {
      trails.getTrailManager().stopTrail(e.getPlayer());
    }
    RainbowUtil.removeHueData(e.getPlayer().getUniqueId());
  }
}
