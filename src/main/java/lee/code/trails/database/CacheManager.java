package lee.code.trails.database;

import lee.code.trails.database.cache.CachePlayers;
import lombok.Getter;

public class CacheManager {
  @Getter private final CachePlayers cachePlayers;

  public CacheManager(DatabaseManager databaseManager) {
    this.cachePlayers = new CachePlayers(databaseManager);
  }
}
