package com.incors.plaf.kunststoff;

/*
 * This code was developed by INCORS GmbH (www.incors.com).
 * It is published under the terms of the GNU Lesser General Public License.
 */

import java.awt.*;

import com.incors.plaf.*;

import javax.swing.JComponent;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

/**
 * Collection of methods often used in the Kunststoff Look&Feel
 */
public class KunststoffUtilities {

  /**
   * Immutable snapshot of the last gradient paint and its parameters. A single
   * volatile reference replaces the previous four-field cache so concurrent
   * painters never observe a paint paired with the wrong colors; the old
   * synchronized methods serialized every component paint unnecessarily.
   */
  private record CachedGradient(FastGradientPaint paint, Color color1, Color color2, boolean vertical) {}

  private record CachedGradientV2(GradientPaint paint, Color color1, Color color2, boolean vertical, Rectangle rect) {}

  private static volatile CachedGradient cachedGradient;
  private static volatile CachedGradientV2 cachedGradientV2;

  /**
   * Convenience method to create a translucent <code>Color</color>.
   */
  public static Color getTranslucentColor(Color color, int alpha) {
    if (color == null) {
      return null;
    } else if (alpha == 255) {
      return color;
    } else {
      return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }
  }

  /**
   * Convenience method to create a translucent <code>ColorUIResource</code>.
   */
  public static Color getTranslucentColorUIResource(Color color, int alpha) {
    if (color == null) {
      return null;
    } else if (alpha == 255) {
      return color;
    } else {
      return new ColorUIResource(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
    }
  }

  /**
   * Convenience method to draw a gradient on the specified rectangle
   */
  public static void drawGradient(Graphics g, Color color1, Color color2, Rectangle rect, boolean isVertical) {
    Graphics2D g2D = (Graphics2D) g;
    CachedGradient cached = cachedGradient;
    FastGradientPaint gradient;
    if (cached != null && cached.color1() == color1 && cached.color2() == color2 && cached.vertical() == isVertical) {
      gradient = cached.paint();
    } else {
      gradient = new FastGradientPaint(color1, color2, isVertical);
      cachedGradient = new CachedGradient(gradient, color1, color2, isVertical);
    }
    g2D.setPaint(gradient);
    g2D.fill(rect);
  }

  /**
   * Convenience method to draw a gradient. The first rectangle defines the drawing region,
   * the second rectangle defines the size of the gradient.
   */
  public static void drawGradient(Graphics g, Color color1, Color color2, Rectangle rect, Rectangle rect2, boolean isVertical) {
    Graphics2D g2D = (Graphics2D) g;
    GradientPaint gradient;

    // A zero-size rect would create a degenerate GradientPaint with coincident
    // anchor points, which throws IllegalArgumentException during paint.
    if (rect.width <= 0 || rect.height <= 0) {
      return;
    }

    CachedGradientV2 cached = cachedGradientV2;
    if (cached != null && cached.color1() == color1 && cached.color2() == color2 && cached.vertical() == isVertical && cached.rect().equals(rect)) {
      gradient = cached.paint();
    } else {
      if (isVertical) {
        gradient = new GradientPaint(0f, (float) rect.getY(), color1, 0f, (float) (rect.getHeight() + rect.getY()), color2);
      } else {
        gradient = new GradientPaint((float) rect.getX(), 0f, color1, (float) (rect.getWidth() + rect.getX()), 0f, color2);
      }
      cachedGradientV2 = new CachedGradientV2(gradient, color1, color2, isVertical, new Rectangle(rect));
    }
    g2D.setPaint(gradient);
    g2D.fill(rect);
  }

  /**
   * Returns true if the display uses 24- or 32-bit color depth (= true color)
   */
  public static boolean isToolkitTrueColor(Component c) {
    int pixelsize = c.getToolkit().getColorModel().getPixelSize();
    return pixelsize >= 24;
  }

    public static void resize(JComponent c) {
        Font font = c.getFont();
        if (font == null) {
            return;
        }
        // Metrics are resolved against the component's own Graphics/font
        // context, so they remain correct across DPI/scale changes. Do not
        // cache globally by Font: metrics are tied to the rendering context.
        FontMetrics fontMetrics = c.getFontMetrics(font);
        Dimension current = c.getPreferredSize();
        double h = current.getHeight();
        if (h < 2 * fontMetrics.getHeight()) {
            c.setPreferredSize(new Dimension((int) current.getWidth(), Math.max((int)current.getHeight(), 2 * fontMetrics.getHeight())));
        }
    }

    private static String cachedDefaultFontName = null;
    private static String cachedMonospaceFontName = null;
    private static String[] cachedFontFamilies = null;
    private static int cachedMinimumFontSize = -1;

    /**
     * Names of preferred monospaced fonts, in order of preference. The first one
     * found installed on the running system is used. The logical font
     * "Monospaced" is a guaranteed fallback on every Java platform.
     */
    private static final String[] PREFERRED_MONOSPACED_FONTS = {
        "Fira Code", "Monaco", "Consolas", "Droid Sans Mono", "Hack"
    };

    /**
     * Installed font family names, enumerated once. Both the proportional and
     * the monospaced font lookup need this list; scanning every installed font
     * twice at startup was wasted work.
     */
    private static String[] availableFontFamilies() {
        if (cachedFontFamilies == null) {
            cachedFontFamilies = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        }
        return cachedFontFamilies;
    }

    private static boolean[] fontFamilyAvailable(String[] candidates) {
        String[] fonts = availableFontFamilies();
        boolean[] available = new boolean[candidates.length];
        for (int i = 0; i < candidates.length; i++) {
            for (String f : fonts) {
                if (candidates[i].equalsIgnoreCase(f)) {
                    available[i] = true;
                    break;
                }
            }
        }
        return available;
    }

    /**
     * Get the default (proportional) font name from the environment, system
     * properties, or fallback to Segoe UI, Fira Sans, or the logical SansSerif.
     * The result is cached because enumerating installed fonts is expensive.
     */
    public static String getDefaultFontName() {
        if (cachedDefaultFontName != null) {
            return cachedDefaultFontName;
        }
        String fontName = System.getenv("KUNSTSTOFF_FONT");
        if (fontName == null || fontName.trim().isEmpty()) {
            fontName = System.getProperty("kunststoff.font");
        }

        if (fontName != null && !fontName.trim().isEmpty()) {
            cachedDefaultFontName = fontName.trim();
            return cachedDefaultFontName;
        }

        boolean[] available = fontFamilyAvailable(new String[] {"Segoe UI", "Fira Sans"});
        if (available[0]) {
            cachedDefaultFontName = "Segoe UI";
        } else if (available[1]) {
            cachedDefaultFontName = "Fira Sans";
        } else {
            cachedDefaultFontName = "SansSerif";
        }
        return cachedDefaultFontName;
    }

    /**
     * Get the name of the monospaced font. Checks KUNSTSTOFF_MONOSPACE_FONT
     * environment variable first, then falls back to the first preferred font
     * found installed, then to the logical font "Monospaced". The result is
     * cached because enumerating installed fonts is expensive.
     */
    public static String getMonospaceFontName() {
        if (cachedMonospaceFontName != null) {
            return cachedMonospaceFontName;
        }
        String envFont = System.getenv("KUNSTSTOFF_MONOSPACE_FONT");
        if (envFont != null && !envFont.trim().isEmpty()) {
            cachedMonospaceFontName = envFont.trim();
            return cachedMonospaceFontName;
        }
        boolean[] available = fontFamilyAvailable(PREFERRED_MONOSPACED_FONTS);
        for (int i = 0; i < PREFERRED_MONOSPACED_FONTS.length; i++) {
            if (available[i]) {
                cachedMonospaceFontName = PREFERRED_MONOSPACED_FONTS[i];
                return cachedMonospaceFontName;
            }
        }
        cachedMonospaceFontName = "Monospaced";
        return cachedMonospaceFontName;
    }

    /**
     * Minimum font size in points applied to every theme font. Configured with
     * the KUNSTSTOFF_MIN_FONT_SIZE environment variable; unset, empty, invalid,
     * or non-positive values use the default of 12 points. The result is cached
     * because the value is read once when the look and feel is initialised.
     */
    public static int getMinimumFontSize() {
        if (cachedMinimumFontSize >= 0) {
            return cachedMinimumFontSize;
        }
        int minimum = 12;
        String value = System.getenv("KUNSTSTOFF_MIN_FONT_SIZE");
        if (value != null && !value.trim().isEmpty()) {
            try {
                int parsed = Integer.parseInt(value.trim());
                if (parsed > 0) {
                    minimum = parsed;
                }
            } catch (NumberFormatException ex) {
                // Invalid value: keep the default floor
            }
        }
        cachedMinimumFontSize = minimum;
        return cachedMinimumFontSize;
    }

    /**
     * Creates a UI font, raising its size to {@link #getMinimumFontSize()}.
     *
     * @param name  font family name
     * @param style font style, see {@link Font}
     * @param size  requested size in points
     * @return a {@link FontUIResource} no smaller than the configured minimum
     */
    public static FontUIResource createFont(String name, int style, int size) {
        return new FontUIResource(name, style, Math.max(size, getMinimumFontSize()));
    }
}
