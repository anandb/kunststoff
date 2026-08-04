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
 * @author  christophw
 * @version
 */
public class KunststoffPresentationTheme extends com.incors.plaf.kunststoff.KunststoffTheme
{
    // primary colors
    private final ColorUIResource primary1 = new ColorUIResource(22, 22, 54);
    private final ColorUIResource primary2 = new ColorUIResource(110, 110, 130);
    private final ColorUIResource primary3 = new ColorUIResource(150, 150, 170);

    // secondary colors
    private final ColorUIResource secondary1 = new ColorUIResource(100, 100, 100);
    private final ColorUIResource secondary2 = new ColorUIResource(130, 130, 130);
    private final ColorUIResource secondary3 = new ColorUIResource(180, 180, 180);

    // fonts
    private FontUIResource controlFont;
    private FontUIResource menuFont;
    private FontUIResource windowTitleFont;
    private FontUIResource monospacedFont;
    private FontUIResource textFont;

    /**
     * Crates this Theme
     */
    public KunststoffPresentationTheme()
    {
        String fontName = com.incors.plaf.kunststoff.KunststoffUtilities.getDefaultFontName();
        String monoFontName = com.incors.plaf.kunststoff.KunststoffUtilities.getMonospaceFontName();
        menuFont = new FontUIResource(fontName,Font.PLAIN, 17);
        controlFont = new FontUIResource(fontName,Font.PLAIN, 16);
        textFont = new FontUIResource(fontName,Font.PLAIN, 14);
        windowTitleFont =  new FontUIResource(fontName, Font.BOLD, 17);
        monospacedFont = new FontUIResource(monoFontName, Font.PLAIN, 15);
    }

    @Override
    public String getName()
    {
        return "Presentation";
    }

    /**
     * The Font of Labels in many cases
     */
    @Override
    public FontUIResource getControlTextFont()
    {
        return controlFont;
    }

    /**
     * The Font of Menus and MenuItems
     */
    @Override
    public FontUIResource getMenuTextFont()
    {
        return menuFont;
    }

    /**
     * The Font of Nodes in JTrees
     */
    @Override
    public FontUIResource getSystemTextFont()
    {
        return controlFont;
    }

    /**
     * The Font in TextFields, EditorPanes, etc.
     */
    @Override
    public FontUIResource getUserTextFont()
    {
        return textFont;
    }

    /**
     * The Font of the Title of JInternalFrames
     */
    @Override
    public FontUIResource getWindowTitleFont()
    {
        return windowTitleFont;
    }

    @Override
    public ColorUIResource getPrimary1()
    {
        return primary1;
    }

    @Override
    public ColorUIResource getPrimary2()
    {
        return primary2;
    }

    @Override
    public ColorUIResource getPrimary3()
    {
        return primary3;
    }

    @Override
    public ColorUIResource getSecondary1()
    {
        return secondary1;
    }

    @Override
    public ColorUIResource getSecondary2()
    {
        return secondary2;
    }

    @Override
    public ColorUIResource getSecondary3()
    {
        return secondary3;
    }

    @Override
    public void addCustomEntriesToTable(UIDefaults table)
    {
        super.addCustomEntriesToTable(table);
        table.put("PasswordField.font", monospacedFont);
        table.put("TextArea.font", monospacedFont);
        table.put("TextPane.font", monospacedFont);
        table.put("EditorPane.font", monospacedFont);
    }
}
