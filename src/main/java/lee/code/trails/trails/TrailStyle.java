package lee.code.trails.trails;

import lee.code.trails.trails.style.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TrailStyle {
  HELIX(new HelixStyle()),
  HALO(new HaloStyle()),
  SPHERE(new SphereStyle()),
  BLOCK_BREAK(new BlockBreakStyle()),
  CUBE(new CubeStyle()),
  PROJECTILE(new ProjectileStyle()),
  GROUND_SPIRAL(new GroundSpiralStyle()),
  FORCE_FIELD(new ForceFieldStyle()),
  ORBIT(new OrbitStyle()),
  PULSE(new PulseStyle()),
  NORMAL(new NormalStyle()),
  ;

  @Getter private final StyleInterface style;
}
