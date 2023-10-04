package lee.code.trails.database.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@DatabaseTable(tableName = "players")
public class PlayerTable {
  @DatabaseField(id = true, canBeNull = false)
  private UUID uniqueId;

  @DatabaseField(columnName = "particle")
  private String particle;

  @DatabaseField(columnName = "style")
  private String style;

  @DatabaseField(columnName = "particle_data")
  private String particleData;

  public PlayerTable(UUID uniqueId) {
    this.uniqueId = uniqueId;
  }
}
