package com.incors.plaf.kunststoff;

/*
 * This code was developed by INCORS GmbH (www.incors.com).
 * It is published under the terms of the GNU Lesser General Public License.
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;

public class KunststoffButtonUI extends MetalButtonUI {
  private final static KunststoffButtonUI buttonUI = new KunststoffButtonUI();
  private static Color cachedReflectionColor = null;
  private static Color cachedShadowColor = null;
  private static Color cachedReflectionFaded = null;
  private static Color cachedShadowFaded = null;
  private Rectangle rectReflection = new Rectangle();
  private Rectangle rectShadow = new Rectangle();

  public static ComponentUI createUI(JComponent c) {
    return buttonUI;
  }

  public void paint(Graphics g, JComponent c) {
    super.paint(g, c);

    if (!c.isOpaque()) {
      return;
    }

    Component parent = c.getParent();
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

    if(parent instanceof JToolBar) {
      int orientation = ((JToolBar) parent).getOrientation();
      Point loc = c.getLocation();

      if(orientation == SwingConstants.HORIZONTAL) {
        if (cachedReflectionFaded != null) {
          rectReflection.setBounds(0, -loc.y, parent.getWidth(), parent.getHeight()/2);
          KunststoffUtilities.drawGradient(g, colorReflection, cachedReflectionFaded, rectReflection, true);
        }
        if (cachedShadowFaded != null) {
          rectShadow.setBounds(0, parent.getHeight()/2 - loc.y, parent.getWidth(), parent.getHeight()/2);
          KunststoffUtilities.drawGradient(g, cachedShadowFaded, colorShadow, rectShadow, true);
        }
      } else {
        if (cachedReflectionFaded != null) {
          rectReflection.setBounds(0, 0, parent.getWidth()/2, parent.getHeight());
          KunststoffUtilities.drawGradient(g, colorReflection, cachedReflectionFaded, rectReflection, false);
        }
        if (cachedShadowFaded != null) {
          rectShadow.setBounds(parent.getWidth()/2 - loc.x, 0, parent.getWidth(), parent.getHeight());
          KunststoffUtilities.drawGradient(g, cachedShadowFaded, colorShadow, rectShadow, false);
        }
      }
    } else {
      if (cachedReflectionFaded != null) {
        rectReflection.setBounds(0, 0, c.getWidth(), c.getHeight()/2);
        KunststoffUtilities.drawGradient(g, colorReflection, cachedReflectionFaded, rectReflection, true);
      }
      if (cachedShadowFaded != null) {
        rectShadow.setBounds(0, c.getHeight()/2, c.getWidth(), c.getHeight()/2);
        KunststoffUtilities.drawGradient(g, cachedShadowFaded, colorShadow, rectShadow, true);
      }
    }
  }

}