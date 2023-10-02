package lee.code.trails.commands;

import lee.code.trails.Lang;
import lee.code.trails.Trails;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.TrailParticle;
import lee.code.trails.trails.TrailStyle;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TrailsCMD implements CommandExecutor {
  private final Trails trails;

  public TrailsCMD(Trails trails) {
    this.trails = trails;
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (!(sender instanceof Player player)) {
      sender.sendMessage(Lang.PREFIX.getComponent(null).append(Lang.ERROR_NOT_CONSOLE_COMMAND.getComponent(null)));
      return true;
    }
    final TrailManager trailManager = trails.getTrailManager();
    if (trailManager.hasActiveTrail(player.getUniqueId())) trailManager.stopTrail(player);
    else trails.getTrailManager().startTrail(player, TrailParticle.BLOCK_CRACK, TrailStyle.HELIX, new String[]{Material.EMERALD_BLOCK.name()});
    return true;
  }
}
