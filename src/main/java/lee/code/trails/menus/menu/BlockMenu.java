package lee.code.trails.menus.menu;

import lee.code.trails.Trails;
import lee.code.trails.lang.Lang;
import lee.code.trails.menus.menu.menudata.BlockItem;
import lee.code.trails.menus.menu.menudata.MenuItem;
import lee.code.trails.menus.system.MenuButton;
import lee.code.trails.menus.system.MenuPaginatedGUI;
import lee.code.trails.trails.data.TrailParticle;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class BlockMenu extends MenuPaginatedGUI {
  private final Trails trails;
  private final TrailParticle trailParticle;

  public BlockMenu(Trails trails, TrailParticle trailParticle){
    this.trails = trails;
    this.trailParticle = trailParticle;
    setInventory();
  }

  @Override
  protected Inventory createInventory() {
    return Bukkit.createInventory(null, 54, Lang.MENU_BLOCK_TITLE.getComponent(null));
  }

  @Override
  public void decorate(Player player) {
    addBorderGlass();
    int slot = 0;
    for (BlockItem blockItem : BlockItem.values()) {
      addButton(paginatedSlots.get(slot), createBlockButton(player, blockItem));
      slot++;
    }
    addBackButton(player);
    super.decorate(player);
  }

  private MenuButton createBlockButton(Player player, BlockItem blockItem) {
    return new MenuButton().creator(p -> blockItem.createItem())
      .consumer(e -> {
        getMenuSoundManager().playClickSound(player);
        trails.getMenuManager().openMenu(new StyleMenu(trails, trailParticle, new String[]{blockItem.getMaterial().name()}), player);
      });
  }

  private void addBackButton(Player player) {
    addButton(49, new MenuButton()
      .creator(p -> MenuItem.BACK_MENU.createItem())
      .consumer(e -> {
        getMenuSoundManager().playClickSound(player);
        trails.getMenuManager().openMenu(new ParticleMenu(trails), player);
      }));
  }
}
