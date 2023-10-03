package lee.code.trails.menus.menu;

import lee.code.trails.Trails;
import lee.code.trails.lang.Lang;
import lee.code.trails.menus.menu.menudata.ParticleItem;
import lee.code.trails.menus.system.MenuButton;
import lee.code.trails.menus.system.MenuPaginatedGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

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
    super.decorate(player);
  }

  private MenuButton createParticleButton(Player player, ParticleItem particleItem) {
    return new MenuButton().creator(p -> particleItem.createItem())
      .consumer(e -> {
        getMenuSoundManager().playClickSound(player);
        switch (particleItem.getTrailParticle()) {
          case REDSTONE, SPELL_MOB ->
            trails.getMenuManager().openMenu(new ColorMenu(trails, particleItem.getTrailParticle()), player);
          case FALLING_DUST, BLOCK_CRACK ->
            trails.getMenuManager().openMenu(new BlockMenu(trails, particleItem.getTrailParticle()), player);
          default ->
            trails.getMenuManager().openMenu(new StyleMenu(trails, particleItem.getTrailParticle(), new String[]{}), player);
        }
      });
  }
}
