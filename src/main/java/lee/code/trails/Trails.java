package lee.code.trails;

import com.mojang.brigadier.tree.LiteralCommandNode;
import lee.code.trails.commands.TabCompletion;
import lee.code.trails.commands.TrailsCMD;
import lee.code.trails.trails.TrailManager;
import lombok.Getter;
import me.lucko.commodore.CommodoreProvider;
import me.lucko.commodore.file.CommodoreFileReader;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Trails extends JavaPlugin {
  @Getter private TrailManager trailManager;

  @Override
  public void onEnable() {
    this.trailManager = new TrailManager(this);
    registerCommands();
  }

  @Override
  public void onDisable() {

  }

  private void registerCommands() {
    getCommand("trails").setExecutor(new TrailsCMD(this));
    getCommand("trails").setTabCompleter(new TabCompletion());
    loadCommodoreData();
  }

  private void registerListeners() {

  }

  private void loadCommodoreData() {
    try {
      final LiteralCommandNode<?> towns = CommodoreFileReader.INSTANCE.parse(getResource("trails.commodore"));
      CommodoreProvider.getCommodore(this).register(getCommand("trails"), towns);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
