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

    private FontUIResource controlFont;
    private FontUIResource menuFont;
    private FontUIResource windowTitleFont;
    private FontUIResource monospacedFont;

    /**
     * Crates this Theme
     */
    public KunststoffDesktopTheme() {
        menuFont = new FontUIResource("Segoe UI", Font.PLAIN, 8);
        controlFont = new FontUIResource("Segoe UI", Font.PLAIN, 11);
        windowTitleFont = new FontUIResource("Segoe UI", Font.BOLD, 8);
        monospacedFont = new FontUIResource("Hack", Font.PLAIN, 11);
    }

    public String getName() {
        return "Desktop";
    }

    /**
     * The Font of Labels in many cases
     */
    public FontUIResource getControlTextFont() {
        return controlFont;
    }

    /**
     * The Font of Menus and MenuItems
     */
    public FontUIResource getMenuTextFont() {
        return menuFont;
    }

    /**
     * The Font of Nodes in JTrees
     */
    public FontUIResource getSystemTextFont() {
        return controlFont;
    }

    /**
     * The Font in TextFields, EditorPanes, etc.
     */
    public FontUIResource getUserTextFont() {
        return controlFont;
    }

    /**
     * The Font of the Title of JInternalFrames
     */
    public FontUIResource getWindowTitleFont() {
        return windowTitleFont;
    }

    public void addCustomEntriesToTable(UIDefaults table) {
        super.addCustomEntriesToTable(table);
        UIManager.getDefaults().put("PasswordField.font", monospacedFont);
        UIManager.getDefaults().put("TextArea.font", monospacedFont);
        UIManager.getDefaults().put("TextPane.font", monospacedFont);
        UIManager.getDefaults().put("EditorPane.font", monospacedFont);

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
        UIManager.getDefaults().put("CheckBoxMenuItem.border", new EmptyBorder(8, 4, 8, 4));
        UIManager.getDefaults().put("RadioButtonMenuItem.border", new EmptyBorder(8, 4, 8, 4));

        // --- Other potentially relevant keys (use with caution) ---
        // If 'contentMargins' doesn't work in your specific base L&F, you might need to try:
        UIManager.getDefaults().put("MenuItem.border", new EmptyBorder(8, 4, 8, 4));
        UIManager.getDefaults().put("Menu.margin", new Insets(8, 4, 8, 4));
        UIManager.getDefaults().put("Menu.border", new EmptyBorder(8, 4, 8, 4));
    }
}
