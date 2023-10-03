package lee.code.trails.trails.data;

import lee.code.trails.trails.data.StyleInterface;
import lee.code.trails.trails.data.StyleType;
import lee.code.trails.trails.style.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TrailStyle {
  HELIX(new HelixStyle(), StyleType.TIMER),
  HALO(new HaloStyle(), StyleType.TIMER),
  SPHERE(new SphereStyle(), StyleType.TIMER),
  CUBE(new CubeStyle(), StyleType.TIMER),
  GROUND_SPIRAL(new GroundSpiralStyle(), StyleType.TIMER),
  FORCE_FIELD(new ForceFieldStyle(), StyleType.TIMER),
  ORBIT(new OrbitStyle(), StyleType.TIMER),
  PULSE(new PulseStyle(), StyleType.TIMER),
  NORMAL(new NormalStyle(), StyleType.TIMER),
  THICK(new ThickStyle(), StyleType.TIMER),
  HEART(new HeartStyle(), StyleType.TIMER),
  BLOCK(new BlockStyle(), StyleType.EVENT),
  DAMAGE(new DamageStyle(), StyleType.EVENT),
  PROJECTILE(new ProjectileStyle(), StyleType.EVENT),
  ;

  @Getter private final StyleInterface style;
  @Getter private final StyleType type;
}
