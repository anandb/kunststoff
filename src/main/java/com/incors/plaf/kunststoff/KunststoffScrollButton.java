package com.incors.plaf.kunststoff;

/*
 * This code was developed by INCORS GmbH (www.incors.com).
 * It is published under the terms of the GNU Lesser General Public License.
 */

import java.awt.*;
import javax.swing.plaf.metal.*;

public class KunststoffScrollButton extends MetalScrollButton {
  private static Color cachedReflectionColor = null;
  private static Color cachedShadowColor = null;
  private static Color cachedReflectionFaded = null;
  private static Color cachedShadowFaded = null;
  private Rectangle rectReflection = new Rectangle();
  private Rectangle rectShadow = new Rectangle();

  public KunststoffScrollButton( int direction, int width, boolean freeStanding) {
    super(direction, width, freeStanding);
  }

  /**
   * Calls the super classes paint(Graphics g) method and then paints two gradients.
   * The direction of the gradients depends on the direction of the scrollbar.
   */
  public void paint(Graphics g) {
    super.paint(g);
    int width = getWidth();
    int height = getHeight();
    boolean isVertical = (getDirection() == EAST || getDirection() == WEST);

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

    if (isVertical) {
      rectReflection.setBounds(1, 1, width, height/2);
      rectShadow.setBounds(1, height/2, width, height/2+1);
    } else {
      rectReflection.setBounds(1, 1, width/2, height);
      rectShadow.setBounds(width/2, 1, width/2+1, height);
    }

    if (cachedReflectionFaded != null) {
      KunststoffUtilities.drawGradient(g, colorReflection, cachedReflectionFaded, rectReflection, isVertical);
    }

    if (cachedShadowFaded != null) {
      KunststoffUtilities.drawGradient(g, cachedShadowFaded, colorShadow, rectShadow, isVertical);
    }

  }


}