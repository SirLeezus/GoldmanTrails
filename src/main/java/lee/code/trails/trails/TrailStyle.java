package lee.code.trails.trails;

import lee.code.trails.trails.style.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TrailStyle {
  HELIX(new HelixStyle()),
  SPINNING_HELIX(new SpinningHelixStyle()),
  HALO(new HaloStyle()),
  SPHERE(new SphereStyle()),
  BLOCK_BREAK(new BlockBreakStyle()),

  ;

  @Getter private final StyleInterface style;
}
