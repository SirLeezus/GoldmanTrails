package lee.code.trails.trails;

import lee.code.trails.trails.style.HaloStyle;
import lee.code.trails.trails.style.HelixStyle;
import lee.code.trails.trails.style.SphereStyle;
import lee.code.trails.trails.style.SpinningHelixStyle;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TrailStyle {
  HELIX(new HelixStyle()),
  SPINNING_HELIX(new SpinningHelixStyle()),
  HALO(new HaloStyle()),
  SPHERE(new SphereStyle()),

  ;

  @Getter private final StyleInterface style;
}
