package com.incors.plaf.kunststoff;

import java.awt.Color;

import javax.swing.plaf.*;

public class KunststoffGradientTheme implements GradientTheme {

  // gradient colors

  private final ColorUIResource componentGradientColorReflection = new ColorUIResource(new Color(255, 255, 255, 48));

  private final ColorUIResource componentGradientColorShadow = new ColorUIResource(new Color(0, 0, 0, 24));

  private final ColorUIResource textComponentGradientColorReflection = new ColorUIResource(new Color(0, 0, 0, 32));

  private final ColorUIResource textComponentGradientColorShadow = null;

  private final int backgroundGradientShadow = 32;

  // methods

  public String getName() { return "Default Kunststoff Gradient Theme"; }


  // methods for getting gradient colors

  @Override
  public ColorUIResource getComponentGradientColorReflection() {
    return componentGradientColorReflection;
  }

  @Override
  public ColorUIResource getComponentGradientColorShadow() {
    return componentGradientColorShadow;
  }

  @Override
  public ColorUIResource getTextComponentGradientColorReflection() {
    return textComponentGradientColorReflection;
  }

  @Override
  public ColorUIResource getTextComponentGradientColorShadow() {
    return textComponentGradientColorShadow;
  }

  @Override
  public int getBackgroundGradientShadow() {
    return backgroundGradientShadow;
  }

}