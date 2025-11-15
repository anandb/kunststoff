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

    // primary colors
//  private final ColorUIResource primary1 = new ColorUIResource(32, 32, 32);
//
//  private final ColorUIResource primary2 = new ColorUIResource(160, 160, 180);
//
//  private final ColorUIResource primary3 = new ColorUIResource(200, 200, 224);
//
//
//  // secondary colors
//  private final ColorUIResource secondary1 = new ColorUIResource(130, 130, 130);
//
//  private final ColorUIResource secondary2 = new ColorUIResource(180, 180, 180);
//
//  private final ColorUIResource secondary3 = new ColorUIResource(224, 224, 224);
//
//
//  // methods
//
//  public String getName() { return "Default Kunststoff Theme"; }
//
//
//  protected ColorUIResource getPrimary1() { return primary1; }
//
//  protected ColorUIResource getPrimary2() { return primary2; }
//
//  protected ColorUIResource getPrimary3() { return primary3; }
//
//
//  protected ColorUIResource getSecondary1() { return secondary1; }
//
//  protected ColorUIResource getSecondary2() { return secondary2; }
//
//  protected ColorUIResource getSecondary3() { return secondary3; }
}
