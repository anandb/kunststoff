package com.incors.plaf.kunststoff;

/*
 * This code was developed for the Kunststoff Look&Feel.
 * It is published under the terms of the Lesser GNU General Public License.
 */

import java.awt.FontMetrics;
import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTableUI;

/**
 * Table UI that keeps rows tall enough for the fonts of this look and feel.
 * <p>
 * <code>JTable</code> has no notion of a font-derived row height: it starts at
 * 16 pixels and stays there unless the application sets one. With the larger
 * fonts this look and feel installs, such rows clip the text of their cells.
 * The row height is therefore derived from the table's font - one line of text
 * plus its intercell spacing and a little room for cell borders - and derived
 * again whenever the font changes. Rows taller than that are left alone, so
 * applications that size rows for their content keep them.
 */
public class KunststoffTableUI extends BasicTableUI {

  /** Room kept beyond the font's line height, enough for cell borders. */
  private static final int EXTRA_ROW_HEIGHT = 2;

  private final PropertyChangeListener fontListener = evt -> updateRowHeight();

  public static ComponentUI createUI(JComponent c) {
    return new KunststoffTableUI();
  }

  @Override
  protected void installDefaults() {
    super.installDefaults();
    updateRowHeight();
    table.addPropertyChangeListener("font", fontListener);
  }

  @Override
  protected void uninstallDefaults() {
    table.removePropertyChangeListener("font", fontListener);
    super.uninstallDefaults();
  }

  /** Grows rows that are too short for one line of the table's font. */
  private void updateRowHeight() {
    int needed = fontBasedRowHeight();
    if (table.getRowHeight() < needed) {
      table.setRowHeight(needed);
    }
  }

  /** One line of the table's font, its intercell spacing and some slack. */
  private int fontBasedRowHeight() {
    FontMetrics metrics = table.getFontMetrics(table.getFont());
    return metrics.getHeight() + table.getIntercellSpacing().height + EXTRA_ROW_HEIGHT;
  }
}
