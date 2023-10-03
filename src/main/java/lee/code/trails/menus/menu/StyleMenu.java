package lee.code.trails.menus.menu;

import lee.code.trails.Trails;
import lee.code.trails.lang.Lang;
import lee.code.trails.menus.menu.menudata.StyleItem;
import lee.code.trails.menus.system.MenuButton;
import lee.code.trails.menus.system.MenuPaginatedGUI;
import lee.code.trails.trails.data.TrailParticle;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class StyleMenu extends MenuPaginatedGUI {
  private final Trails trails;
  private final TrailParticle trailParticle;
  private final String[] trailData;

  public StyleMenu(Trails trails, TrailParticle trailParticle, String[] trailData){
    this.trails = trails;
    this.trailParticle = trailParticle;
    this.trailData = trailData;
    setInventory();
  }

  @Override
  protected Inventory createInventory() {
    return Bukkit.createInventory(null, 54, Lang.MENU_STYLE_TITLE.getComponent(null));
  }

  @Override
  public void decorate(Player player) {
    addBorderGlass();
    final ArrayList<StyleItem> particles = new ArrayList<>();
    for (StyleItem styleItem : StyleItem.values()) {
      if (styleItem.hasPermission(player)) particles.add(styleItem);
    }
    int slot = 0;
    for (int i = 0; i < maxItemsPerPage; i++) {
      index = maxItemsPerPage * page + i;
      if (index >= particles.size()) break;
      final StyleItem styleItem = particles.get(index);
      addButton(paginatedSlots.get(slot), createParticleButton(player, styleItem));
      slot++;
    }
    super.decorate(player);
  }

  private MenuButton createParticleButton(Player player, StyleItem styleItem) {
    return new MenuButton().creator(p -> styleItem.createItem())
      .consumer(e -> {
        getMenuSoundManager().playClickSound(player);
        trails.getTrailManager().startTrail(player, trailParticle, styleItem.getTrailStyle(), trailData);
        getInventory().close();
      });
  }
}
