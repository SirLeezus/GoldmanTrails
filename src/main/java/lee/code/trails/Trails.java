package lee.code.trails;

import com.mojang.brigadier.tree.LiteralCommandNode;
import lee.code.trails.commands.TabCompletion;
import lee.code.trails.commands.TrailsCMD;
import lee.code.trails.database.CacheManager;
import lee.code.trails.database.DatabaseManager;
import lee.code.trails.listeners.JoinListener;
import lee.code.trails.listeners.QuitListener;
import lee.code.trails.menus.system.MenuListener;
import lee.code.trails.menus.system.MenuManager;
import lee.code.trails.trails.TrailManager;
import lee.code.trails.trails.data.TrailStyle;
import lee.code.trails.trails.style.BlockStyle;
import lee.code.trails.trails.style.DamageStyle;
import lee.code.trails.trails.style.ProjectileStyle;
import lombok.Getter;
import me.lucko.commodore.CommodoreProvider;
import me.lucko.commodore.file.CommodoreFileReader;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Trails extends JavaPlugin {
  @Getter private TrailManager trailManager;
  @Getter private MenuManager menuManager;
  @Getter private CacheManager cacheManager;
  private DatabaseManager databaseManager;

  @Override
  public void onEnable() {
    this.databaseManager = new DatabaseManager(this);
    this.cacheManager = new CacheManager(databaseManager);
    this.menuManager = new MenuManager();
    this.trailManager = new TrailManager(this);
    databaseManager.initialize(false);
    registerCommands();
    registerListeners();
  }

  @Override
  public void onDisable() {
    databaseManager.closeConnection();
  }

  private void registerCommands() {
    getCommand("trails").setExecutor(new TrailsCMD(this));
    getCommand("trails").setTabCompleter(new TabCompletion());
    loadCommodoreData();
  }

  private void registerListeners() {
    getServer().getPluginManager().registerEvents((ProjectileStyle) TrailStyle.PROJECTILE.getStyle(), this);
    getServer().getPluginManager().registerEvents((BlockStyle) TrailStyle.BLOCK.getStyle(), this);
    getServer().getPluginManager().registerEvents((DamageStyle) TrailStyle.DAMAGE.getStyle(), this);
    getServer().getPluginManager().registerEvents(new MenuListener(menuManager), this);
    getServer().getPluginManager().registerEvents(new QuitListener(this), this);
    getServer().getPluginManager().registerEvents(new JoinListener(this), this);
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
