/*
 * KunststoffDesktopTheme.java
 *
 * Created on 17. Oktober 2001, 22:40
 */
package com.incors.plaf.kunststoff.themes;

import java.awt.Font;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

import com.incors.plaf.kunststoff.KunststoffTheme;
import com.incors.plaf.kunststoff.KunststoffUtilities;

/**
 *
 * @author christophw
 * @version
 */
public class KunststoffNotebookTheme extends KunststoffTheme {

    // primary colors
    private final ColorUIResource primary1 = new ColorUIResource(22, 22, 54);
    private final ColorUIResource primary2 = new ColorUIResource(110, 110, 130);
    private final ColorUIResource primary3 = new ColorUIResource(150, 150, 170);

    // secondary colors
    private final ColorUIResource secondary1 = new ColorUIResource(100, 100, 100);
    private final ColorUIResource secondary2 = new ColorUIResource(130, 130, 130);
    private final ColorUIResource secondary3 = new ColorUIResource(170, 170, 185);
    //  private final ColorUIResource secondary3 = new ColorUIResource(224, 224, 224);

    // fonts
    private final FontUIResource controlFont;
    private final FontUIResource menuFont;
    private final FontUIResource windowTitleFont;

    /**
     * Crates this Theme
     */
    public KunststoffNotebookTheme() {
        String fontName = KunststoffUtilities.getDefaultFontName();
        String monoFontName = KunststoffUtilities.getMonospaceFontName();
        menuFont = KunststoffUtilities.createFont(fontName, Font.PLAIN, 12);
        controlFont = KunststoffUtilities.createFont(fontName, Font.PLAIN, 11);
        windowTitleFont = KunststoffUtilities.createFont(fontName, Font.BOLD, 12);
        monospacedFont = KunststoffUtilities.createFont(monoFontName, Font.PLAIN, 11);
    }

    @Override
    public String getName() {
        return "Notebook";
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
    public ColorUIResource getPrimary1() {
        return primary1;
    }

    @Override
    public ColorUIResource getPrimary2() {
        return primary2;
    }

    @Override
    public ColorUIResource getPrimary3() {
        return primary3;
    }

    @Override
    public ColorUIResource getSecondary1() {
        return secondary1;
    }

    @Override
    public ColorUIResource getSecondary2() {
        return secondary2;
    }

    @Override
    public ColorUIResource getSecondary3() {
        return secondary3;
    }
}
