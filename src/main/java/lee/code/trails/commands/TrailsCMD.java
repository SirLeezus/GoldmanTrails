package lee.code.trails.commands;

import lee.code.trails.lang.Lang;
import lee.code.trails.Trails;
import lee.code.trails.menus.menu.ParticleMenu;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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
    if (args.length > 0 && args[0].equalsIgnoreCase("help")) {
      final List<Component> lines = new ArrayList<>();
      lines.add(Lang.COMMAND_HELP_TITLE.getComponent(null));
      lines.add(Component.text(""));
      lines.add(Lang.COMMAND_HELP_LINE_1.getComponent(null));
      lines.add(Lang.COMMAND_HELP_LINE_2.getComponent(null));
      lines.add(Lang.COMMAND_HELP_LINE_3.getComponent(null));
      lines.add(Lang.COMMAND_HELP_LINE_4.getComponent(null));
      lines.add(Component.text(""));
      lines.add(Lang.COMMAND_HELP_FOOTER.getComponent(null));
      for (Component line : lines) sender.sendMessage(line);
      return true;
    }
    trails.getMenuManager().openMenu(new ParticleMenu(trails), player);
    return true;
  }
}
