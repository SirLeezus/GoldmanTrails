package lee.code.trails.menus.system;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DelayManager {
  private final Map<UUID, Long> delays = new HashMap<>();

  private void addDelay(UUID uuid) {
    final long expirationTime = System.currentTimeMillis() + 200;
    delays.put(uuid, expirationTime);
  }

  private void removeDelay(UUID uuid) {
    delays.remove(uuid);
  }

  private boolean hasDelay(UUID uuid) {
    if (!delays.containsKey(uuid)) return false;
    if (System.currentTimeMillis() >= delays.get(uuid)) {
      removeDelay(uuid);
      return false;
    }
    return true;
  }

  public boolean hasDelayOrSchedule(UUID uuid) {
    if (hasDelay(uuid)) {
      return true;
    } else {
      addDelay(uuid);
      return false;
    }
  }
}
