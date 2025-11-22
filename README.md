# Kunststoff Look and Feel

This is a fork of the Kunststoff Look and Feel project from [SourceForge](https://sourceforge.net/p/kunststoff-laf/).

## About

Kunststoff LAF (Look and Feel) is a Java Swing look and feel library that provides a modern, gradient-based appearance for Java applications.

## Changes Made in This Fork

This fork includes several modifications and improvements over the original SourceForge version:

- **UI Component Updates**: Enhancements to List, TabbedPane, TextArea, and Tree UI components
- **Menu Spacing**: Added adjustments to menu spacing for better visual hierarchy
- **Brown Theme Variant**: Changes to theme colors featuring brown tones for aesthetic customization
- **Font Change**: Default font set to Segoe UI for improved readability
- **Build Configuration**: Updates to Maven project configuration (pom.xml)

## Usage

### NetBeans IDE

To use this look and feel with NetBeans, you can run the following command:

```bash
netbeans -J-Xmx8192m -cp:p "$HOME/.m2/repository/com/incors/kunstoff-laf/2.0.2/kunstoff-laf-2.0.2.jar" --laf com.incors.plaf.kunststoff.KunststoffLookAndFeel --fontsize 16
```

**Note:** This is an example usage. Adjust the paths to match your NetBeans installation directory and the location of the Kunststoff LAF JAR file (after building the project with `mvn package`).
