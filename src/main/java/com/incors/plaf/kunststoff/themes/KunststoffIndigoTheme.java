package com.incors.plaf.kunststoff.themes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

import com.incors.plaf.kunststoff.KunststoffTheme;
import com.incors.plaf.kunststoff.KunststoffUtilities;

public class KunststoffIndigoTheme extends KunststoffTheme {

    private final ColorUIResource primary1 = new ColorUIResource(new Color(45, 45, 95));
    private final ColorUIResource primary2 = new ColorUIResource(new Color(86, 86, 150));
    private final ColorUIResource primary3 = new ColorUIResource(new Color(178, 178, 214));

    private final ColorUIResource secondary1 = new ColorUIResource(new Color(35, 35, 70));
    private final ColorUIResource secondary2 = new ColorUIResource(new Color(98, 98, 140));
    private final ColorUIResource secondary3 = new ColorUIResource(new Color(224, 224, 238));

    private final FontUIResource controlFont;
    private final FontUIResource menuFont;
    private final FontUIResource windowTitleFont;

    public KunststoffIndigoTheme() {
        String fontName = KunststoffUtilities.getDefaultFontName();
        String monoFontName = KunststoffUtilities.getMonospaceFontName();
        menuFont = KunststoffUtilities.createFont(fontName, Font.PLAIN, 16);
        controlFont = KunststoffUtilities.createFont(fontName, Font.PLAIN, 16);
        windowTitleFont = KunststoffUtilities.createFont(fontName, Font.BOLD, 16);
        monospacedFont = KunststoffUtilities.createFont(monoFontName, Font.PLAIN, 14);
    }

    @Override
    public String getName() { return "Indigo"; }

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
