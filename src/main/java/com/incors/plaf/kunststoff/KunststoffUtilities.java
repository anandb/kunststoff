package com.incors.plaf.kunststoff;

/*
 * This code was developed by INCORS GmbH (www.incors.com).
 * It is published under the terms of the GNU Lesser General Public License.
 */

import java.awt.*;
import java.util.WeakHashMap;

import com.incors.plaf.*;

import javax.swing.JComponent;

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
      return new ColorUIResource2(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }
  }

  /**
   * Convenience method to draw a gradient on the specified rectangle
   */
  public static void drawGradient(Graphics g, Color color1, Color color2, Rectangle rect, boolean isVertical) {
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
  public static void drawGradient(Graphics g, Color color1, Color color2, Rectangle rect, Rectangle rect2, boolean isVertical) {
    Graphics2D g2D = (Graphics2D) g;
    GradientPaint gradient;

    Boolean isVert = isVertical ? Boolean.TRUE : Boolean.FALSE;
    if (cachedGradientColor1_v2 == color1 && cachedGradientColor2_v2 == color2 && cachedGradientVertical_v2 == isVert) {
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

    private static final WeakHashMap<Font, FontMetrics> fontMetricsCache = new WeakHashMap<>();

    public static void resize(JComponent c) {
        Font font = c.getFont();
        FontMetrics fontMetrics = fontMetricsCache.get(font);
        if (fontMetrics == null) {
            fontMetrics = c.getFontMetrics(font);
            fontMetricsCache.put(font, fontMetrics);
        }
        Dimension current = c.getPreferredSize();
        double h = current.getHeight();
        if (h < 2 * fontMetrics.getHeight()) {
            c.setPreferredSize(new Dimension((int) current.getWidth(), Math.max((int)current.getHeight(), 2 * fontMetrics.getHeight())));
        }
    }
}
