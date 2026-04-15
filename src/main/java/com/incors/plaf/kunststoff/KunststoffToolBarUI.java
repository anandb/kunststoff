package com.incors.plaf.kunststoff;

/*
 * This code was developed by INCORS GmbH (www.incors.com)
 * based on a contribution by C.J. Kent
 * It is published under the terms of the GNU Lesser General Public License.
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;

public class KunststoffToolBarUI extends MetalToolBarUI {
  private static Color cachedReflectionColor = null;
  private static Color cachedShadowColor = null;
  private static Color cachedReflectionFaded = null;
  private static Color cachedShadowFaded = null;
  private Rectangle rectReflection = new Rectangle();
  private Rectangle rectShadow = new Rectangle();

  public static ComponentUI createUI(JComponent c) {
    return new KunststoffToolBarUI();
  }

  public void paint(Graphics g, JComponent c) {
    super.paint(g, c);

    int orientation = SwingConstants.HORIZONTAL;

    if (c instanceof JToolBar) {
      orientation = ((JToolBar) c).getOrientation();
    }

    if (orientation == SwingConstants.HORIZONTAL) {
      Color colorReflection = KunststoffLookAndFeel.getComponentGradientColorReflection();
      Color colorShadow = KunststoffLookAndFeel.getComponentGradientColorShadow();

      if (cachedReflectionColor != colorReflection) {
        cachedReflectionColor = colorReflection;
        cachedReflectionFaded = colorReflection != null ? KunststoffUtilities.getTranslucentColor(colorReflection, 0) : null;
      }
      if (cachedShadowColor != colorShadow) {
        cachedShadowColor = colorShadow;
        cachedShadowFaded = colorShadow != null ? KunststoffUtilities.getTranslucentColor(colorShadow, 0) : null;
      }

      if (cachedReflectionFaded != null) {
        rectReflection.setBounds(0, 0, c.getWidth(), c.getHeight()/2);
        KunststoffUtilities.drawGradient(g, colorReflection, cachedReflectionFaded, rectReflection, true);
      }
      if (cachedShadowFaded != null) {
        rectShadow.setBounds(0, c.getHeight()/2, c.getWidth(), c.getHeight()/2);
        KunststoffUtilities.drawGradient(g, cachedShadowFaded, colorShadow, rectShadow, true);
      }

    } else {
      Color colorReflection = KunststoffLookAndFeel.getComponentGradientColorReflection();
      Color colorShadow = KunststoffLookAndFeel.getComponentGradientColorShadow();

      if (cachedReflectionColor != colorReflection) {
        cachedReflectionColor = colorReflection;
        cachedReflectionFaded = colorReflection != null ? KunststoffUtilities.getTranslucentColor(colorReflection, 0) : null;
      }
      if (cachedShadowColor != colorShadow) {
        cachedShadowColor = colorShadow;
        cachedShadowFaded = colorShadow != null ? KunststoffUtilities.getTranslucentColor(colorShadow, 0) : null;
      }

      if (cachedReflectionFaded != null) {
        rectReflection.setBounds(0, 0, c.getWidth()/2, c.getHeight());
        KunststoffUtilities.drawGradient(g, colorReflection, cachedReflectionFaded, rectReflection, false);
      }
      if (cachedShadowFaded != null) {
        rectShadow.setBounds(c.getWidth()/2, 0, c.getWidth()/2, c.getHeight());
        KunststoffUtilities.drawGradient(g, cachedShadowFaded, colorShadow, rectShadow, false);
      }
    }

  }

}
