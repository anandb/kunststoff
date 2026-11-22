package com.incors.plaf.kunststoff;

/*
 * This code was developed by Matthew Philips and INCORS GmbH (www.incors.com).
 * It is published under the terms of the Lesser GNU Public License.
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;

/**
 * The KunststoffComboBoxUI is a little bit tricky, but it should work fine in
 * most cases. It currently draws only correctly if the renderer of the combo
 * box is an instance of <code>JComponent</code> and the background color is an
 * instance of <code>ColorUIResource</code>. In a default <code>JComboBox</code>
 * with a default renderer this should be the case.
 */
public class KunststoffComboBoxUI extends MetalComboBoxUI {

  public static ComponentUI createUI(JComponent c) {
    return new KunststoffComboBoxUI();
  }

  /**
   * Installs MyMetalComboBoxButton.
   * <p>
   * The button keeps only the horizontal padding of the theme's Button.border:
   * vertical padding is applied while painting and only as far as the value
   * still fits (see {@link MyMetalComboBoxButton#getInsets()}), while the combo
   * box height keeps accounting for it through {@link #getMinimumSize()}.
   */
  @Override
  protected JButton createArrowButton() {
    JButton button = new MyMetalComboBoxButton (comboBox, new MetalComboBoxIcon(),
                                                comboBox.isEditable() ? true : false,
                                                currentValuePane, listBox);
    button.setMargin(new Insets(0, 1, 1, 3 ));
    Insets padding = buttonPadding();
    button.setBorder(BorderFactory.createEmptyBorder(0, padding.left, 0, padding.right));
    return button;
  }

  /**
   * Insets of the border a combo box arrow button inherits from the theme's
   * <code>Button.border</code>.
   */
  private Insets buttonPadding() {
    Border border = UIManager.getBorder("Button.border");
    return border != null ? border.getBorderInsets(comboBox) : new Insets(0, 0, 0, 0);
  }

  /**
   * Keeps the combo box height Metal derives from the arrow button's border
   * insets. The button itself has no vertical border padding (see
   * {@link #createArrowButton()}), so the theme's button padding is added here;
   * the height therefore matches what it was before that padding moved out of
   * the border, and dialog layouts are unaffected.
   */
  @Override
  public Dimension getMinimumSize(JComponent c) {
    Dimension size = super.getMinimumSize(c);
    if (!comboBox.isEditable()) {
      Insets padding = buttonPadding();
      size.height += padding.top + padding.bottom;
    }
    return size;
  }

  /*
   * This inner class finally fixed a nasty bug with the combo box. Thanks to
   * Matthew Philips for providing the bugfix.
   * Thanks to Ingo Kegel for fixing two compiling issues for jikes.
   */
  private final class MyMetalComboBoxButton extends javax.swing.plaf.metal.MetalComboBoxButton {

    public MyMetalComboBoxButton(JComboBox cb, Icon i, boolean onlyIcon,
                                CellRendererPane pane, JList list) {
      super (cb, i, onlyIcon, pane, list);
    }

    /** Set while the button paints, so getInsets can tell painting from layout. */
    private boolean paintingValue;

    /**
     * Insets MetalComboBoxButton uses to place the selected value inside the
     * button. While painting, the theme's vertical Button.border padding is
     * applied only as far as the button stays tall enough for one line of text:
     * a combo box squeezed below its preferred height (as dialogs often do)
     * then paints the value in full instead of clipping it, while a combo box
     * with room keeps its padding - and with it the frame around the value,
     * which the value's own (often opaque) background would otherwise cover.
     * <p>
     * Outside painting the plain border insets are reported, so preferred and
     * minimum sizes stay independent of the button's momentary height.
     */
    @Override
    public Insets getInsets() {
      Insets border = super.getInsets();
      if (!paintingValue) {
        return border;
      }
      Insets padding = buttonPadding();
      int lineHeight = getFontMetrics(getFont()).getHeight();
      int slack = Math.max(0, (getHeight() - lineHeight) / 2);
      return new Insets(Math.min(padding.top, slack), border.left,
                        Math.min(padding.bottom, slack), border.right);
    }

    @Override
    public void paintComponent(Graphics g) {
      paintingValue = true;
      try {
        paintValue(g);
      } finally {
        paintingValue = false;
      }
    }

    private void paintValue(Graphics g) {
      if (! iconOnly && MyMetalComboBoxButton.this.comboBox != null) {
        boolean isSetRendererOpaque = false;
        ListCellRenderer renderer = MyMetalComboBoxButton.this.comboBox.getRenderer();
        if (renderer instanceof JComponent) {
          JComponent jRenderer = (JComponent) renderer;
          if (jRenderer.isOpaque() && jRenderer.getBackground() instanceof ColorUIResource) {
            isSetRendererOpaque = true;  // remember to set the renderer opaque again
            jRenderer.setOpaque(false);
          }
        }
        try {
          super.paintComponent(g);
        } finally {
          if (isSetRendererOpaque) {
            ((JComponent) renderer).setOpaque(true);
          }
        }
      } else {
        super.paintComponent(g);
      }
    }

  }
}
