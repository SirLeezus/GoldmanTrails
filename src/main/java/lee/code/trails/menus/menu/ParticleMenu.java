package lee.code.trails.menus.menu;

import lee.code.trails.Trails;
import lee.code.trails.database.cache.CachePlayers;
import lee.code.trails.lang.Lang;
import lee.code.trails.menus.menu.menudata.MenuItem;
import lee.code.trails.menus.menu.menudata.ParticleItem;
import lee.code.trails.menus.system.MenuButton;
import lee.code.trails.menus.system.MenuPaginatedGUI;
import lee.code.trails.trails.TrailManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.UUID;

public class ParticleMenu extends MenuPaginatedGUI {
  private final Trails trails;

  public ParticleMenu(Trails trails){
    this.trails = trails;
    setInventory();
  }

  @Override
  protected Inventory createInventory() {
    return Bukkit.createInventory(null, 54, Lang.MENU_PARTICLE_TITLE.getComponent(null));
  }

  @Override
  public void decorate(Player player) {
    addBorderGlass();
    final ArrayList<ParticleItem> particles = new ArrayList<>();
    for (ParticleItem particleItem : ParticleItem.values()) {
      if (particleItem.hasPermission(player)) particles.add(particleItem);
    }
    int slot = 0;
    for (int i = 0; i < maxItemsPerPage; i++) {
      index = maxItemsPerPage * page + i;
      if (index >= particles.size()) break;
      final ParticleItem particleItem = particles.get(index);
      addButton(paginatedSlots.get(slot), createParticleButton(player, particleItem));
      slot++;
    }
    addPaginatedButtons(player);
    addToggleTailButton(player);
    super.decorate(player);
  }

  private MenuButton createParticleButton(Player player, ParticleItem particleItem) {
    return new MenuButton().creator(p -> particleItem.createItem())
      .consumer(e -> {
        getMenuSoundManager().playClickSound(player);
        switch (particleItem.getTrailParticle()) {
          case REDSTONE, POTION_EFFECT ->
            trails.getMenuManager().openMenu(new ColorMenu(trails, particleItem.getTrailParticle()), player);
          case FALLING_DUST, BLOCK_CRACK ->
            trails.getMenuManager().openMenu(new BlockMenu(trails, particleItem.getTrailParticle()), player);
          default ->
            trails.getMenuManager().openMenu(new StyleMenu(trails, particleItem.getTrailParticle(), new String[]{}), player);
        }
      });
  }

  private void addToggleTailButton(Player player) {
    addButton(49, new MenuButton().creator(p -> MenuItem.TOGGLE_TRAIL.createToggleItem(trails.getTrailManager().hasActiveTrail(player.getUniqueId())))
      .consumer(e -> {
        final TrailManager trailManager = trails.getTrailManager();
        final CachePlayers cachePlayers = trails.getCacheManager().getCachePlayers();
        getMenuSoundManager().playClickSound(player);
        final UUID uuid = player.getUniqueId();
        if (trailManager.hasActiveTrail(uuid)) {
          trailManager.stopTrail(player);
          player.sendMessage(Lang.PREFIX.getComponent(null).append(Lang.MENU_TOGGLE_TRAIL_SUCCESSFUL.getComponent(new String[]{Lang.OFF.getString()})));
          getInventory().close();
        } else {
          if (cachePlayers.hasTrailData(uuid)) {
            trails.getTrailManager().startTrail(player, cachePlayers.getParticle(uuid), cachePlayers.getStyle(uuid), cachePlayers.getParticleData(uuid));
            player.sendMessage(Lang.PREFIX.getComponent(null).append(Lang.MENU_TOGGLE_TRAIL_SUCCESSFUL.getComponent(new String[]{Lang.ON.getString()})));
            getInventory().close();
          } else {
            player.sendMessage(Lang.PREFIX.getComponent(null).append(Lang.ERROR_NO_TRAIL_DATA.getComponent(null)));
          }
        }
      }));
  }

  private void addPaginatedButtons(Player player) {
    addButton(51, new MenuButton().creator(p -> MenuItem.NEXT_PAGE.createItem())
      .consumer(e -> {
        getMenuSoundManager().playClickSound(player);
        if (!((index + 1) >= ParticleItem.values().length)) {
          page += 1;
          clearInventory();
          clearButtons();
          decorate(player);
        } else player.sendMessage(Lang.PREFIX.getComponent(null).append(Lang.ERROR_NEXT_PAGE.getComponent(null)));
      }));
    addButton(47, new MenuButton().creator(p -> MenuItem.PREVIOUS_PAGE.createItem())
      .consumer(e -> {
        getMenuSoundManager().playClickSound(player);
        if (page == 0) {
          player.sendMessage(Lang.PREFIX.getComponent(null).append(Lang.ERROR_PREVIOUS_PAGE.getComponent(null)));
        } else {
          page -= 1;
          clearInventory();
          clearButtons();
          decorate(player);
        }
      }));
  }
}
