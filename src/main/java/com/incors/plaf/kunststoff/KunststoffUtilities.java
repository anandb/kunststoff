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

  private static FastGradientPaint cachedGradientPaint = null;
  private static Color cachedGradientColor1 = null;
  private static Color cachedGradientColor2 = null;
  private static Boolean cachedGradientVertical = null;

  private static GradientPaint cachedGradientPaint2 = null;
  private static Color cachedGradientColor1_v2 = null;
  private static Color cachedGradientColor2_v2 = null;
  private static Boolean cachedGradientVertical_v2 = null;
  private static Rectangle cachedGradientRect2 = null;

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
  public static synchronized void drawGradient(Graphics g, Color color1, Color color2, Rectangle rect, boolean isVertical) {
    Graphics2D g2D = (Graphics2D) g;
    FastGradientPaint gradient;
    if (cachedGradientColor1 == color1 && cachedGradientColor2 == color2 && cachedGradientVertical == isVertical) {
      gradient = cachedGradientPaint;
    } else {
      gradient = new FastGradientPaint(color1, color2, isVertical);
      cachedGradientPaint = gradient;
      cachedGradientColor1 = color1;
      cachedGradientColor2 = color2;
      cachedGradientVertical = isVertical;
    }
    g2D.setPaint(gradient);
    g2D.fill(rect);
  }

  /**
   * Convenience method to draw a gradient. The first rectangle defines the drawing region,
   * the second rectangle defines the size of the gradient.
   */
  public static synchronized void drawGradient(Graphics g, Color color1, Color color2, Rectangle rect, Rectangle rect2, boolean isVertical) {
    Graphics2D g2D = (Graphics2D) g;
    GradientPaint gradient;

    // A zero-size rect would create a degenerate GradientPaint with coincident
    // anchor points, which throws IllegalArgumentException during paint.
    if (rect.width <= 0 || rect.height <= 0) {
      return;
    }

    Boolean isVert = isVertical ? Boolean.TRUE : Boolean.FALSE;
    if (cachedGradientColor1_v2 == color1 && cachedGradientColor2_v2 == color2 && cachedGradientVertical_v2 == isVert && cachedGradientRect2 != null && cachedGradientRect2.equals(rect)) {
      gradient = cachedGradientPaint2;
    } else {
      if (isVertical) {
        gradient = new GradientPaint(0f, (float) rect.getY(), color1, 0f, (float) (rect.getHeight() + rect.getY()), color2);
      } else {
        gradient = new GradientPaint((float) rect.getX(), 0f, color1, (float) (rect.getWidth() + rect.getX()), 0f, color2);
      }
      cachedGradientPaint2 = gradient;
      cachedGradientColor1_v2 = color1;
      cachedGradientColor2_v2 = color2;
      cachedGradientVertical_v2 = isVert;
      cachedGradientRect2 = new Rectangle(rect);
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
    private static int cachedMinimumFontSize = -1;

    /**
     * Names of preferred monospaced fonts, in order of preference. The first one
     * found installed on the running system is used. The logical font
     * "Monospaced" is a guaranteed fallback on every Java platform.
     */
    private static final String[] PREFERRED_MONOSPACED_FONTS = {
        "Fira Code", "Monaco", "Consolas", "Droid Sans Mono", "Hack"
    };

    private static boolean[] fontFamilyAvailable(String[] candidates) {
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
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
