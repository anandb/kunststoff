package com.incors.plaf.kunststoff.themes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

import com.incors.plaf.kunststoff.KunststoffTheme;
import com.incors.plaf.kunststoff.KunststoffUtilities;

public class KunststoffForestTheme extends KunststoffTheme {

    private final ColorUIResource primary1 = new ColorUIResource(new Color(30, 60, 45));
    private final ColorUIResource primary2 = new ColorUIResource(new Color(60, 110, 80));
    private final ColorUIResource primary3 = new ColorUIResource(new Color(160, 195, 170));

    private final ColorUIResource secondary1 = new ColorUIResource(new Color(25, 45, 35));
    private final ColorUIResource secondary2 = new ColorUIResource(new Color(80, 110, 90));
    private final ColorUIResource secondary3 = new ColorUIResource(new Color(215, 230, 218));

    private final FontUIResource controlFont;
    private final FontUIResource menuFont;
    private final FontUIResource windowTitleFont;

    public KunststoffForestTheme() {
        String fontName = KunststoffUtilities.getDefaultFontName();
        String monoFontName = KunststoffUtilities.getMonospaceFontName();
        menuFont = KunststoffUtilities.createFont(fontName, Font.PLAIN, 16);
        controlFont = KunststoffUtilities.createFont(fontName, Font.PLAIN, 16);
        windowTitleFont = KunststoffUtilities.createFont(fontName, Font.BOLD, 16);
        monospacedFont = KunststoffUtilities.createFont(monoFontName, Font.PLAIN, 14);
    }

    @Override
    public String getName() { return "Forest"; }

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
