package lee.code.trails.utils;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class RainbowUtil {
  private final static ConcurrentHashMap<UUID, Double> hues = new ConcurrentHashMap<>();

  public static String[] getNextColor(UUID uuid) {
    if (!hues.containsKey(uuid)) hues.put(uuid, 0.0);
    double hue = hues.get(uuid);

    // Calculate the next color using HSV color space
    final int rgb = hsvToRgb(hue);

    // Extract the individual color components (red, green, blue) from the calculated color
    final int red = (rgb >> 16) & 0xFF;
    final int green = (rgb >> 8) & 0xFF;
    final int blue = rgb & 0xFF;

    // Update the hue for the next iteration
    hue += 0.005;
    if (hue > 1.0) hue = 0.0;

    hues.put(uuid, hue);
    return new String[]{String.valueOf(red), String.valueOf(green), String.valueOf(blue)};
  }

  private static int hsvToRgb(double hue) {
    final int h = (int) (hue * 6);
    final double f = hue * 6 - h;
    final double p = 0.0;
    final double q = (1 - f);
    final double t = (1 - (1 - f));

    return switch (h) {
      case 0 -> ((int) (1.0 * 255) << 16) | ((int) (t * 255) << 8);
      case 1 -> ((int) (q * 255) << 16) | ((int) (1.0 * 255) << 8);
      case 2 -> ((int) (p * 255) << 16) | ((int) (1.0 * 255) << 8) | (int) (t * 255);
      case 3 -> ((int) (p * 255) << 16) | ((int) (q * 255) << 8) | (int) (1.0 * 255);
      case 4 -> ((int) (t * 255) << 16) | ((int) (p * 255) << 8) | (int) (1.0 * 255);
      case 5 -> ((int) (1.0 * 255) << 16) | ((int) (p * 255) << 8) | (int) (q * 255);
      default -> throw new IllegalArgumentException("Invalid hue value");
    };
  }

  public static void removeHueData(UUID uuid) {
    hues.remove(uuid);
  }
}
