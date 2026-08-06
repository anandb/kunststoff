package com.incors.plaf.kunststoff;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.UIDefaults;
import javax.swing.border.EmptyBorder;
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
    }
}
