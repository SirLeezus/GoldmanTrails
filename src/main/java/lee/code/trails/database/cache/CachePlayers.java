package lee.code.trails.database.cache;

import lee.code.trails.database.DatabaseManager;
import lee.code.trails.database.handler.DatabaseHandler;
import lee.code.trails.database.tables.PlayerTable;
import lee.code.trails.trails.data.TrailParticle;
import lee.code.trails.trails.data.TrailStyle;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CachePlayers extends DatabaseHandler {

  public CachePlayers(DatabaseManager databaseManager) {
    super(databaseManager);
  }

  private final ConcurrentHashMap<UUID, PlayerTable> playersCache = new ConcurrentHashMap<>();

  public PlayerTable getPlayerTable(UUID uuid) {
    return playersCache.get(uuid);
  }

  public void setPlayerTable(PlayerTable playerTable) {
    playersCache.put(playerTable.getUniqueId(), playerTable);
  }

  public boolean hasPlayerData(UUID uuid) {
    return playersCache.containsKey(uuid);
  }

  public void createPlayerData(UUID uuid) {
    final PlayerTable playerTable = new PlayerTable(uuid);
    setPlayerTable(playerTable);
    createPlayerDatabase(playerTable);
  }

  public TrailParticle getParticle(UUID uuid) {
    return TrailParticle.valueOf(getPlayerTable(uuid).getParticle());
  }

  public TrailStyle getStyle(UUID uuid) {
    return TrailStyle.valueOf(getPlayerTable(uuid).getStyle());
  }

  public String[] getParticleData(UUID uuid) {
    return getPlayerTable(uuid).getParticleData().split(",");
  }

  public void saveTrailSelection(UUID uuid, TrailParticle trailParticle, TrailStyle trailStyle, String[] trailData) {
    final PlayerTable playerTable = getPlayerTable(uuid);
    playerTable.setParticle(trailParticle.name());
    playerTable.setStyle(trailStyle.name());
    playerTable.setParticleData(StringUtils.join(trailData, ","));
    updatePlayerDatabase(playerTable);
  }

  public boolean hasTrailData(UUID uuid) {
    return getPlayerTable(uuid).getParticle() != null;
  }
}
