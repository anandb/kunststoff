/*
 * KunststoffDesktopTheme.java
 *
 * Created on 17. Oktober 2001, 22:40
 */
package com.incors.plaf.kunststoff.themes;

import java.awt.Font;

import javax.swing.plaf.FontUIResource;

/**
 *
 * @author christophw
 * @version
 */
public class KunststoffDesktopTheme extends com.incors.plaf.kunststoff.KunststoffTheme {

    private final FontUIResource controlFont;
    private final FontUIResource menuFont;
    private final FontUIResource windowTitleFont;

    /**
     * Crates this Theme
     */
    public KunststoffDesktopTheme() {
        String fontName = com.incors.plaf.kunststoff.KunststoffUtilities.getDefaultFontName();
        String monoFontName = com.incors.plaf.kunststoff.KunststoffUtilities.getMonospaceFontName();
        menuFont = new FontUIResource(fontName, Font.PLAIN, 16);
        controlFont = new FontUIResource(fontName, Font.PLAIN, 16);
        windowTitleFont = new FontUIResource(fontName, Font.BOLD, 16);
        monospacedFont = new FontUIResource(monoFontName, Font.PLAIN, 14);
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
}
