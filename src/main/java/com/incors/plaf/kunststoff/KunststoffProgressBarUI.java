package com.incors.plaf.kunststoff;

/*
 * This code was developed by INCORS GmbH (www.incors.com)
 * It is published under the terms of the Lesser GNU Public License.
 *
 * The original class was contributed by
 * Julien Ponge
 * julien@izforge.com
 * http://www.izforge.com/
 *
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;

public class KunststoffProgressBarUI extends BasicProgressBarUI {
  private static Color cachedReflectionColor = null;
  private static Color cachedShadowColor = null;
  private static Color cachedReflectionFaded = null;
  private static Color cachedShadowFaded = null;
  private Rectangle rectReflection = new Rectangle();
  private Rectangle rectShadow = new Rectangle();

  public static ComponentUI createUI(JComponent c) {
      return new KunststoffProgressBarUI();
  }

  public void paint(Graphics g, JComponent c) {
    super.paint(g, c);

    JProgressBar prog = (JProgressBar) c;
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

    if (prog.getOrientation() == JProgressBar.HORIZONTAL) {
      if (cachedReflectionFaded != null) {
        rectReflection.setBounds(0, 0, c.getWidth(), c.getHeight()/2);
        KunststoffUtilities.drawGradient(g, colorReflection, cachedReflectionFaded, rectReflection, true);
      }
      if (cachedShadowFaded != null) {
        rectShadow.setBounds(0, c.getHeight()/2, c.getWidth(), c.getHeight()/2);
        KunststoffUtilities.drawGradient(g, cachedShadowFaded, colorShadow, rectShadow, true);
      }
    } else {
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