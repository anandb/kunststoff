package com.incors.plaf.kunststoff;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.UIDefaults;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.*;
import javax.swing.plaf.metal.DefaultMetalTheme;

public class KunststoffTheme extends DefaultMetalTheme {

    private final FontUIResource controlFont;
    private final FontUIResource menuFont;
    private final FontUIResource windowTitleFont;

    /**
     * Monospaced font used for PasswordField, TextArea, TextPane and EditorPane.
     * Subclasses set this in their constructor with their preferred size.
     */
    protected FontUIResource monospacedFont;

    public KunststoffTheme() {
        String fontName = KunststoffUtilities.getDefaultFontName();
        String monoFontName = KunststoffUtilities.getMonospaceFontName();
        menuFont = new FontUIResource(fontName, java.awt.Font.PLAIN, 16);
        controlFont = new FontUIResource(fontName, java.awt.Font.PLAIN, 16);
        windowTitleFont = new FontUIResource(fontName, java.awt.Font.BOLD, 16);
        monospacedFont = new FontUIResource(monoFontName, java.awt.Font.PLAIN, 14);
    }

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

    @Override
    public ColorUIResource getPrimary1() {
        return new ColorUIResource(new Color(102, 51, 0)); // Dark Brown
    }

    @Override
    public ColorUIResource getPrimary2() {
        return new ColorUIResource(new Color(153, 102, 51)); // Medium Brown
    }

    @Override
    public ColorUIResource getPrimary3() {
        return new ColorUIResource(new Color(204, 153, 102)); // Light Brown
    }

    @Override
    public ColorUIResource getSecondary1() {
        return new ColorUIResource(new Color(77, 38, 0)); // Very Dark Brown
    }

    @Override
    public ColorUIResource getSecondary2() {
        return new ColorUIResource(new Color(128, 77, 26)); // Medium Dark Brown
    }

    @Override
    public ColorUIResource getSecondary3() {
        return new ColorUIResource(new Color(212, 204, 181)); // Medium Light Brown
    }

    // ── Derived accent colors ──────────────────────────────────────────
    // Every colour below is computed from the palette (getPrimary2 / getSecondary3 etc.)
    // so each theme subclass automatically gets a harmonious accent.

    @Override
    public ColorUIResource getFocusColor() {
        return new ColorUIResource(lighten(getPrimary2(), 0.18f));
    }

    @Override
    public ColorUIResource getTextHighlightColor() {
        return new ColorUIResource(lighten(getPrimary2(), 0.18f));
    }

    @Override
    public ColorUIResource getHighlightedTextColor() {
        return new ColorUIResource(Color.WHITE);
    }

    @Override
    public ColorUIResource getInactiveControlTextColor() {
        return new ColorUIResource(darken(getSecondary2(), 0.08f));
    }

    @Override
    public ColorUIResource getInactiveSystemTextColor() {
        return getInactiveControlTextColor();
    }

    @Override
    public ColorUIResource getMenuDisabledForeground() {
        return getInactiveControlTextColor();
    }

    @Override
    public void addCustomEntriesToTable(UIDefaults table) {
        super.addCustomEntriesToTable(table);
        table.put("PasswordField.font", monospacedFont);
        table.put("TextArea.font", monospacedFont);
        table.put("TextPane.font", monospacedFont);
        table.put("EditorPane.font", monospacedFont);

        table.put("CheckBoxMenuItem.border", new EmptyBorder(8, 4, 8, 4));
        table.put("RadioButtonMenuItem.border", new EmptyBorder(8, 4, 8, 4));
        table.put("MenuItem.border", new EmptyBorder(8, 4, 8, 4));
        table.put("Menu.margin", new Insets(8, 4, 8, 4));
        table.put("Menu.border", new EmptyBorder(8, 4, 8, 4));

        // Toolbar buttons: extra horizontal padding so they aren't packed together
        table.put("ToolBar.buttonMargins", new Insets(4, 8, 4, 8));

        // Button padding — +1px top to offset Metal's 1px bottom bevel shadow
        table.put("Button.border", new EmptyBorder(7, 14, 5, 14));
        table.put("ToggleButton.border", new EmptyBorder(7, 14, 5, 14));

        // Text field padding — symmetric so text isn't crammed against the top
        table.put("TextField.border", new CompoundBorder(
            new LineBorder(darken(getSecondary3(), 0.18f), 1),
            new EmptyBorder(4, 6, 4, 6)));

        // Zebra striping — lighter variant of the palette's Secondary3
        table.put("Table.alternateRowColor", new ColorUIResource(lighten(getSecondary3(), 0.10f)));

        // Slimmer, softer scrollbars than the chunky Metal default
        table.put("ScrollBar.width", 14);
        table.put("ScrollBar.thumb", new ColorUIResource(darken(getSecondary3(), 0.18f)));
        table.put("ScrollBar.track", new ColorUIResource(lighten(getSecondary3(), 0.04f)));

        // Tabbed pane — derived from Secondary3 palette
        ColorUIResource tabSelected = new ColorUIResource(lighten(getSecondary3(), 0.02f));
        table.put("TabbedPane.selected", tabSelected);
        table.put("TabbedPane.contentAreaColor", tabSelected);
        table.put("TabbedPane.tabAreaInsets", new Insets(4, 6, 0, 6));
        table.put("TabbedPane.selectedTabPadInsets", new Insets(2, 2, 2, 2));

        // Card-like tooltips derived from palette
        table.put("ToolTip.background", new ColorUIResource(lighten(getSecondary3(), 0.10f)));
        table.put("ToolTip.foreground", new ColorUIResource(getPrimary1()));
        table.put("ToolTip.border", new CompoundBorder(
            new LineBorder(darken(getSecondary3(), 0.18f), 1),
            new EmptyBorder(4, 6, 4, 6)));
    }

    // ── HSB helpers for palette-derived colours ─────────────────────────

    private static float[] toHSB(Color c) {
        return Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
    }

    private static Color fromHSB(float h, float s, float b) {
        return new Color(Color.HSBtoRGB(h, s, b));
    }

    /** Lighten by raising brightness, keeping hue and saturation. */
    private static Color lighten(Color c, float amount) {
        float[] hsb = toHSB(c);
        float b = Math.min(1f, hsb[2] + amount);
        return fromHSB(hsb[0], hsb[1], b);
    }

    /** Darken by lowering brightness, keeping hue and saturation. */
    private static Color darken(Color c, float amount) {
        float[] hsb = toHSB(c);
        float b = Math.max(0f, hsb[2] - amount);
        return fromHSB(hsb[0], hsb[1], b);
    }
}
