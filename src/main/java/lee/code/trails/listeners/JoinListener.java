package lee.code.trails.listeners;

import lee.code.trails.Trails;
import lee.code.trails.utils.RainbowUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {
  private final Trails trails;

  public JoinListener(Trails trails) {
    this.trails = trails;
  }

  @EventHandler
  public void onTrailActiveQuit(PlayerJoinEvent e) {
    if (trails.getCacheManager().getCachePlayers().hasPlayerData(e.getPlayer().getUniqueId())) {
      trails.getCacheManager().getCachePlayers().createPlayerData(e.getPlayer().getUniqueId());
    }
  }
}
