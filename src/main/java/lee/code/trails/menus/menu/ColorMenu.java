package lee.code.trails.menus.menu;

import lee.code.trails.Trails;
import lee.code.trails.lang.Lang;
import lee.code.trails.menus.menu.menudata.ColorItem;
import lee.code.trails.menus.system.MenuButton;
import lee.code.trails.menus.system.MenuPaginatedGUI;
import lee.code.trails.trails.data.TrailParticle;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ColorMenu extends MenuPaginatedGUI {
  private final Trails trails;
  private final TrailParticle trailParticle;

  public ColorMenu(Trails trails, TrailParticle trailParticle){
    this.trails = trails;
    this.trailParticle = trailParticle;
    setInventory();
  }

  @Override
  protected Inventory createInventory() {
    return Bukkit.createInventory(null, 54, Lang.MENU_COLOR_TITLE.getComponent(null));
  }

  @Override
  public void decorate(Player player) {
    addFillerGlass();
    int slot = 0;
    for (ColorItem colorItem : ColorItem.values()) {
      addButton(paginatedSlots.get(slot), createColorButton(player, colorItem));
      slot++;
    }
    super.decorate(player);
  }

  private MenuButton createColorButton(Player player, ColorItem colorItem) {
    return new MenuButton().creator(p -> colorItem.createItem())
      .consumer(e -> {
        getMenuSoundManager().playClickSound(player);
        trails.getMenuManager().openMenu(new StyleMenu(trails, trailParticle, colorItem.getData()), player);
      });
  }
}
