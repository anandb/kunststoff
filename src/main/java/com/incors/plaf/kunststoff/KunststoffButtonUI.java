package com.incors.plaf.kunststoff;

/*
 * This code was developed by INCORS GmbH (www.incors.com).
 * It is published under the terms of the GNU Lesser General Public License.
 */

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;

public class KunststoffButtonUI extends MetalButtonUI {
  private final static KunststoffButtonUI buttonUI = new KunststoffButtonUI();
  private static Color cachedReflectionColor = null;
  private static Color cachedShadowColor = null;
  private static Color cachedReflectionFaded = null;
  private static Color cachedShadowFaded = null;
  private final Rectangle rectReflection = new Rectangle();
  private final Rectangle rectShadow = new Rectangle();

  public static ComponentUI createUI(JComponent c) {
    return buttonUI;
  }

  @Override
  public void installDefaults(AbstractButton b) {
    super.installDefaults(b);
    // Remove Metal's rectangular border so our rounded outline is the only border.
    // Note: we do NOT force setOpaque(true) here — the body is painted based on
    // contentAreaFilled, and overriding opaque would defeat callers (e.g. NetBeans)
    // that explicitly ask for a non-opaque button.
    b.setBorderPainted(false);
  }

  @Override
  public void paint(Graphics g, JComponent c) {
    // Toolbar buttons stay flat — no rounded corners, no gradient
    if (c.getParent() instanceof JToolBar) {
      super.paint(g, c);
      return;
    }

    // The button body is controlled by contentAreaFilled, NOT by opaque.
    // Metal (and FlatLaf) paint the button chrome regardless of the opaque flag;
    // Swing's own contract says opaque only means "this component fills its
    // bounds". NetBeans, for example, creates some of its buttons with
    // setOpaque(false) (see autoupdate's HeaderPanel/DetailsPanel); if we skip
    // painting those we end up with bare text on the parent's background.
    boolean paintBody = isBodyPainted(c);
    int arc = paintBody ? Math.min(14, c.getHeight()) : 0;
    RoundRectangle2D shape = new RoundRectangle2D.Float(0, 0, c.getWidth(), c.getHeight(), arc, arc);

    Graphics2D g2 = (Graphics2D) g.create();
    try {
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      if (paintBody) {
        // Paint the rounded background first — this replaces Metal's rectangular fill.
        // If the button is not opaque the rounded corners are not cleared by Swing,
        // so paint whatever is behind the button first to avoid stale pixels there.
        if (!c.isOpaque()) {
          paintParentBackground(g2, c);
        }
        Color bg = c.getBackground();
        if (bg != null) {
          g2.setColor(bg);
          g2.fill(shape);
        }
        g2.setClip(shape);
      }

      // Now super.paint() (MetalButtonUI) draws bevels + text, all clipped to the rounded shape
      super.paint(g2, c);
      paintGradient(g2, c);
    } finally {
      g2.dispose();
    }

    // Draw rounded outline on top
    if (paintBody) {
      Graphics2D gb = (Graphics2D) g.create();
      try {
        gb.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gb.setColor(getOutlineColor(c));
        gb.setStroke(new BasicStroke(1f));
        gb.draw(new RoundRectangle2D.Float(0.5f, 0.5f, c.getWidth() - 1f, c.getHeight() - 1f, arc, arc));
      } finally {
        gb.dispose();
      }
    }
  }

  /**
   * Whether this button paints its own body (rounded fill, gradient, outline).
   * Swing's {@code contentAreaFilled} property is the switch for that, as in
   * MetalButtonUI — the {@code opaque} flag only says whether Swing fills the
   * rectangular background behind us.
   */
  private boolean isBodyPainted(JComponent c) {
    if (c instanceof AbstractButton) {
      return ((AbstractButton) c).isContentAreaFilled();
    }
    return c.isOpaque();
  }

  /**
   * Paints the background of the first opaque ancestor, so the area outside our
   * rounded shape looks like the surrounding container instead of garbage.
   */
  private static void paintParentBackground(Graphics g, JComponent c) {
    Container parent = c.getParent();
    if (parent == null) {
      return;
    }
    Color bg = getOpaqueParentBackground(parent);
    if (bg != null) {
      g.setColor(bg);
      g.fillRect(0, 0, c.getWidth(), c.getHeight());
    }
  }

  private static Color getOpaqueParentBackground(Container parent) {
    Color bg = parent.getBackground();
    if (bg == null || !parent.isOpaque()) {
      Container grandParent = parent.getParent();
      return grandParent != null ? getOpaqueParentBackground(grandParent) : null;
    }
    return bg;
  }

  /**
   * Outline color that reacts to the button state: accent on hover/press,
   * muted grey when disabled, dark otherwise.
   */
  private Color getOutlineColor(JComponent c) {
    if (!c.isEnabled()) {
      return MetalLookAndFeel.getControlShadow();
    }
    AbstractButton b = (AbstractButton) c;
    ButtonModel m = b.getModel();
    if (m.isPressed() || (b.isRolloverEnabled() && m.isRollover())) {
      return MetalLookAndFeel.getFocusColor();
    }
    return MetalLookAndFeel.getControlDarkShadow();
  }

  private void paintGradient(Graphics g, JComponent c) {
    if (!isBodyPainted(c)) {
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
          rectShadow.setBounds(0, parent.getHeight()/2 - loc.y, parent.getWidth(), parent.getHeight() - parent.getHeight()/2);
          KunststoffUtilities.drawGradient(g, cachedShadowFaded, colorShadow, rectShadow, true);
        }
      } else {
        if (cachedReflectionFaded != null) {
          rectReflection.setBounds(0, 0, parent.getWidth()/2, parent.getHeight());
          KunststoffUtilities.drawGradient(g, colorReflection, cachedReflectionFaded, rectReflection, false);
        }
        if (cachedShadowFaded != null) {
          rectShadow.setBounds(parent.getWidth()/2 - loc.x, 0, parent.getWidth() - parent.getWidth()/2, parent.getHeight());
          KunststoffUtilities.drawGradient(g, cachedShadowFaded, colorShadow, rectShadow, false);
        }
      }
    } else {
      if (cachedReflectionFaded != null) {
        rectReflection.setBounds(0, 0, c.getWidth(), c.getHeight()/2);
        KunststoffUtilities.drawGradient(g, colorReflection, cachedReflectionFaded, rectReflection, true);
      }
      if (cachedShadowFaded != null) {
        rectShadow.setBounds(0, c.getHeight()/2, c.getWidth(), c.getHeight() - c.getHeight()/2);
        KunststoffUtilities.drawGradient(g, cachedShadowFaded, colorShadow, rectShadow, true);
      }
    }
  }

}