/*
 * KunststoffDesktopTheme.java
 *
 * Created on 17. Oktober 2001, 22:40
 */
package com.incors.plaf.kunststoff.themes;

import java.awt.Font;
import java.awt.Insets;

import javax.swing.plaf.FontUIResource;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author christophw
 * @version
 */
public class KunststoffDesktopTheme extends com.incors.plaf.kunststoff.KunststoffTheme {

    private final FontUIResource controlFont;
    private final FontUIResource menuFont;
    private final FontUIResource windowTitleFont;
    private final FontUIResource monospacedFont;

    /**
     * Crates this Theme
     */
    public KunststoffDesktopTheme() {
        String fontName = com.incors.plaf.kunststoff.KunststoffUtilities.getDefaultFontName();
        menuFont = new FontUIResource(fontName, Font.PLAIN, 16);
        controlFont = new FontUIResource(fontName, Font.PLAIN, 16);
        windowTitleFont = new FontUIResource(fontName, Font.BOLD, 16);
        monospacedFont = new FontUIResource("Hack", Font.PLAIN, 14);
    }

    @Override
    public String getName() {
        return "Desktop";
    }

    /**
     * The Font of Labels in many cases
     */
    @Override
    public FontUIResource getControlTextFont() {
        return controlFont;
    }

    /**
     * The Font of Menus and MenuItems
     */
    @Override
    public FontUIResource getMenuTextFont() {
        return menuFont;
    }

    /**
     * The Font of Nodes in JTrees
     */
    @Override
    public FontUIResource getSystemTextFont() {
        return controlFont;
    }

    /**
     * The Font in TextFields, EditorPanes, etc.
     */
    @Override
    public FontUIResource getUserTextFont() {
        return controlFont;
    }

    /**
     * The Font of the Title of JInternalFrames
     */
    @Override
    public FontUIResource getWindowTitleFont() {
        return windowTitleFont;
    }

    @Override
    public void addCustomEntriesToTable(UIDefaults table) {
        super.addCustomEntriesToTable(table);
        table.put("PasswordField.font", monospacedFont);
        table.put("TextArea.font", monospacedFont);
        table.put("TextPane.font", monospacedFont);
        table.put("EditorPane.font", monospacedFont);

        // 1. **MenuItem Insets:** This is the most common way to add vertical padding.
        // Insets: (top, left, bottom, right)
        // To increase vertical space, increase the 'top' and 'bottom' values.
        // Example: (8, 4, 8, 4) gives 8 pixels of padding above and below the item's content.
//    UIManager.getDefaults().put("MenuItem.contentMargins", new Insets(20, 4, 20, 4));
        // 2. **Menu Insets:** The JMenu (parent) also often uses this property.
//    UIManager.getDefaults().put("Menu.contentMargins", new Insets(20, 4, 20, 4));
        // 3. **Separator Height (if applicable):** You might also want to adjust separators.
//    UIManager.getDefaults().put("MenuSeparator.height", 8); // Increase this for more space between groups.
        // 4. **Check/Radio Menu Items:** Apply the same change to these specific items as well.
        table.put("CheckBoxMenuItem.border", new EmptyBorder(8, 4, 8, 4));
        table.put("RadioButtonMenuItem.border", new EmptyBorder(8, 4, 8, 4));

        // --- Other potentially relevant keys (use with caution) ---
        // If 'contentMargins' doesn't work in your specific base L&F, you might need to try:
        table.put("MenuItem.border", new EmptyBorder(8, 4, 8, 4));
        table.put("Menu.margin", new Insets(8, 4, 8, 4));
        table.put("Menu.border", new EmptyBorder(8, 4, 8, 4));
    }
}
