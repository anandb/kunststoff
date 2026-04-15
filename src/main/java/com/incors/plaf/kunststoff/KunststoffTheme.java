package com.incors.plaf.kunststoff;

import java.awt.Color;

import javax.swing.plaf.*;
import javax.swing.plaf.metal.DefaultMetalTheme;

public class KunststoffTheme extends DefaultMetalTheme {

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
}
