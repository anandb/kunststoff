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
- **Combo Boxes**: The selected value is no longer clipped when a combo box is shorter than its preferred height
- **Build Configuration**: Updates to Maven project configuration (pom.xml)

## Environment variables

All variables are read once, when the look and feel is initialised. Set them before launching the application; a restart is required for changes to take effect.

| Variable | Purpose | Values and fallback |
|----------|---------|---------------------|
| `KUNSTSTOFF_THEME` | Selects the colour theme. | A theme name, matched case-insensitively after trimming. If unset, empty, or unknown, the look and feel uses `brick`, the original Kunststoff brown theme. See [Themes](#themes). |
| `KUNSTSTOFF_FONT` | Family name for the proportional UI font (menus, controls, window titles). | Any installed font family, e.g. `Fira Sans`. Falls back to the `kunststoff.font` system property, then to `Segoe UI`, `Fira Sans`, or the logical `SansSerif`, whichever is available first. |
| `KUNSTSTOFF_MONOSPACE_FONT` | Family name for monospaced text (password fields, text areas, text panes, editor panes). | Any installed monospaced family, e.g. `Fira Code`. Falls back to the first available of `Fira Code`, `Monaco`, `Consolas`, `Droid Sans Mono`, `Hack`, then the logical `Monospaced`. |
| `KUNSTSTOFF_MIN_FONT_SIZE` | Minimum font size in points. Every theme font (UI text, menus, window titles, monospaced text) is raised to at least this size; larger theme sizes are kept. | A positive integer. If unset, empty, invalid, or non-positive, the floor is `12`. |

Notes:

- `KUNSTSTOFF_FONT` takes precedence over the JVM system property `kunststoff.font` (`-Dkunststoff.font="Fira Sans"`). The monospaced font and the theme have no system-property equivalent.
- These variables choose font families only. Font sizes come from the theme (16 pt UI text, 14 pt monospaced text), floored by `KUNSTSTOFF_MIN_FONT_SIZE`, which defaults to 12 pt.
- An unknown `KUNSTSTOFF_THEME` value is not an error; it silently falls back to `brick`.
- `KunststoffNotebookTheme` and `KunststoffPresentationTheme` exist in `com.incors.plaf.kunststoff.themes` but are not registered, so they cannot be selected with `KUNSTSTOFF_THEME`.

Example:

```bash
export KUNSTSTOFF_THEME=steelblue
export KUNSTSTOFF_FONT="Fira Sans"
export KUNSTSTOFF_MONOSPACE_FONT="Fira Code"
export KUNSTSTOFF_MIN_FONT_SIZE=13
netbeans -J-Xmx8192m -cp:p "$HOME/.m2/repository/io/github/anandb/kunstoff-laf/2.0.4/kunstoff-laf-2.0.4.jar" --laf com.incors.plaf.kunststoff.KunststoffLookAndFeel
```

## Themes

Select a theme at runtime with the `KUNSTSTOFF_THEME` environment variable (case-insensitive):

| Name | Description |
|------|-------------|
| `default` | Gray-blue (base colors) |
| `brick` | Warm brown (original Kunststoff) |
| `aqua` | Teal/aqua |
| `burgundy` | Muted wine red |
| `creme` | Warm cream/tan |
| `forest` | Deep cool green |
| `indigo` | Deep blue-violet |
| `mint` | Light green-teal |
| `olive` | Olive green |
| `purple` | Purple/violet |
| `sepia` | Sepia/brown-yellow |
| `slate` | Neutral cool gray |
| `steelblue` | Blue-gray |
| `terracotta` | Warm clay orange |

Names are matched case-insensitively. If `KUNSTSTOFF_THEME` is unset, empty, or not recognised, the look and feel uses `brick`, the original Kunststoff brown theme; the `default` entry selects `KunststoffDefaultTheme` (gray-blue).

Example:

```bash
export KUNSTSTOFF_THEME=brick
netbeans -J-Xmx8192m -cp:p "$HOME/.m2/repository/io/github/anandb/kunstoff-laf/2.0.4/kunstoff-laf-2.0.4.jar" --laf com.incors.plaf.kunststoff.KunststoffLookAndFeel --fontsize 16
```

## Usage

### NetBeans IDE

To use this look and feel with NetBeans, you can run the following command:

```bash
netbeans -J-Xmx8192m -cp:p "$HOME/.m2/repository/io/github/anandb/kunstoff-laf/2.0.4/kunstoff-laf-2.0.4.jar" --laf com.incors.plaf.kunststoff.KunststoffLookAndFeel --fontsize 16
```

**Note:** This is an example usage. Adjust the paths to match your NetBeans installation directory and the location of the Kunststoff LAF JAR file (after building the project with `mvn package`).
