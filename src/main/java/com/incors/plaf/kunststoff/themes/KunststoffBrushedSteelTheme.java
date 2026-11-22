package com.incors.plaf.kunststoff.themes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.UIDefaults;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

import com.incors.plaf.kunststoff.KunststoffTheme;
import com.incors.plaf.kunststoff.KunststoffUtilities;

/**
 * Brushed aluminium, after the old QuickTime Player: silver-grey chrome for
 * windows and controls, near-black text and a QuickTime blue accent for focus,
 * selection highlights and the progress bar.
 */
public class KunststoffBrushedSteelTheme extends KunststoffTheme {

    /** QuickTime blue, used for the accents the base theme would derive in grey. */
    private static final Color ACCENT = new Color(46, 112, 176);

    private final ColorUIResource primary1 = new ColorUIResource(new Color(38, 42, 46));
    private final ColorUIResource primary2 = new ColorUIResource(ACCENT);
    private final ColorUIResource primary3 = new ColorUIResource(new Color(188, 196, 204));

    private final ColorUIResource secondary1 = new ColorUIResource(new Color(52, 56, 60));
    private final ColorUIResource secondary2 = new ColorUIResource(new Color(122, 128, 134));
    private final ColorUIResource secondary3 = new ColorUIResource(new Color(222, 225, 228));

    private final FontUIResource controlFont;
    private final FontUIResource menuFont;
    private final FontUIResource windowTitleFont;

    public KunststoffBrushedSteelTheme() {
        String fontName = KunststoffUtilities.getDefaultFontName();
        String monoFontName = KunststoffUtilities.getMonospaceFontName();
        menuFont = KunststoffUtilities.createFont(fontName, Font.PLAIN, 16);
        controlFont = KunststoffUtilities.createFont(fontName, Font.PLAIN, 16);
        windowTitleFont = KunststoffUtilities.createFont(fontName, Font.BOLD, 16);
        monospacedFont = KunststoffUtilities.createFont(monoFontName, Font.PLAIN, 14);
    }

    @Override
    public String getName() { return "Brushed Steel"; }

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

    /**
     * The base theme derives selection and progress colours from the greys of
     * the palette; QuickTime's chrome carries its blue there instead.
     */
    @Override
    public void addCustomEntriesToTable(UIDefaults table) {
        super.addCustomEntriesToTable(table);
        ColorUIResource accent = new ColorUIResource(ACCENT);
        table.put("List.selectionBackground", accent);
        table.put("Table.selectionBackground", accent);
        table.put("Tree.selectionBackground", accent);
        table.put("ProgressBar.foreground", accent);
    }
}
