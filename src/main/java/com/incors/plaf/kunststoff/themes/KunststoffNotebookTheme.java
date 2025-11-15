/*
 * KunststoffDesktopTheme.java
 *
 * Created on 17. Oktober 2001, 22:40
 */
package com.incors.plaf.kunststoff.themes;

import java.awt.Font;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

/**
 *
 * @author christophw
 * @version
 */
public class KunststoffNotebookTheme extends com.incors.plaf.kunststoff.KunststoffTheme {

    // primary colors
    private final ColorUIResource primary1 = new ColorUIResource(22, 22, 54);
    private final ColorUIResource primary2 = new ColorUIResource(110, 110, 130);
    private final ColorUIResource primary3 = new ColorUIResource(150, 150, 170);

    // secondary colors
    private final ColorUIResource secondary1 = new ColorUIResource(100, 100, 100);
    private final ColorUIResource secondary2 = new ColorUIResource(130, 130, 130);
    private final ColorUIResource secondary3 = new ColorUIResource(180, 180, 180);
    //  private final ColorUIResource secondary3 = new ColorUIResource(224, 224, 224);

    // fonts
    private FontUIResource controlFont;
    private FontUIResource menuFont;
    private FontUIResource windowTitleFont;
    private FontUIResource monospacedFont;

    /**
     * Crates this Theme
     */
    public KunststoffNotebookTheme() {
        menuFont = new FontUIResource("Segoe UI", Font.PLAIN, 12);
        controlFont = new FontUIResource("Segoe UI", Font.PLAIN, 11);
        windowTitleFont = new FontUIResource("Segoe UI", Font.BOLD, 12);
        monospacedFont = new FontUIResource("Hack", Font.PLAIN, 11);
    }

    public String getName() {
        return "Notebook";
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

    public ColorUIResource getPrimary1() {
        return primary1;
    }

    public ColorUIResource getPrimary2() {
        return primary2;
    }

    public ColorUIResource getPrimary3() {
        return primary3;
    }

    public ColorUIResource getSecondary1() {
        return secondary1;
    }

    public ColorUIResource getSecondary2() {
        return secondary2;
    }

    public ColorUIResource getSecondary3() {
        return secondary3;
    }

    public void addCustomEntriesToTable(UIDefaults table) {
        super.addCustomEntriesToTable(table);
        UIManager.getDefaults().put("PasswordField.font", monospacedFont);
        UIManager.getDefaults().put("TextArea.font", monospacedFont);
        UIManager.getDefaults().put("TextPane.font", monospacedFont);
        UIManager.getDefaults().put("EditorPane.font", monospacedFont);
    }
}
