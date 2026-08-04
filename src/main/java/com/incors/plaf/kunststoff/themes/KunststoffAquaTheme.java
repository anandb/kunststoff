package com.incors.plaf.kunststoff.themes;

import java.awt.Color;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

import com.incors.plaf.kunststoff.KunststoffTheme;

public class KunststoffAquaTheme extends KunststoffTheme {

    private final ColorUIResource primary1 = new ColorUIResource(new Color(0, 80, 100));
    private final ColorUIResource primary2 = new ColorUIResource(new Color(0, 140, 170));
    private final ColorUIResource primary3 = new ColorUIResource(new Color(150, 210, 225));

    private final ColorUIResource secondary1 = new ColorUIResource(new Color(50, 50, 50));
    private final ColorUIResource secondary2 = new ColorUIResource(new Color(100, 100, 100));
    private final ColorUIResource secondary3 = new ColorUIResource(new Color(200, 220, 225));

    private final FontUIResource controlFont;
    private final FontUIResource menuFont;
    private final FontUIResource windowTitleFont;
    private final FontUIResource monospacedFont;

    public KunststoffAquaTheme() {
        String fontName = com.incors.plaf.kunststoff.KunststoffUtilities.getDefaultFontName();
        String monoFontName = com.incors.plaf.kunststoff.KunststoffUtilities.getMonospaceFontName();
        menuFont = new FontUIResource(fontName, java.awt.Font.PLAIN, 16);
        controlFont = new FontUIResource(fontName, java.awt.Font.PLAIN, 16);
        windowTitleFont = new FontUIResource(fontName, java.awt.Font.BOLD, 16);
        monospacedFont = new FontUIResource(monoFontName, java.awt.Font.PLAIN, 14);
    }

    @Override
    public String getName() { return "Aqua"; }

    @Override
    public ColorUIResource getPrimary1() { return primary1; }

    @Override
    public ColorUIResource getPrimary2() { return primary2; }

    @Override
    public ColorUIResource getPrimary3() { return primary3; }

    @Override
    public ColorUIResource getSecondary1() { return secondary1; }

    @Override
    public ColorUIResource getSecondary2() { return secondary2; }

    @Override
    public ColorUIResource getSecondary3() { return secondary3; }

    @Override
    public FontUIResource getControlTextFont() { return controlFont; }

    @Override
    public FontUIResource getMenuTextFont() { return menuFont; }

    @Override
    public FontUIResource getSystemTextFont() { return controlFont; }

    @Override
    public FontUIResource getUserTextFont() { return controlFont; }

    @Override
    public FontUIResource getWindowTitleFont() { return windowTitleFont; }
}
