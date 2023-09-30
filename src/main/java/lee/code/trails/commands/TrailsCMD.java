package lee.code.trails.commands;

import lee.code.trails.Lang;
import lee.code.trails.Trails;
import lee.code.trails.trails.TrailParticle;
import lee.code.trails.trails.TrailStyle;
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
    trails.getTrailManager().startTrail(player, TrailParticle.END_ROD, TrailStyle.SPHERE);
    return true;
  }
}
